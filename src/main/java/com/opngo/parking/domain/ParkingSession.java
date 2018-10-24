/**
 * 
 */
package com.opngo.parking.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * @author miguel
 *
 */
@Data
@Entity
public class ParkingSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;
	
	private int asset;
	
	@ManyToOne
	private Vehicle vehicle;

	@OneToOne(fetch = FetchType.EAGER)
	private Invoice invoice;
	
}
