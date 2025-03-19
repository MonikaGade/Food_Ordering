package com.shopping.cart.app.service;

import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.app.Repository.AddressRepository;
import com.shopping.cart.app.Repository.OrderItemRepository;
import com.shopping.cart.app.Repository.OrderRepository;
import com.shopping.cart.app.Repository.RestaurantRepo;
import com.shopping.cart.app.Repository.UserRepository;
import com.shopping.cart.app.model.Address;
import com.shopping.cart.app.model.Cart;
import com.shopping.cart.app.model.CartItem;
import com.shopping.cart.app.model.Order;
import com.shopping.cart.app.model.OrderItem;
import com.shopping.cart.app.model.Restaurant;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.request.OrderRequest;
//order item -> createOrder -> user,rest,cart
@Service
public class OrderServiceImplem implements OrderService{
    @Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepo restaurantRepo;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private CartService cartService;
	
	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {
		Address shippAddress = order.getDeliveryAddress();
		Address savedAddress = addressRepository.save(shippAddress);
		if(!user.getAddress().contains(savedAddress)) {
			user.getAddress().add(savedAddress);
			userRepository.save(user);
		}
		
		Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
	
		
		Order createdOrder = new Order();
		createdOrder.setCustomer(user);
		createdOrder.setCreateddot(new Date());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.setDeliveryAddress(savedAddress);
		createdOrder.setRestaurant(restaurant);
		
		Cart cart = cartService.findCartByUserId(user.getId());
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		for(CartItem cartItem:cart.getCartItems()) {
		   OrderItem orderItem = new OrderItem();
		   orderItem.setFood(cartItem.getFood());
		   orderItem.setIngredients(cartItem.getIngredients());
		   orderItem.setQuantity(cartItem.getQuantity());
		   orderItem.setTotalPrice(cartItem.getTotalPrice());
		   
		   OrderItem savedOrderItem = orderItemRepository.save(orderItem);
		   orderItems.add(savedOrderItem);
		}
		
		Long totalPrice = cartService.calculateCartTotals(cart);
		createdOrder.setItems(orderItems);
		createdOrder.setTotalPrice(totalPrice);
		
		Order savedOrder = orderRepository.save(createdOrder);
		restaurant.getOrder().add(savedOrder);
		
		return createdOrder;
	}

	@Override
	public Order updateOrder(Long oredrId, String orderStatus) throws Exception {
		Order order = findOrderById(oredrId);
		
		if(orderStatus.equals("OUT_FOR_DELIVERY")||
				orderStatus.equals("DELIVERED")||
				orderStatus.equals("PENDING")||
				orderStatus.equals("COMPLETED")) {
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		throw new Exception("Please select a valid order status");
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		
		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
		
	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {
		
		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
		// TODO Auto-generated method stub
		List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
		if(orderStatus!=null) {
			orders=orders.stream().filter(order->order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
		}
		return orders;
	}

	@Override
	public Order findOrderById(Long orderId) throws Exception {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isEmpty()) {
			throw new Exception("Order not found");
		}
		return optionalOrder.get();
	}

}
