/**
 * 
 */
package com.santosh.ms.customer.response;

import com.santosh.ms.customer.entity.Address;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@Data
@NoArgsConstructor
public class AddressResponse {
	
	private String address;

	private String landmark;

	private String city;

	private String state;

	private String country;

	private int pincode;
	
	private boolean defaults;
	
	public AddressResponse(Address add) {
		this.address = add.getAddress();
		this.landmark = add.getLandmark();
		this.city = add.getCity();
		this.state = add.getState();
		this.country = add.getCountry();
		this.pincode = add.getPincode();
		this.defaults = add.isDefaults();
	}
}
