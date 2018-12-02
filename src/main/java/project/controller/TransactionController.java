package project.controller;

import org.assertj.core.util.Lists;
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
import java.util.Collections;
import java.util.List;


/**
 * Controller that handles all transaction methods
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
    }

    /**
     * Shows all transactins for current user on the url /transaction/all
     * @param model
     * @return
     */
    @RequestMapping("/all")
    public String allTransactions(Model model){
        //Get current user
        this.currUser = getUser();

        //Find all transasations in reverse order by date
        List <Transaction> allTransactions = transactionManagementService.findAllReverseOrder(currUser.getUsername());

        //Add it to model
        model.addAttribute("currUser", currUser);
        model.addAttribute("transactions", allTransactions);

        //return the view
        return "transaction/transactionList";
    }

    /**
     * Attribute to create the list of friends available for splitting a transaction
     * @return
     */
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

    /**
     * Method the recieves the GET request on the url /transaction/
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String transactionNewGet(Model model){
        this.currUser = getUser();

        //Create a new empty transaction
        Transaction transaction = new Transaction();

        //Add it to the model
        model.addAttribute("transaction", transaction);

        //Return the view
        return "transaction/transactionNew";
    }

    /**
     * Method that receives the POST request on the URL //transaction/new
     * and receives the ModelAttribute("transaction")
     * This handles the GET request for this URL
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String transactionNewPost(@ModelAttribute("transaction") Transaction transaction,
                                     Model model){
        //Find the current user
        this.currUser = getUser();

        //Create a list for the split info and names
        List <String> splitNames = transaction.getSplitInfo();
        // Catch all check for inputs
        if((splitNames == null || splitNames.size()<=0) || (splitNames.size() == 1 && splitNames.get(0).equals("Me"))){
            model.addAttribute("msg", "Check your inputs");
            return "transaction/transactionNew";
        }

        List <String> splitInfo = new ArrayList<String>(splitNames);

        //Calculate the ammount based on the numer of friends selected for the split
        Double splitAmmount = (int)((transaction.getAmount() / splitNames.size())*100)/100.0;
        //Initialize the split ID
        Long currSplitId = null;

        //Check if the user includes himseld
        if(splitInfo.get(0).equals("Me")) {
            splitInfo.remove(0);
        }
        //Add to the splitInfo list
        splitInfo.add(0,currUser.getUsername());
        splitInfo.add(0,splitAmmount.toString());

        //Creates new transactions for every friend on the list excluding the user himself
        for (String name: splitNames) {
            if(name.equals("Me")){

            }
            else {
                Account currAccount = accountManagementService.findAccountByUsers(currUser.getUsername(), name);
                Transaction currTransaction = new Transaction();

                currTransaction.setAccount(currAccount);
                currTransaction.setDescr(transaction.getDescr());
                currTransaction.setSplitInfo(splitInfo);

                int prefix = -1;
                if (currAccount.getUser1().equals(currUser.getUsername())){
                    prefix = 1;
                }
                currTransaction.setAmount(prefix*splitAmmount);
                accountManagementService.updateBalance(prefix*splitAmmount, currAccount);

                if (currSplitId == null){
                    transactionManagementService.save(currTransaction);
                    currSplitId = currTransaction.getId();
                }

                currTransaction.setSplitId(currSplitId);
                transactionManagementService.save(currTransaction);
            }
        }
        //Return the view
        return "redirect:/transaction/all";
    }

    // Method that returns the correct view for the URL /transaction/{transactionID}
    @RequestMapping(value = "/{transactionID}", method = RequestMethod.GET)
    public String transactionView(@PathVariable String transactionID,
                                  Model model) {
        //Get current user
        this.currUser = getUser();

        //Find the transaction from the ID
        Long id = Long.parseLong(transactionID);
        Transaction transaction = transactionManagementService.findOne(id);

        //Handles correct views of the amount if you are the paying user or not
        int prefix = -1;
        if (transaction.getAccount().getUser1().equals(currUser.getUsername())) {
            prefix =1;
        }

        //Add to the model
        model.addAttribute("transaction", transaction);
        model.addAttribute("sign", prefix);

        // Return the view
        return "transaction/transactionView";
    }

}
