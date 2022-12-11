package com.javastack.spring.authentication.services;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.javastack.spring.authentication.models.LoginUser;
import com.javastack.spring.authentication.models.User;
import com.javastack.spring.authentication.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepo;
	private final  String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
	private final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
    // TO-DO: Write register and login methods!
	
	public User getUser(String email) {
		Optional<User> potentialUser = userRepo.findByEmail(email);
		return potentialUser.isPresent() ? potentialUser.get() : null;
	}
	
	public User getUser(Long id) {
		Optional<User> potentialUser = userRepo.findById(id);
		return potentialUser.isPresent() ? potentialUser.get() : null;
	}
    public boolean isValid(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
	

    public User register(User newUser, BindingResult result) {
        // TO-DO: Additional validations!
    	String passwordEntered = newUser.getPassword();
    	
    	
    	if(!passwordEntered.equals(newUser.getConfirm())) {
    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	    
    	}
    	
//    	if(!isValid(passwordEntered)) {
//    		result.rejectValue("password", "Invalid password include 1 special char and number");
//    	}
////    	String emailEntered = newUser.getEmail();
//    	Optional<User> user = userRepo.findByEmail(emailEntered);
//    	
//    	if(user != null) {
//    		result.rejectValue("email", "Email is already Taken");
//    	}
    	else {
    		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    		newUser.setPassword(hashed);
    		userRepo.save(newUser);
    	}
        return null;
    }
    public User login(LoginUser newLogin, BindingResult result) {
        // TO-DO: Additional validations!
    	if(result.hasErrors()) {
    		return null;
    	}
    	User existingUser = getUser(newLogin.getEmail());
    	if(existingUser == null) {
    		result.rejectValue("email", "Unique", "Invalid Login");
    	}
    	if(!BCrypt.checkpw(newLogin.getPassword(), existingUser.getPassword())) {
    		result.rejectValue("password", "Matches", "Invalid Login");
    		return null;
    	}
    	
    	    // Exit the method and go back to the controller 
    	    // to handle the response
    	  
        return existingUser;
    }

    public User findById(Long id) {
    	Optional<User> potentialUser = userRepo.findById(id);
    	if(potentialUser.isPresent()) {
    		return potentialUser.get();
    	}
    	return null;
    }


}
