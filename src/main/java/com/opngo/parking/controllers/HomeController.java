/**
 * 
 */
package com.opngo.parking.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.opngo.parking.domain.User;
import com.opngo.parking.services.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author miguel
 *
 */
@Slf4j
@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@RequestMapping({"", "/index"})
	public String getHomePage(Model model) {
		log.debug("Getting Index Page");
		List<User> users = userService.getUsers();
		model.addAttribute("users", users);

		return "index";
	}

	@RequestMapping({"/login"})
	public String login() {
		return "login";
	}

	// Login form with error
	@RequestMapping({"/login-error"})
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

	@GetMapping("/registration")
	public String register(WebRequest request, Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register";

	}
	
	@PostMapping("/registration")
	public String register(@ModelAttribute("user") @Valid User user, BindingResult result) {
		User existing = userService.findByUsername(user.getUsername());
        if (existing != null){
            result.rejectValue("username", null, "There is already an account registered with that email");
        }
        
        if (!user.getPassword().equals(user.getMatchingPassword())) {
        	result.rejectValue("password", null, "The passwords does not match");
        }

        if (result.hasErrors()){
            return "register";
        }

        userService.save(user);
        
        return "redirect:/login";
	}
	
}
