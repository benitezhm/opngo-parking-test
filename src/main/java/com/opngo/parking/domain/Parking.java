/**
 * 
 */
package com.opngo.parking.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author miguel
 *
 */
public class Parking {
	
	private String plate;
	
	public Parking() {}

	@JsonCreator
	public Parking(@JsonProperty String plate) {
		super();
		this.plate = plate;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	
}
