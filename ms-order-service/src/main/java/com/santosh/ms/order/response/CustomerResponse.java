/**
 * 
 */
package com.santosh.ms.order.response;

import java.util.List;
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


}
