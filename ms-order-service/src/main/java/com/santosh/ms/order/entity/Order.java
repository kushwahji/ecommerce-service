/**
 * 
 */
package com.santosh.ms.order.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.santosh.ms.order.helper.Helper;
import com.santosh.ms.order.request.OrderItemDto;
import com.santosh.ms.order.response.AddressResponse;
import com.santosh.ms.order.response.CustomerResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 * @since 15-0-2022
 */
@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "phone")
	private String phone;

	@Column(name = "customer_name")
	private String name;

	@Column(name = "accountNumber")
	private long accountNumber;

	@Column(name = "shipping_address")
	private String address;

	@Column(name = "total_amount")
	private BigDecimal total;

	@Column(name = "tracking_number")
	private String trackingNumber;

	@Column(name = "status")
	private String status;

	@Column(name = "payment_status")
	private String payStatus;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "order_date")
	private LocalDateTime orderDate;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "last_updated")
	private LocalDateTime lastUpdated;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "id")

	private List<OrderItem> items;

	@PrePersist
	public void prePersist() {
		this.orderDate = LocalDateTime.now();
	}

	@PostPersist
	public void postPersist() {
		this.lastUpdated = LocalDateTime.now();
	}

	public Order(CustomerResponse customer, long accountNumber, Collection<OrderItemDto> carts) {
		this.phone = customer.getPhone();
		this.name = customer.getFirstName().concat(" " + customer.getLastName());
		this.address =  customer.getAddress().stream().filter(AddressResponse::isDefaults)
				.map(ad -> ad.getAddress()
						.concat(" " + ad.getLandmark().concat(" " + ad.getLandmark().concat(" " + ad.getCity().concat(
								"," + ad.getState().concat(" " + ad.getCountry().concat("-" + ad.getPincode())))))))
				.collect(Collectors.joining(","));
		this.trackingNumber = Helper.generateOrderNumber();
		this.accountNumber = accountNumber;
		this.status = "Pending";
		this.total = carts.stream().map(OrderItemDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
		this.payStatus = "Paid";
		this.items = carts.stream().map(i -> new OrderItem(i.getQuantity(), i.getProductId(), i.getPrice()))
				.collect(Collectors.toList());

	}
}
