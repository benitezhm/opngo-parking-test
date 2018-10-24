/**
 * 
 */
package com.opngo.parking.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.opngo.parking.domain.User;
import com.opngo.parking.domain.Vehicle;

/**
 * @author miguel
 *
 */
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
	
	List<Vehicle> findByUser(User user);
	
	Vehicle findByPlate(String plate);
}
