package com.santosh.ms.fundtransfer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Auther Santosh-Kus Date: 02-01-2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundTransferDto {

	@Schema(name = "fromAccountNumber", type = "number", format = "number", description = "The from-account nubmer value", required = true, example = "301220210734202")
	private long fromAccountNumber;

	@Schema(name = "toAccountNumber", type = "number", format = "number", description = "The to-account nubmer value", required = true, example = "301220210737381")
	private long toAccountNumber;

	@Schema(name = "amount", type = "double", format = "double", description = "The amount value", required = true, example = "100")
	@Min(value = 50, message = "transfer amount should be less than, please try with less amount.")
	private double amount;

	@Schema(name = "remarks", type = "String", format = "String", description = "The remarks value", required = true, example = "Recharge")
	@NotBlank(message = "please enter some remark.")
	private String remarks;

}
