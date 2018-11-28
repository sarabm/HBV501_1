package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import project.persistence.entities.User;
import project.service.AccountManagementService;
import project.service.UserManagementService;


/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
@RequestMapping("/accounts") // Notice here that the Request Mapping is set at the Class level
public class AccountsController {

    //Instance Variables
    private UserManagementService userManagementService;
    private AccountManagementService accountManagementService;

    private User currUser;

    // Getting current user  ---
    private User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userManagementService.findByUsername(userDetails.getUsername());
    }
    //Dependency Injection
    @Autowired
    public AccountsController(UserManagementService userManagementService, AccountManagementService accountManagementService) {
        this.userManagementService = userManagementService;
        this.accountManagementService = accountManagementService;
    }

    // Notice here that since the class has "/demo", this path is "/demo/page"
    @RequestMapping(value = "/all")
    public String allAccounts(Model model) {
        this.currUser = getUser();

        model.addAttribute("currUser", currUser);
        model.addAttribute("accounts", accountManagementService.findByUser(currUser));
        return "/Account/accountList";
    }

}
