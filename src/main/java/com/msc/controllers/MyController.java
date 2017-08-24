package com.msc.controllers;

import com.msc.model.Friendship;
import com.msc.model.User;
import com.msc.model.UserRole;
import com.msc.model.Wall;
import com.msc.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Paul on 24/07/2017.
 */
@Controller
public class MyController {
    private ServiceUser userService;
    private String userWall = "";
    private boolean friendsWall;
    private String url;


    @Autowired(required=true)
    @Qualifier(value="userService")
    public void setUserService(ServiceUser su){
        userService = su;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)// Specified in URL
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.listUsers());
        System.out.println("Controller!!!!!!!!!!!!!!!!!");
        return "userList";//Specify name of .jsp file here without .jsp at the end
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)// Specified in URL
    public String reg(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userRole", new UserRole());
        model.addAttribute("listUsers", userService.listUsers());
        return "register";//Specify name of .jsp file here without .jsp at the end
    }

    //For add and update person both, BindingResults must follow the object it is used with, i.e. User
    @RequestMapping(value= "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") @Valid User u,
                          BindingResult bindingResult,
                          @ModelAttribute("userRole") @Valid UserRole ur){
        if(bindingResult.hasErrors()){
            return "register";
        }
        else if(u.getId() == 0){
            //new user, add it
            try {
                userService.addUser(u, ur);
            } catch (Exception e) {
                return "fail";
            }
        }else{
            //existing user, call update
            userService.updateUser(u);
        }

        return "redirect:/users";// Back to previous URL, i.e. users

    }

    @RequestMapping("/remove/{username}")
    public String removeUser(@PathVariable("username") String username){

        userService.removeUser(username);
        return "redirect:/users";
    }

    @RequestMapping("/addFriend/{username}")
    public String addFriend(@PathVariable("username") String username, ModelMap model){

        //userService.removeUser(username);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username
        model.addAttribute("username", currentUser);

        userService.addFriend(currentUser, username);
        return "redirect:/users";
    }

    @RequestMapping("/acceptFriendRequest/{username}")
    @Transactional // Needed to avoid lazy loader error, as no session will be found
    public String acceptFriendRequest(@PathVariable("username") String username, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username
        model.addAttribute("username", currentUser);

        // These attributes are used in the for loops on outstandingRequests.jsp
        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("listUsers", userService.listFriendRequests(currentUser));


        userService.acceptFriendRequest(currentUser, username);
        return "redirect:/outstandingRequests"; //Specify name of .jsp file here without .jsp at the end
    }

    @RequestMapping("/declineFriendRequest/{username}")
    @Transactional // Needed to avoid lazy loader error, as no session will be found
    public String declineFriendRequest(@PathVariable("username") String username, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username
        model.addAttribute("username", currentUser);

        // These attributes are used in the for loops on outstandingRequests.jsp
        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("listUsers", userService.listFriendRequests(currentUser));


        userService.declineFriendRequest(currentUser, username);
        return "redirect:/outstandingRequests"; //Specify name of .jsp file here without .jsp at the end
    }

    @RequestMapping("/blockUser/{username}")
    @Transactional // Needed to avoid lazy loader error, as no session will be found
    public String blockUser(@PathVariable("username") String username, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username
        model.addAttribute("username", currentUser);

        // These attributes are used in the for loops on outstandingRequests.jsp
        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("listUsers", userService.listFriendRequests(currentUser));


        userService.blockUser(currentUser, username);
        return "redirect:/outstandingRequests"; //Specify name of .jsp file here without .jsp at the end
    }

    @RequestMapping(value = "/wall", method = RequestMethod.GET)// Specified in URL
    @Transactional
    public String userWall(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username
        model.addAttribute("user", new Wall());
        model.addAttribute("listUsers", userService.showUserWall(currentUser));
        for(Wall w: userService.showUserWall(currentUser)){
            userWall = w.getWallOwner().getUsername();
        }
        return "userWall";//Specify name of .jsp file here without .jsp at the end
    }

    @RequestMapping(value = "/addComment", method=RequestMethod.POST)
    @Transactional
    public String addComment(@RequestParam("comment") String comment, ModelMap model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username
        model.addAttribute("username", currentUser);
        userService.addComment(currentUser, userWall, comment);
        if(friendsWall){
            url = "redirect:/wall/"+userWall;
        } else {
            url = "redirect:/wall";
        }
        return url;
    }

    @RequestMapping(value = "/wall/{username}", method = RequestMethod.GET)// Specified in URL
    @Transactional
    public String friendWall(@PathVariable("username") String username,Model model) {
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username*/
        model.addAttribute("user", new Wall());
        model.addAttribute("listUsers", userService.showUserWall(username));
        for(Wall w: userService.showUserWall(username)){
            userWall = w.getWallOwner().getUsername();
        }
        friendsWall = true;
        return "userWall";//Specify name of .jsp file here without .jsp at the end
    }

    /*@RequestMapping(value = "/addCommentFriend", method=RequestMethod.POST)
    @Transactional
    public String addCommentFriend(@RequestParam("comment") String comment, ModelMap model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username
        model.addAttribute("username", currentUser);
        userService.addComment(currentUser, userWall, comment);
        String s = "redirect:/wall/"+userWall;
        System.out.println(s);
        return s;
    }*/

    @RequestMapping(value = "/outstandingRequests", method = RequestMethod.GET)// Specified in URL
    @Transactional
    public String listFriendRequests(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username
        model.addAttribute("username", currentUser);
        model.addAttribute("x", new Friendship());
        model.addAttribute("listUsers", userService.listFriendRequests(currentUser));
        return "outstandingRequests";//Specify name of .jsp file here without .jsp at the end
    }

    @RequestMapping("/edit/{username}")
    @Transactional // Needed to avoid lazy loader error, as no session will be found
    public String editUser(@PathVariable("username") String username, Model model){
        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("listUsers", userService.listUsers());
        return "register"; //Specify name of .jsp file here without .jsp at the end
    }

    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Login or Create an Account!");
        return "homepage";//Specify name of .jsp file here without .jsp at the end
    }

    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
    public ModelAndView defaultPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security + Hibernate Example");
        model.addObject("message", "This is default page!");
        model.setViewName("hello");
        return model;

    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security + Hibernate Example");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.setViewName("admin");

        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    // customize the error message
    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password! Locked";
        }

        return error;
    }

    // for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }
}
