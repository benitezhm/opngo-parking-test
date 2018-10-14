package com.opngo.parking;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.opngo.parking.domain.User;
import com.opngo.parking.repositories.UserRepository;

@SpringBootApplication
public class ParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}
	
	/**
	 * Some database initialization
	 * @param repository
	 * @return
	 */
	@Bean
	ApplicationRunner init(UserRepository repository) {
		return args -> {
			Stream.of("customer_1@example.com", "customer_2@example.com", "customer_3@example.com", 
					"customer_4@example.com", "customer_5@example.com",
					"customer_6@example.com", "customer_7@example.com", "customer_8@example.com", "customer_9@example.com").forEach(email -> {
						User user = new User();
						user.setUsername(email);
						user.setPassword("123");
						user.setRoles(new String[] {"USER"});
						repository.save(user);
					});
		};
	}
	
}
