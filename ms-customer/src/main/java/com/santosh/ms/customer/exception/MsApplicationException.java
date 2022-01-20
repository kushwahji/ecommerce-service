/**
 * 
 */
package com.santosh.ms.customer.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author santosh.kushwah
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
public class MsApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String errorDetails;
	private final String errorMoreInfo;
}
