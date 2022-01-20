package com.santosh.ms.order.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @author santosh.kushwah
 * @since 12-01-2022
 */

@Entity
@Table(name="product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private ProductCategory category;

    @Column(name ="name")
    private String name;
    
    @Column(name ="sku")
    private String sku;

    @Column(name ="description")
    private String description;

    @Column(name ="price")
    private BigDecimal price;

    @Column(name ="image_url")
    private String imageUrl;

    @Column(name ="active")
    private boolean active;

    @Column(name ="stock")
    private int stock;

    @Column(name ="date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name ="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;


}