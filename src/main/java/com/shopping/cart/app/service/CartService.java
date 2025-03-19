package com.shopping.cart.app.service;

import com.shopping.cart.app.model.Cart;
import com.shopping.cart.app.model.CartItem;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.request.AddCartItemRequest;

public interface CartService {
	
	
	public CartItem addItemToCart(AddCartItemRequest req,String jwt) throws Exception;
	 
	
	public CartItem updateCartItemQuntity(Long cartItem,int qunatity) throws Exception;
	
	public Cart removeItemFromCart(Long cartItemId,String jwt) throws Exception;

	public Long calculateCartTotals(Cart cart) throws Exception;
	
	public Cart findCartById(Long id) throws Exception;
	
	public Cart findCartByUserId(Long userId) throws Exception;
	
	public Cart clearCart(Long userId) throws Exception;
}
