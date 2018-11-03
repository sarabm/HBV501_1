package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.PostitNote;

import javax.transaction.Transaction;

/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
@RequestMapping("/demo") // Notice here that the Request Mapping is set at the Class level
public class TransactionController {



    /* Instance Variables
    private TransactionController TransactionManagementService;

    // Dependency Injection
    @Autowired
    public GetTransactionsController(TransactionManagementService TransactionManagementService) {
        this.TransactionManagementService = TransactionManagementService;
    }*/


    // Notice here that since the class has "/demo", this path is "/demo/page"
    @RequestMapping("/transaction")
    public String demoPage(){
        return "demo/demo"; // this returns a .jsp file with the path /webapp/WEB-INF/jsp/demo/demo.jsp
    }

}
