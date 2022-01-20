package com.santosh.ms.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRequestDto {

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

	@Schema(name = "phone", type = "String", format = "String", description = "The phone value", required = true, example = "+91-9752572357")
    @NotBlank(message = "{msg.phone}")
    private String phone;

	@Schema(name = "aadhaar", type = "String", format = "String", description = "The aadhaar value", required = true, example = "987898789876")
    @Pattern(regexp = "^[2-9]{1}[0-9]{11}",message = "{msg.aadhaar}")
    private String aadhaar;
}
