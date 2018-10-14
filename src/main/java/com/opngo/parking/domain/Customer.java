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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

/**
 * @author miguel
 *
 */
@Data
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String password;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Wallet wallet;
	
	@OneToMany
	private List<Vehicle> vehicles;
	
	@OneToMany
	private List<Invoice> invoice;

}
