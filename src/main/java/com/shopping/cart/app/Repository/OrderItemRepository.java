package com.shopping.cart.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.cart.app.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	
	
	
}
