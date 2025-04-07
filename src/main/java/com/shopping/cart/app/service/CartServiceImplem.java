package com.shopping.cart.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.app.Repository.CartItemRepository;
import com.shopping.cart.app.Repository.CartRepository;
import com.shopping.cart.app.model.Cart;
import com.shopping.cart.app.model.CartItem;
import com.shopping.cart.app.model.Food;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.request.AddCartItemRequest;

@Service
public class CartServiceImplem implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private FoodService foodService;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		Food food = foodService.findFoodById(req.getFoodId());
		Cart cart = cartRepository.findByCustomerId(user.getId());
		for (CartItem cartItem : cart.getCartItems()) {
			if (cartItem.getFood().equals(food)) {
				int newQuantity = cartItem.getQuantity() + req.getQuantity();
				return updateCartItemQuntity(cartItem.getId(), newQuantity);
			}
		}
		CartItem newCartItem = new CartItem();
		newCartItem.setFood(food);
		newCartItem.setCart(cart);
		newCartItem.setQuantity(req.getQuantity());
		newCartItem.setIngredients(req.getIngredients());
		newCartItem.setTotalPrice(req.getQuantity() * food.getPrice());
		CartItem savedCartItem = cartItemRepository.save(newCartItem);
		cart.getCartItems().add(savedCartItem);
		return savedCartItem;
	}
	@Override
	public CartItem updateCartItemQuntity(Long cartItemId, int qunatity) throws Exception {
		Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
		if (cartItemOptional.isEmpty()) {
			throw new Exception("Cart Item not found");
		}
		CartItem item = cartItemOptional.get();
		item.setQuantity(qunatity);
		item.setTotalPrice(item.getFood().getPrice() * qunatity);
		// price * quantity = totalPrice
		return cartItemRepository.save(item);
	}
	@Override
	public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		Cart cart = cartRepository.findByCustomerId(user.getId());
		Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
		if (cartItemOptional.isEmpty()) {
			throw new Exception("Cart Item not found");
		}
		CartItem item = cartItemOptional.get();
		cart.getCartItems().remove(item);
		return cartRepository.save(cart);
	}
	@Override
	public Long calculateCartTotals(Cart cart) throws Exception {
		Long total = 0L;
		for (CartItem cartItem : cart.getCartItems()) {
			total += cartItem.getFood().getPrice() * cartItem.getQuantity();
		}
		return total;
	}
	@Override
	public Cart findCartById(Long id) throws Exception {
		Optional<Cart> optionalCart = cartRepository.findById(id);
		if (optionalCart.isEmpty()) {
			throw new Exception("cart not found with id " + id);
		}
		return optionalCart.get();
	}
	@Override
	public Cart findCartByUserId(Long userId) throws Exception {
		//User user = userService.findUserByJwtToken(jwt);
		Cart cart = cartRepository.findByCustomerId(userId);
		cart.setTotal(calculateCartTotals(cart));
		return cart;
	}
	@Override
	public Cart clearCart(Long userId) throws Exception {
		//User user = userService.findUserByJwtToken(jwt);
		Cart cart = findCartByUserId(userId);
		cart.getCartItems().clear();
		return cartRepository.save(cart);
	}

}
