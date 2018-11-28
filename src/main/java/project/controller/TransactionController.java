package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.persistence.entities.PostitNote;
import project.persistence.entities.Transaction;
import project.persistence.entities.User;
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
    private User currUser;

    // Getting current user  ---
    private User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userManagementService.findByUsername(userDetails.getUsername());
    }

    // Dependency Injection
    @Autowired
    public TransactionController(TransactionManagementService transactionManagementService, UserManagementService userManagementService) {
        this.transactionManagementService = transactionManagementService;
        this.userManagementService = userManagementService;
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

    // Method that returns the correct view for the URL /transaction/new
    // This handles the GET request for this URL
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String transactionNewGet(Model model /*ModelMap map*/){
        this.currUser = getUser();
/*
        User sakki = new User("sakki", "Ísak", "Kolbeins", "iak5@hi.is", "lykilord");
        User frodo = new User("frodo", "Fríða", "Dóttir", "frodo@hi.is", "lykilord5");
        User snara = new User("snara", "Sara", "Snara", "nice@hi.is", "SuperSecret");

        List<User> friendlistSakki = new ArrayList<User>();
        friendlistSakki.add(frodo);
        friendlistSakki.add(snara);
*/
        /*
        List<User> friendlistFrodo = new ArrayList<User>();
        friendlistFrodo.add(sakki);
        friendlistFrodo.add(snara);

        List<User> friendlistSnara = new ArrayList<User>();
        friendlistSnara.add(frodo);
        friendlistSnara.add(sakki);*/

        //sakki.setFriendlist(friendlistSakki);
       //frodo.setFriendlist(friendlistFrodo);
        //snara.setFriendlist(friendlistSnara);

        //System.out.println(sakki);


        //User sakki = new User();

        // userManagementService.save(sakki);
        //userManagementService.save(frodo);
        //userManagementService.save(snara);

        // Add new transaction to the model
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("friendlist", currUser.getFriendlist());

        // Add user friendslist to the model
        //model.addAttribute("friendlist", friendlistFrodo);

        // Return the view
        return "transaction/transactionNew";
    }

    // Method that receives the POST request on the URL //transaction/new
    // and receives the ModelAttribute("transaction")
    // This handles the GET request for this URL
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String transactionNewPost(@ModelAttribute("transaction") Transaction transaction,
                                     Model model){

        /*@ModelAttribute("transaction") Transaction transaction,
                                     @ModelAttribute("friendlist") List<User> friendlist,ModelMap map){*/


        // Save transaction from the form
        transactionManagementService.save(transaction);

        // Add a new Postit Note to the model for the form
        // If you look at the form in PostitNotes.jsp, you can see that we
        // reference this attribute there by the name `postitNote`.
        //  model.addAttribute("postitNote", new PostitNote());

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
                                  Model model)
            /*@RequestParam(value="transaction", required=false, defaultValue= "default") String transaction,
                                  Model model)*/{



        // Return the view */
        // System.out.println(transaction);
        Long id = Long.parseLong(transactionID);

        model.addAttribute("transaction", transactionManagementService.findOne(id));
        return "transaction/transactionView";
    }

    // Method that returns the correct view for the URL /{transactionId}/update
    // This handles the GET request for this URL
    @RequestMapping(value = "/{transactionId}/update", method = RequestMethod.GET)
    public String transactionUpdateNew(Model model){

        // Add a new Postit Note to the model for the form
        // If you look at the form in PostitNotes.jsp, you can see that we
        // reference this attribute there by the name `postitNote`.
        model.addAttribute("postitNote",new PostitNote());

        // Here we get all the Postit Notes (in a reverse order) and add them to the model
        // model.addAttribute("postitNotes",postitNoteService.findAllReverseOrder());

        // Return the view
        return "postitnotes/PostitNotes";
    }


    @RequestMapping(value = "/{transactionId}/update", method = RequestMethod.POST)
    public String transactionUpdateSave(@PathVariable String name,
                                             Model model){
        /*
        // Get all Postit Notes with this name and add them to the model
        model.addAttribute("postitNotes", postitNoteService.findByName(name));

        // Add a new Postit Note to the model for the form
        // If you look at the form in PostitNotes.jsp, you can see that we
        // reference this attribute there by the name `postitNote`.
        model.addAttribute("postitNote", new PostitNote());

        // Return the view */
        return "postitnotes/PostitNotes";
    }

}
