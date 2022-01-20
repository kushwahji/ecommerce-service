/**
 * 
 */
package com.santosh.ms.order.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;


	@Column(name = "product_id")
	private Long productId;
	
	@Column(name = "quantity")
	private int quantity;

	@Column(name = "product_price")
	private BigDecimal productPrice;

	@ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

	public OrderItem(Integer quantity, long productId, BigDecimal subTotalForItem) {
		this.quantity = quantity;
		this.productId = productId;
		this.productPrice = subTotalForItem;
	}
}
