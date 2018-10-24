package com.opngo.parking;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.opngo.parking.domain.User;
import com.opngo.parking.domain.Vehicle;
import com.opngo.parking.repositories.UserRepository;
import com.opngo.parking.repositories.VehicleRepository;

@SpringBootApplication
public class ParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}

	/**
	 * Some database initialization
	 * 
	 * @param userRepository
	 * @return
	 */
	@Bean
	ApplicationRunner init(UserRepository userRepository, VehicleRepository vehicleRespository) {
		return args -> {
			User user = new User();
			user.setUsername("customer@info.com");
			user.setPassword("123");
			user.setRoles(new String[] { "USER" });
			userRepository.save(user);
			
			Stream.of("AEB-123", "AEB-456", "AEB-789" ).forEach(plate -> {
				Vehicle vehicle = new Vehicle();
				vehicle.setUser(user);
				vehicle.setName("Sedan ");
				vehicle.setPlate(plate);
				vehicleRespository.save(vehicle);
			});
		};

	}

}
