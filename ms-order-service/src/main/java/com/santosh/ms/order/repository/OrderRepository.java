/**
 * 
 */
package com.santosh.ms.order.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.santosh.ms.order.entity.Order;

/**
 * @author santosh.kushwah
 * @since 13-01-2022
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByPhone(String phone);

}
