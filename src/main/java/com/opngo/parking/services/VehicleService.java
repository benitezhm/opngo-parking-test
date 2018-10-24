/**
 * 
 */
package com.opngo.parking.services;

import java.util.List;

import com.opngo.parking.domain.Vehicle;

/**
 * @author miguel
 *
 */
public interface VehicleService {
	List<Vehicle> getVehiclesByUser(String username);
	
	Vehicle findById(Long l);
	
	Vehicle save(Vehicle user);
	
	Vehicle findByPlate(String plate);
}
