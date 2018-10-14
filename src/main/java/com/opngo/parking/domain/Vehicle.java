/**
 * 
 */
package com.opngo.parking.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * @author miguel
 *
 */
@Data
@Entity
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String plate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@OneToMany
	private List<ParkingSession> session;

}
