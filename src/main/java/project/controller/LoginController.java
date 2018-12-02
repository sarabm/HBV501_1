package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.service.UserManagementService;

/**
 * Login controller
 */
@Controller
public class LoginController {

    /**
     * Method that gets the request for login
     * @param model
     * @param error
     * @param logout
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {

        //Check if success
        if (error != null)
            //Add error to the model
            model.addAttribute("errorMsg", "Your username or password is invalid.");

        //Check if the user logs out
        if (logout != null)
            //Add logg out text to model
            model.addAttribute("msg", "You have been logged out successfully.");

        //return the view
        return "login";
    }
}
