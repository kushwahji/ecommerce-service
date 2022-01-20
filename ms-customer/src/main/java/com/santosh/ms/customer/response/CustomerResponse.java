/**
 * 
 */
package com.santosh.ms.customer.response;

import java.util.List;
import java.util.stream.Collectors;

import com.santosh.ms.customer.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 * @since 22-01-2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

	private String firstName;

	private String lastName;

	private int age;

	private String email;

	private String phone;

	private List<AddressResponse> address;

	public CustomerResponse(Customer customer) {
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.age = customer.getAge();
		this.email = customer.getEmail();
		this.phone = customer.getPhone();
		this.address = customer.getAddress().stream().map(AddressResponse::new).collect(Collectors.toList());
	}
}
