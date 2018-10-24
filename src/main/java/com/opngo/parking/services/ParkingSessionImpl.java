/**
 * 
 */
package com.opngo.parking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opngo.parking.domain.ParkingSession;
import com.opngo.parking.domain.Vehicle;
import com.opngo.parking.repositories.ParkingSessionRepository;
import com.opngo.parking.repositories.VehicleRepository;

/**
 * @author miguel
 *
 */
@Service
public class ParkingSessionImpl implements ParkingSessionService {
	
	@Autowired
	ParkingSessionRepository sessionRepository;
	
	@Autowired
	VehicleRepository vehicleRepository;

	@Override
	public ParkingSession save(ParkingSession session) {
		return sessionRepository.save(session);
	}

	@Override
	public ParkingSession findByVehicleAndAsset(String plate, int asset) {
		Vehicle vehicle = vehicleRepository.findByPlate(plate);
		return sessionRepository.findByVehicleAndAsset(vehicle, asset);
	}

}
