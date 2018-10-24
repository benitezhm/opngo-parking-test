/**
 * 
 */
package com.opngo.parking.domain;

/**
 * @author miguel
 *
 */
public class ParkingStatus {
	
	private String status;
	
	public ParkingStatus() {}

	public ParkingStatus(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
