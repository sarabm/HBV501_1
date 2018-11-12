package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.service.TransactionManagementService;

/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
public class GetTransactionsController {


    //Instance Variables
    private project.service.TransactionManagementService transactionManagementService;

    //Dependency Injection
    @Autowired
    public GetTransactionsController(TransactionManagementService transactionManagementService) {
        this.transactionManagementService = transactionManagementService;
    }


    // Notice here that since the class has "/demo", this path is "/demo/page"
    @RequestMapping("/transactions")
    public String allTransactions(Model model){
        model.addAttribute("transactions", transactionManagementService.findAll());

        return "transaction/transactionList";

    }


}
