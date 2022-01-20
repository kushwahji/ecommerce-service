/**
 * 
 */
package com.santosh.ms.customer.entity;

import java.time.LocalDateTime;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.santosh.ms.customer.request.CustomerDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "phone", unique = true, nullable = false)
	private String phone;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	
	@Valid
	private List<Address> address;

	@PrePersist
	public void prePersist() {
		createdOn = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedOn = LocalDateTime.now();
	}

	public Customer(CustomerDto request) {
		this.firstName = request.getFirstName();
		this.lastName = request.getLastName();
		this.age = request.getAge();
		this.email = request.getEmail();
		this.phone = request.getPhone();
		this.address = request.getAddress().stream().map(Address::new).collect(Collectors.toList());
	}

}
