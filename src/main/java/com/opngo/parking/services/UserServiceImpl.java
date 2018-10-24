/**
 * 
 */
package com.opngo.parking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.opngo.parking.domain.User;
import com.opngo.parking.repositories.UserRepository;

/**
 * @author miguel
 *
 */
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		UserBuilder builder = null;
		if (user == null) {
			throw new UsernameNotFoundException(username);
		} else {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
			builder.roles(user.getRoles());
		}
		return builder.build();
	}

	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(users::add);
		return users;
	}

	@Override
	public User save(User user) {
		user.setRoles(new String[] {"USER"});
		return userRepository.save(user);
	}

	@Override
	public User findById(Long l) {
		Optional<User> user = userRepository.findById(l);
		return user.isPresent() ? user.get() : null;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
