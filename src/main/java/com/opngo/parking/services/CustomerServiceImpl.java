/**
 * 
 */
package com.opngo.parking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opngo.parking.domain.Customer;
import com.opngo.parking.repositories.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author miguel
 *
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	/* 
	 * return the list of customers 
	 */
	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().iterator().forEachRemaining(customers::add);
		return customers;
	}

	/* 
	 * find a customer by its id
	 * throws exception when customer does not exist
	 */
	@Override
	public Customer findById(Long l) {
		Optional<Customer> customerOptional = customerRepository.findById(l);
		
		if (!customerOptional.isPresent()) {
			throw new RuntimeException("Customer not found!");
		}
		
		return customerOptional.get();
	}

}
