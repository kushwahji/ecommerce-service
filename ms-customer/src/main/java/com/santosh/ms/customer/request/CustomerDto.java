package com.santosh.ms.customer.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto {

	@Schema(name = "firstName", type = "String", format = "String", description = "The firstname value", required = true, example = "Santosh")
    @NotBlank(message = "{msg.firstname}")
    private String firstName;

	@Schema(name = "lastName", type = "String", format = "String", description = "The lastname value", required = true, example = "Kushwah")
    @NotBlank(message = "{msg.lastname}")
    private String lastName;

	@Schema(name = "age", type = "number", format = "number", description = "The age value", required = true, example = "30")
    @Min(value = 5,message = "msg.age")
    @Max(value = 85,message = "{msg.age}")
    private int age;

	@Schema(name = "email", type = "String", format = "String", description = "The email value", required = true, example = "santosh.kushwah@hcl.com")
    @Email(message = "{msg.email}")
    private String email;

	@Schema(name = "phone", type = "String", format = "String", description = "The phone value", required = true, example = "9752572357")
    @NotBlank(message = "{msg.phone}")
	@Size(min = 10,max = 10,message = "{msg.phone}")
    private String phone;
	
	@Valid
	@NotNull
	List<AddressDto> address;

}
