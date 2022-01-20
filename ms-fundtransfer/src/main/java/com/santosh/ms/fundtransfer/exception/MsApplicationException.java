/**
 * 
 */
package com.santosh.ms.fundtransfer.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class MsApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errorDetails;
	private String errorMoreInfo;
}
