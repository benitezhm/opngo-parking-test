/**
 * 
 */
package com.opngo.parking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String register(WebRequest request, Model model) {
		User customer = new User();
		model.addAttribute("customer", customer);
		return "register";

	}
}
