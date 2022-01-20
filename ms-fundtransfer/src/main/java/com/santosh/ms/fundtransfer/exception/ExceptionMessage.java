/**
 * 
 */
package com.santosh.ms.fundtransfer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 * @since 16-01-2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {
	private String errorDetail;
	private Object errorMoreInfo;
}
