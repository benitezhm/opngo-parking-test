/**
 * 
 */
package com.opngo.parking.services;

import com.opngo.parking.domain.ParkingSession;

/**
 * @author miguel
 *
 */
public interface ParkingSessionService {

	ParkingSession save(ParkingSession session);
	
	ParkingSession findByVehicleAndAsset(String plate, int asset);
}
