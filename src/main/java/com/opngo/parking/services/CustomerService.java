/**
 * 
 */
package com.opngo.parking.services;

import java.util.List;

import com.opngo.parking.domain.Customer;

/**
 * @author miguel
 *
 */
public interface CustomerService {
	
	List<Customer> getCustomers();
	
	Customer findById(Long l);

}
