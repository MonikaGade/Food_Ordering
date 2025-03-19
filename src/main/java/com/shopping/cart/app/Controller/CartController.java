package com.shopping.cart.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.app.model.Cart;
import com.shopping.cart.app.model.CartItem;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.request.AddCartItemRequest;
import com.shopping.cart.app.request.UpdateCartItemRequest;
import com.shopping.cart.app.service.CartService;
import com.shopping.cart.app.service.UserService;

@RestController
@RequestMapping("/api")
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;

	@PutMapping("/cart/add")
	public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception {

		CartItem cartItem = cartService.addItemToCart(req, jwt);
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
	}
	
	@PutMapping("/cart-item/update")
	public ResponseEntity<CartItem> updateCartItemQuantity(
			@RequestBody UpdateCartItemRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception {

		CartItem cartItem = cartService.updateCartItemQuntity(req.getCartItemId(), req.getQuantity());
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
	}
	
	@PutMapping("/cart-item/{id}/remove")
	public ResponseEntity<Cart> updateCartItemQuantity(
			@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws Exception {

		Cart cart = cartService.removeItemFromCart(id, jwt);
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	@PutMapping("/cart/clear")
	public ResponseEntity<Cart> clearCart(
			@RequestHeader("Authorization") String jwt) throws Exception {
		 User userId = userService.findUserByJwtToken(jwt);
		Cart cart = cartService.clearCart(userId.getId());
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}
	
	
	@GetMapping("/cart")
	public ResponseEntity<Cart> findUserCart(
			@RequestHeader("Authorization") String jwt) throws Exception {
        User userId = userService.findUserByJwtToken(jwt);
		Cart cart = cartService.findCartByUserId(userId.getId());
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

}
