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
public class RegistrationController {


    //Instance Variables
    private project.service.UserManagementService UserManagementService;

    //Dependency Injection
    @Autowired
    public RegistrationController(UserManagementService UserManagementService) {
        this.UserManagementService = UserManagementService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String newRegistration(Model model){
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveRegistration(Model model){
        return "registration";
    }


}
