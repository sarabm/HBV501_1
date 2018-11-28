package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.persistence.entities.Account;
import project.persistence.entities.User;
import project.service.AccountManagementService;
import project.service.UserManagementService;

import java.util.ArrayList;
import java.util.List;


/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
@RequestMapping("/account") // Notice here that the Request Mapping is set at the Class level
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

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allAccounts(Model model) {
        this.currUser = getUser();

        // model.addAttribute("friendUserName", "");
        model.addAttribute("currUser", currUser);
        model.addAttribute("accounts", accountManagementService.findByUsername(currUser.getUsername()));
        return "/account/accountList";
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public String allAccountsAddFriend(Model model, @RequestParam String friendUserName) {
        this.currUser = getUser();

        if(friendUserName != null && !friendUserName.isEmpty()){
            User friend = userManagementService.findByUsername(friendUserName);
            if (friend != null){
                if (currUser.getFriendlist() == null){
                    currUser.setFriendlist(new ArrayList<User>());
                }
                if (!currUser.getFriendlist().contains(friend)) { // Ef þeir eru vinir do nothing
                    userManagementService.addFriend(currUser, friend);
                    Account account = new Account();
                    account.setUsers(currUser.getUsername(),friend.getUsername());
                    account.setNetBalance(0.0);
                    accountManagementService.save(account);
                    model.addAttribute("msg", friend.getUsername() + " was succesfully added to your friends");
                } else {
                    model.addAttribute("msg", friend.getUsername() + " is already your friend");
                }
            } else {
                model.addAttribute("msg", "No user found with username :" + friendUserName);
            }
        }

        model.addAttribute("currUser", currUser);
        model.addAttribute("accounts", accountManagementService.findByUsername(currUser.getUsername()));
        return "/account/accountList";
    }

    @RequestMapping(value = "/{accountID}", method = RequestMethod.GET)
    public String transactionView(@PathVariable String accountID, Model model){
        this.currUser = getUser();
        Long id = Long.parseLong(accountID);
        Account account = accountManagementService.findOne(id);
        Boolean isUser1 = currUser.getUsername().equals(account.getUser1());
        model.addAttribute("account", account);
        model.addAttribute("isUser1", isUser1);

        return "/account/accountView";
    }

}
