package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import project.service.UserManagementService;

/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
@RequestMapping("/UserUpdate") // Notice here that the Request Mapping is set at the Class level
public class UserUpdateController {


    //Instance Variables
    private project.service.UserManagementService UserManagementService;

    //Dependency Injection
    @Autowired
    public UserUpdateController(UserManagementService UserManagementService) {
        this.UserManagementService = UserManagementService;
    }


    // Notice here that since the class has "/demo", this path is "/demo/page"
    @RequestMapping("/UserUpdate")
    public String demoPage(){
        return "demo/demo"; // this returns a .jsp file with the path /webapp/WEB-INF/jsp/demo/transactionNew.jsp
    }


}
