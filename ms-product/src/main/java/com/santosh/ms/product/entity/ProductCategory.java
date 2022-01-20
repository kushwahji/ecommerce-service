/**
 * 
 */
package com.santosh.ms.product.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.santosh.ms.product.request.ProductRequestCategoryDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author santosh.kushwah
 *
 */
@Entity
@Table(name="product_category")
@Data
@NoArgsConstructor
public class ProductCategory {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="category_name")
    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> products;
    
    public ProductCategory(ProductRequestCategoryDto c) {
		this.categoryName = c.getCategoryName();
	}
}
