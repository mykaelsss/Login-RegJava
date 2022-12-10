package com.javastack.spring.authentication.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.javastack.spring.authentication.models.LoginUser;
import com.javastack.spring.authentication.models.User;
import com.javastack.spring.authentication.services.UserService;

@Controller
public class HomeController {
    
     //Add once service is implemented:
     @Autowired
     private UserService userServ;
    
    @GetMapping("/")
    public String index(Model model) {
        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
    		model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    

	@PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
        // TO-DO Later -- call a register method in the service 
        // to do some extra validations and create a new user!
        
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        if(!newUser.getPassword().equals(newUser.getConfirm())) {
    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        	model.addAttribute("newLogin", new LoginUser());
        	return "index.jsp";
    	    }
        
        //Checking if password follow regex format
        if(userServ.isValid(newUser.getPassword()) == false) {
        		result.rejectValue("password", "invalidFormat", "Password must be 8 characters long and contain a special char, uppercase and a number.");
            	model.addAttribute("newLogin", new LoginUser());
            	return "index.jsp";
        }
        
        System.out.println(userServ.isValid(newUser.getPassword()));
        
        //Checking if email is unique
        if(userServ.getUser(newUser.getEmail()) != null) {
        	result.rejectValue("email", "Unique", "Email is already taken.");
        	model.addAttribute("newLogin", new LoginUser());
        	return "index.jsp";
        }
        
        //Checking if user is at least 10
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.YEAR, -10);
        Date date = currentDate.getTime();
        Date userBirthday = newUser.getBirthday();
       
        if(userBirthday.after(date)) {
         	result.rejectValue("birthday", "tooYoung", "You must be at least 10 years old to register.");
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        

        
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        	userServ.register(newUser, result);
        	System.out.println(newUser.getBirthday());
        	session.setAttribute("userId", newUser.getId());
        	System.out.println(newUser.getId());
        	session.setAttribute("userName", newUser.getUserName());
        	System.out.println(newUser.getUserName());
        	return "redirect:/home";
        
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        // Add once service is implemented:
         User user = userServ.login(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        session.setAttribute("userId", user.getId());
        session.setAttribute("userName", user.getUserName());
        System.out.println(user.getId());
        System.out.println(user.getUserName());
        return "redirect:/home";
    }
    
    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
	   	if(session.getAttribute("userId") == null) {
	   		return "redirect:/";
	   	}
    		User user = userServ.getUser((Long) session.getAttribute("userId"));
    		System.out.println(user);
		String userName = (String) session.getAttribute("userName");
		model.addAttribute("userName", userName);
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("userId", userId);
		model.addAttribute("user", user);
		return "home.jsp";

    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    		session.invalidate();
    	return "redirect:/";
    }
}