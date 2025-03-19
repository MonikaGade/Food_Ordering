package com.shopping.cart.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.cart.app.model.Cart;

public interface CartRepository extends JpaRepository<Cart,Long>{
	
	public Cart findByCustomerId(Long userId);
	

}
