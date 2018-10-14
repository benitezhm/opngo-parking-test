/**
 * 
 */
package com.opngo.parking.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

/**
 * @author miguel
 *
 */
@Data
@Entity
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal price;
	private Boolean isSent;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Customer customer;
	
	@OneToOne(fetch = FetchType.EAGER)
	private ParkingSession session;
}
