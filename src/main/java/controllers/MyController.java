package controllers;

import model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.ServiceUser;

import javax.validation.Valid;

/**
 * Created by Paul on 24/07/2017.
 */
@Controller
public class MyController {
    private ServiceUser userService;

    @Autowired(required=true)
    @Qualifier(value="userService")
    public void setUserService(ServiceUser su){
        userService = su;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)// Specified in URL
    public String listUsers(Model model) {
        model.addAttribute("user", new Users());
        model.addAttribute("listUsers", userService.listUsers());
        return "userList";//Specify name of .jsp file here without .jsp at the end
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)// Specified in URL
    public String reg(Model model) {
        model.addAttribute("user", new Users());
        model.addAttribute("listUsers", userService.listUsers());
        return "register";//Specify name of .jsp file here without .jsp at the end
    }

    //For add and update person both
    @RequestMapping(value= "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") @Valid Users u, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }
        else if(u.getId() == 0){
            //new user, add it
            userService.addUser(u);
        }else{
            //existing user, call update
            userService.updateUser(u);
        }

        return "redirect:/users";// Back to previous URL, i.e. users

    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id){

        userService.removeUser(id);
        return "redirect:/users";
    }

    @RequestMapping("/edit/{id}")
    @Transactional // Needed to avoid lazy loader error, as no session will be found
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listUsers", userService.listUsers());
        return "register"; //Specify name of .jsp file here without .jsp at the end
    }

    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Login or Create an Account!");
        return "homepage";//Specify name of .jsp file here without .jsp at the end
    }
}
