/**
 * 
 */
package com.opngo.parking.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opngo.parking.domain.Parking;
import com.opngo.parking.domain.ParkingSession;
import com.opngo.parking.domain.ParkingStatus;
import com.opngo.parking.domain.SessionEndResponse;
import com.opngo.parking.domain.Vehicle;
import com.opngo.parking.services.ParkingSessionService;
import com.opngo.parking.services.VehicleService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author miguel
 *
 */
@Slf4j
@Controller
@RequestMapping("/pms/v1/assets")
public class PMSController {

	@Autowired
	ParkingSessionService sessionService;

	@Autowired
	VehicleService vehicleService;

	private final BigDecimal COST = new BigDecimal(3);

	@PostMapping("/{asset:[\\d]+}/vehicle/{plate}/session")
	@ResponseBody
	public ResponseEntity<SessionEndResponse> stopSession(@RequestBody ParkingStatus park,
			@PathVariable("asset") int assetId, @PathVariable("plate") String plate) {
		ParkingSession parkingSession = sessionService.findByVehicleAndAsset(plate, assetId);
		if (parkingSession == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		parkingSession.setEnd(new Date());
		sessionService.save(parkingSession);
		BigDecimal total = new BigDecimal(
				new Duration(parkingSession.getStart().getTime(), parkingSession.getEnd().getTime())
						.getStandardMinutes()).divide(new BigDecimal(15), RoundingMode.UP).multiply(COST);
		SessionEndResponse resp = new SessionEndResponse(park.getStatus(), total, parkingSession.getStart(),
				parkingSession.getEnd());
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}

	@PostMapping("/{asset:[\\d]+}/sessions")
	@ResponseBody
	public ResponseEntity<ParkingSession> startNewSession(@RequestBody Parking park,
			@PathVariable("asset") int assetId) {
		log.debug("asset: " + assetId);
		Vehicle vehicle = vehicleService.findByPlate(park.getPlate());
		if (vehicle == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		ParkingSession parkingSession = new ParkingSession();
		parkingSession.setVehicle(vehicle);
		parkingSession.setStart(new Date());
		parkingSession.setAsset(assetId);
		sessionService.save(parkingSession);
		return ResponseEntity.status(HttpStatus.OK).body(parkingSession);
	}

}
