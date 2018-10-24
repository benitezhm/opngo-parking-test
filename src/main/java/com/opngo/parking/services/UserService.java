/**
 * 
 */
package com.opngo.parking.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.opngo.parking.domain.User;

/**
 * @author miguel
 *
 */
public interface UserService {
	
	List<User> getUsers();
	
	User findById(Long l);
	
	User findByUsername(String username);
	
	User save(User user);

}
