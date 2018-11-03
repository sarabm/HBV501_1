package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
@RequestMapping("/demo") // Notice here that the Request Mapping is set at the Class level
public class ViewAccountsController {



    /* Instance Variables
    private ViewAccountsController ViewAccountsManagementService;

    // Dependency Injection
    @Autowired
    public GetViewAccountssController(ViewAccountsManagementService ViewAccountsManagementService) {
        this.ViewAccountsManagementService = ViewAccountsManagementService;
    }*/


    // Notice here that since the class has "/demo", this path is "/demo/page"
    @RequestMapping("/ViewAccounts")
    public String demoPage(){
        return "demo/demo"; // this returns a .jsp file with the path /webapp/WEB-INF/jsp/demo/demo.jsp
    }

}
