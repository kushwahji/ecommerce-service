/**
 * 
 */
package com.santosh.ms.order.response;

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

}
