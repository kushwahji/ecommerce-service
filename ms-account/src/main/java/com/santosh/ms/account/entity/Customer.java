package com.santosh.ms.account.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.santosh.ms.account.dto.CustomerRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther Santosh-Kus
 * Date: 28-12-2021
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
    private String firstName;

    @Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
    private String lastName;

    @Column(name = "AGE", unique = false, nullable = false)
    private int age;

    @Column(name = "EMAIL", unique = false, nullable = false, length = 100)
    private String email;

    @Column(name = "PHONE", unique = false, nullable = false)
    private String phone;

    @Column(name = "AADHAAR_CARD", unique = true, nullable = false, length = 100)
    private String aadhaar;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "ID")

    @Valid
    private List<Account> accounts;

    public Customer(CustomerRequestDto customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.age =customer.getAge();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.aadhaar = customer.getAadhaar();
        List<Account> ac = new ArrayList<>();
        ac.add(new Account(customer.getFirstName().concat(" ").concat(customer.getLastName())));
        this.accounts = ac;
    }
    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }

}
