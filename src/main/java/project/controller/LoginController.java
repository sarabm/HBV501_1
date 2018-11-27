package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.service.UserManagementService;

/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
public class LoginController {


    //Instance Variables
    private project.service.UserManagementService UserManagementService;

    //Dependency Injection
    @Autowired
    public LoginController(UserManagementService UserManagementService) {
        this.UserManagementService = UserManagementService;
    }



    // Notice here that since the class has "/demo", this path is "/demo/page"
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username or password is invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }

}
