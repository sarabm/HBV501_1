package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.Transaction;
import project.persistence.entities.User;
import project.service.AccountManagementService;
import project.service.StringManipulationService;
import project.service.TransactionManagementService;
import project.service.UserManagementService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    // Instance Variables
    StringManipulationService stringService;
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
    public HomeController(TransactionManagementService transactionManagementService,
                          UserManagementService userManagementService,
                          AccountManagementService accountManagementService,
                          StringManipulationService stringService) {
        this.transactionManagementService = transactionManagementService;
        this.userManagementService = userManagementService;
        this.accountManagementService = accountManagementService;
        this.stringService = stringService;
    }

    // Request mapping is the path that you want to map this method to
    // In this case, the mapping is the root "/", so when the project
    // is running and you enter "localhost:8080" into a browser, this
    // method is called
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){
        this.currUser = getUser();
        List<Transaction> allTransactions = transactionManagementService.findAll();
        allTransactions.sort((t1, t2) -> t1.getDate().compareTo(t2.getDate()));
        Collections.reverse(allTransactions);
        List<Transaction> lastestTransactions = new ArrayList<Transaction>(allTransactions.subList(0, 4));

        model.addAttribute("currUser", currUser);
        model.addAttribute("transactions", lastestTransactions);
        return "Index";
    }

    // To call this method, enter "localhost:8080/user" into a browser
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model){

        // Here we will show how to add attributes to a model and send it to the view

        // Since this small example is for a user, let's create some attributes
        // that users might usually have in a system
        String name = "Rincewind";
        String job  = "Wizzard";
        String email = "rincewizz@unseenuni.edu";
        String description = "most likely to survive in a dungeon dimension.";


        // Since we want our attributes regarding the user always in the same format,
        // we are going to convert some strings using our StringManipulationService

        // Let's assume that the name, job and description always have
        // the first character in upper case
        name = stringService.convertsFirstCharInStringToUpperCase(name);
        job = stringService.convertsFirstCharInStringToUpperCase(job);
        description = stringService.convertsFirstCharInStringToUpperCase(description);

        // Let's assume that we always want e-mail in lower case
        email = stringService.convertStringToLowerCase(email);


        // Now let's add the attributes to the model
        model.addAttribute("name",name);
        model.addAttribute("job",job);
        model.addAttribute("email",email);
        model.addAttribute("description",description);

        // By adding attributes to the model, we can pass information from the controller
        // to the view (the .jsp file).
        // Look at the User.jsp file in /main/webapp/WEB-INF/jsp/ to see how the data is accessed
        return "User";
    }
}
