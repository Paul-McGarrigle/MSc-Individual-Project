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
// This is the Controller Class, it is essentially the backbone of a MVC WebApp as it links the
// Models/Entities/POJOs to the Views/JSP/Webpages
// Request mapping ensures the Method is linked to a URL, when the URL is sent for in the browser the Method is invoked
@Controller
public class MyController {
    // Variables
    private ServiceUser userService;
    private String userWall = "";
    private boolean friendsWall;
    private String url;

    @Autowired(required=true)
    @Qualifier(value="userService")
    public void setUserService(ServiceUser su){
        userService = su;
    }

    // This Method returns a List of Users the model.addatribute adds information to the view to be interperted
    // by the JSP Spring based Tags
    @RequestMapping(value = "/users", method = RequestMethod.GET)// Specified in URL
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.listUsers());
        return "userList";//Specify name of .jsp file here without .jsp at the end
    }

    // The next few Methods deal with Registering & editing user accounts they were inspired from
    // http://www.journaldev.com/2668/spring-validation-example-mvc-validator
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

    // This Method removes a user record and all subsequent records
    @RequestMapping("/remove/{username}")
    public String removeUser(@PathVariable("username") String username){

        userService.removeUser(username);
        return "redirect:/admin";
    }

    // The next few Methods deal with adding and updating Friend Requests
    @RequestMapping("/addFriend/{username}")
    public String addFriend(@PathVariable("username") String username, ModelMap model){

        // The following code will retrieve the name of the currently logged in user,
        // this code is repeated regularly throughout the Controller Class
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username
        model.addAttribute("username", currentUser);

        // This handles the error thrown if a user tries to friend request a user who they are already friends with
        // because this will cause a duplicate error on a Unique Key in the Database, a Unique key was used
        // to stop duplicate relationships between users
        // The design was inspired by http://www.codedodle.com/2014/12/social-network-friends-database.html
        try {
            userService.addFriend(currentUser, username);
        } catch(Exception e){
            System.out.print("Already Friend " + username);
            model.addAttribute("friendName", username);
        }
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

    @RequestMapping("/unBlockUser/{username}")
    @Transactional // Needed to avoid lazy loader error, as no session will be found
    public String unBlockUser(@PathVariable("username") String username, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username
        model.addAttribute("username", currentUser);

        // These attributes are used in the for loops on outstandingRequests.jsp
        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("listUsers", userService.listFriendRequests(currentUser));


        userService.unBlockUser(currentUser, username);
        return "redirect:/blockList"; //Specify name of .jsp file here without .jsp at the end
    }

    // The next few Methods deal with accessing and posting to users personal and friends walls and activity feeds
    @RequestMapping(value = "/wall", method = RequestMethod.GET)// Specified in URL
    @Transactional
    public String userWall(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username
        model.addAttribute("user", new Wall());
        model.addAttribute("listUsers", userService.showUserWall(currentUser));

        // Get username of the Wall Owner, i.e. the user whos wall it is
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

        // Determine if it is a personal wall or friend wall as this will dictate which URL is redirected to
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

    // The next few Methods deal with listing the users in various different statuses of friendship
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

    @RequestMapping(value = "/friendList", method = RequestMethod.GET)// Specified in URL
    @Transactional
    public String listFriends(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username
        model.addAttribute("username", currentUser);
        model.addAttribute("x", new Friendship());
        model.addAttribute("listUsers", userService.listFriends(currentUser));
        return "friendList";//Specify name of .jsp file here without .jsp at the end
    }

    @RequestMapping(value = "/blockList", method = RequestMethod.GET)// Specified in URL
    @Transactional
    public String listBlock(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username
        model.addAttribute("username", currentUser);
        model.addAttribute("x", new Friendship());
        model.addAttribute("listUsers", userService.listBlock(currentUser));
        return "blockList";//Specify name of .jsp file here without .jsp at the end
    }

    // This Method deals with editing users supplied information
    @RequestMapping("/edit/{username}")
    @Transactional // Needed to avoid lazy loader error, as no session will be found
    public String editUser(@PathVariable("username") String username, Model model){
        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("listUsers", userService.listUsers());
        return "register"; //Specify name of .jsp file here without .jsp at the end
    }

    // This Method redirects to a default page if a URL is not found
    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Login or Create an Account!");
        return "homepage";//Specify name of .jsp file here without .jsp at the end
    }

    // This Method links to the users Profile Page, it is also the redirected page when a login attempt has been successful
    @RequestMapping(value = { "/welcome**" }, method = RequestMethod.GET)
    @Transactional
    public ModelAndView defaultPage(ModelMap m) {
        // Get logged in user information
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName(); //get logged in username
        m.addAttribute("username", currentUser);

        // List Activity Feed
        User u = userService.findByUserName(currentUser);
        m.addAttribute("user", new Wall());
        m.addAttribute("listUsers", userService.activityFeed(currentUser));

        // Display user information
        userWall = u.getUsername();
        model.addObject("name", u.getUsername());
        model.addObject("email", u.getEmail());
        model.addObject("age", u.getAge());
        model.addObject("phone", u.getPhone());
        model.addObject("country", u.getCountry());
        model.setViewName("profile");
        return model;

    }

    // The next few Methods deal with logging in a user, they were modified and taken directly from
    // http://www.mkyong.com/spring-security/spring-security-hibernate-annotation-example/
    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage(Model m) {

        ModelAndView model = new ModelAndView();
        model.addObject("message", "This page is for Admin Accounts only!");
        m.addAttribute("user", new User());
        m.addAttribute("listUsers", userService.listUsers());
        model.setViewName("admin");

        return model;
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
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

    // This Method customises the error message
    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username and password!";
        }

        return error;
    }

    // This Method redirects to the 403 access denied page when authentication fails
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // Checks if user is logged in
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }

    // The next two Methods deal with returning any user accounts that match the search string
    // specified in the search bar on the profile page
    @RequestMapping(value = "/searchResults", method = RequestMethod.GET)// Specified in URL
    @Transactional
    public String results(Model model) {
        return "searchResults";//Specify name of .jsp file here without .jsp at the end
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)// Specified in URL
    @Transactional
    public String listResults(@RequestParam("search") String search,Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.searchUsers(search));
        System.out.println(search);
        return "userList";//Specify name of .jsp file here without .jsp at the end
    }

}
