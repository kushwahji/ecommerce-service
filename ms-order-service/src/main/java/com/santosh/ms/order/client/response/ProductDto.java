/**
 * 
 */
package com.santosh.ms.order.client.response;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author santosh.kushwah
 *
 */
@Data
public class ProductDto {
	private long productId;
	private String name;
	private String sku;
	private String description;
	private BigDecimal price;
	private String imageUrl;
}
