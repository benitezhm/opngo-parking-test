/**
 * 
 */
package com.opngo.parking.repositories;

import org.springframework.data.repository.CrudRepository;

import com.opngo.parking.domain.ParkingSession;
import com.opngo.parking.domain.Vehicle;

/**
 * @author miguel
 *
 */
public interface ParkingSessionRepository extends CrudRepository<ParkingSession, Long> {

	ParkingSession findByVehicleAndAsset(Vehicle vehicle, int asset);
}
