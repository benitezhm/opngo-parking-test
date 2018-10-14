/**
 * 
 */
package com.opngo.parking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opngo.parking.services.CustomerService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author miguel
 *
 */
@Slf4j
@Controller
public class HomeController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping({"", "/", "/index"})
	public String getHomePage(Model model) {
		log.debug("Getting Index Page");
		
		model.addAttribute("customers", customerService.getCustomers());
		
		return "index";
	}
}
