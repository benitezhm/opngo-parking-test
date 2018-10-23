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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author miguel
 *
 */
@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotEmpty
	private String username;
	
	@NotNull
	@NotEmpty
	private String password;
	private String matchingPassword;
	
	private String roles[];
	
	@OneToOne(fetch = FetchType.EAGER)
	private Wallet wallet;
	
	@OneToMany
	private List<Vehicle> vehicles;
	
	@OneToMany
	private List<Invoice> invoice;

}
