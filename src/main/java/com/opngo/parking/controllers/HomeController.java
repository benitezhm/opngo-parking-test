/**
 * 
 */
package com.opngo.parking.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.opngo.parking.domain.User;
import com.opngo.parking.domain.Vehicle;
import com.opngo.parking.repositories.UserRepository;
import com.opngo.parking.services.UserService;
import com.opngo.parking.services.VehicleService;

/**
 * @author miguel
 *
 */
@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private VehicleService vehicleService;

	@RequestMapping({"", "/index"})
	public String getHomePage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		List<Vehicle> vehicles = vehicleService.getVehiclesByUser(userDetails.getUsername());
		model.addAttribute("vehicles", vehicles);

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
	public String register(Model model) {
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
	
	@GetMapping("/newVehicle")
	public String newVehicle(WebRequest request, Model model) {
		Vehicle vehicle = new Vehicle();
		model.addAttribute("vehicle", vehicle);
		return "newVehicle";
	}
	
	@PostMapping("/newVehicle")
	public String newVehicle(@ModelAttribute("vehicle") @Valid Vehicle vehicle, BindingResult result) {
		Vehicle existing = vehicleService.findByPlate(vehicle.getPlate());
		if (existing != null) {
			result.rejectValue("plate", null, "There is already a vehicle with that plate");
		}
		
		if (result.hasErrors()) {
			return "newVehicle";
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails user = (UserDetails) authentication.getPrincipal();
		vehicle.setUser(userService.findByUsername(user.getUsername()));
		vehicleService.save(vehicle);
		
		return "redirect:/index";
	}
	
}
