package com.shopping.cart.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.cart.app.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Long>{

}
