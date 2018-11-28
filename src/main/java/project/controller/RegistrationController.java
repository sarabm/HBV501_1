package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import project.persistence.entities.User;
import project.service.UserManagementService;

import java.util.ArrayList;
import java.util.List;

/**
 * Small controller just to show that you can have multiple controllers
 * in your project
 */
@Controller
public class RegistrationController {


    //Instance Variables
    private UserManagementService userManagementService;

    //Dependency Injection
    @Autowired
    public RegistrationController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String newRegistration(Model model){

        model.addAttribute("newUser", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveRegistration(@ModelAttribute("newUser") User newUser , Model model, RedirectAttributes redirectAttrs){

        List<String> errors = new ArrayList();


        User usernameTaken = userManagementService.findByUsername(newUser.getUsername());

        if (usernameTaken != null) {
            errors.add("The username " + newUser.getUsername() + " is already taken");
        }

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("newUser", newUser);
            return "registration";
        }
        else {
            userManagementService.save(newUser);
            redirectAttrs.addFlashAttribute("msg", "A new user: " + newUser.getUsername() + " has been registered successfully");
            return "redirect:/login";
        }
    }
}
