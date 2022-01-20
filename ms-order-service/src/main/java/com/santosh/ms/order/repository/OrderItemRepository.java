/**
 * 
 */
package com.santosh.ms.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosh.ms.order.entity.OrderItem;

/**
 * @author santosh.kushwah
 * @since 14-01-2022
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
