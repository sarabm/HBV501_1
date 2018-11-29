package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.persistence.entities.Account;
import project.persistence.entities.Transaction;
import project.persistence.entities.User;
import project.service.AccountManagementService;
import project.service.TransactionManagementService;
import project.service.UserManagementService;

import java.util.ArrayList;
import java.util.List;

/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
@RequestMapping("/transaction")
public class TransactionController {

    // Instance Variables
    private TransactionManagementService transactionManagementService;
    private UserManagementService userManagementService;
    private AccountManagementService accountManagementService;
    private User currUser;

    // Getting current user  ---
    private User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userManagementService.findByUsername(userDetails.getUsername());
    }

    // Dependency Injection
    @Autowired
    public TransactionController(TransactionManagementService transactionManagementService,
                                 UserManagementService userManagementService,
                                 AccountManagementService accountManagementService) {
        this.transactionManagementService = transactionManagementService;
        this.userManagementService = userManagementService;
        this.accountManagementService = accountManagementService;
        //this.securityContextHolder = securityContextHolder;
        //this.userDetails = (UserDetails) securityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    // Method that returns the correct view for the URL /transaction
    @RequestMapping("/all")
    public String allTransactions(Model model){
        // To use current user,,,,, call this
        this.currUser = getUser();

        model.addAttribute("userName", currUser.getUsername());
        model.addAttribute("firstName", currUser.getFirstname());
        model.addAttribute("currUser", currUser);
        model.addAttribute("transactions", transactionManagementService.findAll());
        return "transaction/transactionList";
    }

    @ModelAttribute("splitList")
    public List<String> getSplitList() {
        List<String> splitList = new ArrayList<>();
        splitList.add("Me");
        this.currUser = getUser();
        List<User> friendlist = currUser.getFriendlist();
        for (User friend : friendlist) {
            splitList.add(friend.getUsername());
        }
        return splitList;
    }

    // Method that returns the correct view for the URL /transaction/new
    // This handles the GET request for this URL
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String transactionNewGet(Model model /*ModelMap map*/){
        this.currUser = getUser();

        //List<User> friendlist = currUser.getFriendlist(); // Obtain all products.

        // Add new transaction and friendlist to the model
        Transaction transaction = new Transaction();
        transaction.setSplitInfo(getSplitList());

        model.addAttribute("transaction", transaction);
        // model.addAttribute("friendlist", friendlist);

        // Return the view
        return "transaction/transactionNew";
    }

    // Method that receives the POST request on the URL //transaction/new
    // and receives the ModelAttribute("transaction")
    // This handles the GET request for this URL
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String transactionNewPost(@ModelAttribute("transaction") Transaction transaction,
                                     Model model){

        List <String> splitNames = transaction.getSplitInfo();
        Double splitAmmount = transaction.getAmount() / splitNames.size();
        Long currSplitId = null;

        for (String name: splitNames) {
            if(name.equals("Me"));
            else {
                Account currAccount;
                //accountManagementService.findByUsername // add account
                // get is User1
                Transaction currTransaction = new Transaction();
                transaction.setAmount(splitAmmount);
                transaction.setDescr(transaction.getDescr());

            }


        }



        //String[] selections = request.getParameterValues("selection");

        //bæta við það að account ID fylgi með
        // Save transaction from the form
        transactionManagementService.save(transaction);

        //System.out.println(transaction.getDescr());
        Long id = transaction.getId();
        // Return the view --- {" + id + "}
        return "redirect:/transaction/"+id;
        //return "";
    }

    // Method that returns the correct view for the URL /postit/{name}
    // The {name} part is a Path Variable, and we can reference that in our method
    // parameters as a @PathVariable. This enables us to create dynamic URLs that are
    // based on the data that we have.
    // This method finds all Postit Notes posted by someone with the requested {name}
    // and returns a list with all those Postit Notes.

    @RequestMapping(value = "/{transactionID}", method = RequestMethod.GET)
    public String transactionView(@PathVariable String transactionID,
                                  Model model) {

        // Return the view */
        Long id = Long.parseLong(transactionID);

        model.addAttribute("transaction", transactionManagementService.findOne(id));
        return "transaction/transactionView";
    }

    // Method that returns the correct view for the URL /{transactionId}/update
    // This handles the GET request for this URL
    @RequestMapping(value = "/{transactionID}/update", method = RequestMethod.GET)
    public String transactionUpdateNew(@PathVariable String transactionID, Model model){

        /*Long id = Long.parseLong(transactionID);
        Transaction transaction = transactionManagementService.findOne(id);


        model.addAttribute("transaction",transaction);
       */
        return "ransaction/transactionNew";
    }
}
