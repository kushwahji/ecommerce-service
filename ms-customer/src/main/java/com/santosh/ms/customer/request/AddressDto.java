/**
 * 
 */
package com.santosh.ms.customer.request;

import javax.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@Data
public class AddressDto {
	
	@Schema(name = "addressLine1", type = "string", format = "String", description = "The addressLine value", required = true, example = "79 Udina Tehsil Bhander")
    @NotBlank
	private String addressLine1;
	
	@Schema(name = "landmark", type = "string", format = "String", description = "The landmark value", required = true, example = "Near By Bhander Road")
    @NotBlank
	private String landmark;

	@Schema(name = "city", type = "string", format = "String", description = "The city value", required = true, example = "Datia")
    @NotBlank
	private String city;

	@Schema(name = "state", type = "string", format = "String", description = "The state value", required = true, example = "Madhya Pradesh")
    @NotBlank
	private String state;

	@Schema(name = "country", type = "string", format = "String", description = "The country value", required = true, example = "India")
    @NotBlank
	private String country;
	
	@Schema(name = "isDefault", type = "boolean", format = "boolean", description = "The default value", required = true, example = "false")
	private boolean defaults;

	@Schema(name = "pincode", type = "number", format = "integer", description = "The pincode value", required = true, example = "400701")
	private int pincode;
}
