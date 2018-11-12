package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import project.persistence.entities.PostitNote;
import project.persistence.entities.Transaction;
import project.persistence.entities.User;
import project.service.TransactionManagementService;
import project.service.UserManagementService;

import java.util.Arrays;
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

    // Dependency Injection
    @Autowired
    public TransactionController(TransactionManagementService transactionManagementService) {
        this.transactionManagementService = transactionManagementService;
    }

    // Method that returns the correct view for the URL /transaction
    @RequestMapping(value = "")
    public String demoPage(){
        return "demo/demo"; // this returns a .jsp file with the path /webapp/WEB-INF/jsp/demo/transactionNew.jsp
    }

    // Method that returns the correct view for the URL /transaction/new
    // This handles the GET request for this URL
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String transactionNewGet(Model model /*ModelMap map*/){
        /*
        User sakki = new User("sakki", "Ísak", "Kolbeins", "iak5@hi.is", "lykilord");
        User frodo = new User("frodo", "Fríða", "Dóttir", "frodo@hi.is", "lykilord5");
        User snara = new User("snara", "Sara", "Snara", "nice@hi.is", "SuperSecret");

        List<User> friendlist = Arrays.asList(new User[]{sakki, frodo,snara});

        sakki.setFriendlist(friendlist); */

        // Add new transaction to the model
        model.addAttribute("transaction", new Transaction());

        // Add user friendslist to the model
        // map.addAttribute("friendlist", friendlist);


        // Return the view
        return "transaction/transactionNew";
    }

    // Method that receives the POST request on the URL //transaction/new
    // and receives the ModelAttribute("transaction")
    // This handles the GET request for this URL
    @RequestMapping(value = "/new", method = RequestMethod.POST)
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

        System.out.println(transaction.getDescr());
        Long id = transaction.getId();

        // Return the view --- {" + id + "}
        return "transaction/transactionView";
    }

    // Method that returns the correct view for the URL /postit/{name}
    // The {name} part is a Path Variable, and we can reference that in our method
    // parameters as a @PathVariable. This enables us to create dynamic URLs that are
    // based on the data that we have.
    // This method finds all Postit Notes posted by someone with the requested {name}
    // and returns a list with all those Postit Notes.

    @RequestMapping(value = "/view/{transactionID}", method = RequestMethod.GET)
    public String transactionView(@PathVariable String transactionID,
                                  Model model)
            /*@RequestParam(value="transaction", required=false, defaultValue= "default") String transaction,
                                  Model model)*/{

        /*// Get all Postit Notes with this name and add them to the model
        model.addAttribute("postitNotes", postitNoteService.findByName(name));


        // Add a new Postit Note to the model for the form
        // If you look at the form in PostitNotes.jsp, you can see that we
        // reference this attribute there by the name `postitNote`.
        model.addAttribute("postitNote", new PostitNote());


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
