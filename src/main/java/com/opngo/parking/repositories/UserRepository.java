/**
 * 
 */
package com.opngo.parking.repositories;

import org.springframework.data.repository.CrudRepository;

import com.opngo.parking.domain.User;

/**
 * @author miguel
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

}
