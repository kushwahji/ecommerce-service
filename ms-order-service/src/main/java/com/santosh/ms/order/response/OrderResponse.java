/**
 * 
 */
package com.santosh.ms.order.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
	private String message;
	private String trackingNumber;
}
