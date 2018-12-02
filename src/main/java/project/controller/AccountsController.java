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
import java.util.Collections;
import java.util.List;


/**
 * Accounts controller
 */
@Controller
@RequestMapping("/account")
public class AccountsController {

    //Instance Variables
    private UserManagementService userManagementService;
    private AccountManagementService accountManagementService;
    private TransactionManagementService transactionManagementService;

    private User currUser;

    // Getting current user
    private User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userManagementService.findByUsername(userDetails.getUsername());
    }

    //Dependency Injection
    @Autowired
    public AccountsController(UserManagementService userManagementService,
                              AccountManagementService accountManagementService,
                              TransactionManagementService transactionManagementService) {
        this.userManagementService = userManagementService;
        this.accountManagementService = accountManagementService;
        this.transactionManagementService = transactionManagementService;
    }

    /**
     * Get methood for showing all accounts
     * @param model
     * @return a list
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allAccounts(Model model) {
        this.currUser = getUser();

        model.addAttribute("currUser", currUser);
        model.addAttribute("accounts", accountManagementService.findByUsername(currUser.getUsername()));

        return "/account/accountList";
    }

    /**
     * Posts a list of all the users accounts
     * @param model
     * @param friendUserName
     * @return
     */
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


    /**
     * Gets the view for the selected accound
     * @param accountID
     * @param model
     * @return
     */
    @RequestMapping(value = "/{accountID}", method = RequestMethod.GET)
    public String transactionView(@PathVariable String accountID, Model model){
        this.currUser = getUser();
        Long id = Long.parseLong(accountID);

        Account account = accountManagementService.findOne(id);
        Boolean isUser1 = currUser.getUsername().equals(account.getUser1());
        List <Transaction> transactions = account.getTransactionList();
        transactions.sort((t1, t2) -> t1.getDate().compareTo(t2.getDate()));
        Collections.reverse(transactions);

        model.addAttribute("account", account);
        model.addAttribute("transactions", transactions);
        model.addAttribute("isUser1", isUser1);

        return "/account/accountView";
    }


    @RequestMapping(value = "/{accountID}/payup", method = RequestMethod.GET)
    public String getPayUp(@PathVariable String accountID, Model model){
        this.currUser = getUser();
        Long id = Long.parseLong(accountID);
        Account account = accountManagementService.findOne(id);
        Boolean isUser1 = currUser.getUsername().equals(account.getUser1());

        String friendName = account.getUser2();
        int prefix = -1;
        if(isUser1) {
            friendName = account.getUser1();
            prefix = 1;
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(prefix*account.getNetBalance());
        List <String> splitInfo = new ArrayList<>();
        splitInfo.add(friendName);
        transaction.setAccount(account);
        transaction.setDescr("_____Pay Up_____");

        model.addAttribute("friend", friendName);
        model.addAttribute("transaction", transaction);

        return "/account/payUp";
    }

    @RequestMapping(value = "/{accountID}/payup", method = RequestMethod.POST)
    public String savePayUp(@PathVariable String accountID, @ModelAttribute("transaction") Transaction transaction, Model model){
        this.currUser = getUser();
        Double ammount = transaction.getAmount();

        Long id = Long.parseLong(accountID);
        Account account = accountManagementService.findOne(id);
        Boolean isUser1 = currUser.getUsername() == account.getUser1();

        String friendName = account.getUser1();
        int prefix = -1;
        if(isUser1) {
            friendName = account.getUser2();
            prefix = 1;
        }

        List <String> splitInfo = new ArrayList<>();
        splitInfo.add(friendName);

        transaction.setAccount(account);
        transaction.setDescr("_____Pay Up_____");
        transaction.setAmount(prefix*account.getNetBalance());

        accountManagementService.updateBalance(prefix*account.getNetBalance(),account);
        transactionManagementService.save(transaction);


        return "redirect:/transaction/all";
    }

}
