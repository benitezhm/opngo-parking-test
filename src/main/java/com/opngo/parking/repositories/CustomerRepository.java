/**
 * 
 */
package com.opngo.parking.repositories;

import org.springframework.data.repository.CrudRepository;

import com.opngo.parking.domain.Customer;

/**
 * @author miguel
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	

}
