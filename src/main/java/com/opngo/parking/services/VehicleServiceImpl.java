package com.opngo.parking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opngo.parking.domain.User;
import com.opngo.parking.domain.Vehicle;
import com.opngo.parking.repositories.UserRepository;
import com.opngo.parking.repositories.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	VehicleRepository vehicleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<Vehicle> getVehiclesByUser(String username) {
		User user = userRepository.findByUsername(username);
		List<Vehicle> vehicles = new ArrayList<>();
		vehicleRepository.findByUser(user).iterator().forEachRemaining(vehicles::add);
		return vehicles;
		
	}

	@Override
	public Vehicle findById(Long l) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(l);
		return vehicle.isPresent() ? vehicle.get() : null;
	}

	@Override
	public Vehicle save(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	@Override
	public Vehicle findByPlate(String plate) {
		return vehicleRepository.findByPlate(plate);
	}

}
