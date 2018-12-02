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

/**
 * Controller for the home page that is shown when the user logges in
 */
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

    /**
     * Frontpage mapping
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){
        this.currUser = getUser();

        //Find all transactions in reverse order by date
        List<Transaction> allTransactions;
        allTransactions = transactionManagementService.findAllReverseOrder(currUser.getUsername());

        //Only show the 4 most recent transactions
        List<Transaction> latestTransactions;
        if (allTransactions.size() > 4){
            latestTransactions = new ArrayList<>(allTransactions.subList(0, 4));
        } else {
            latestTransactions = allTransactions;
        }
        //Add attributes to the model
        model.addAttribute("currUser", currUser);
        model.addAttribute("transactions", latestTransactions);
        return "Index";
    }
}
