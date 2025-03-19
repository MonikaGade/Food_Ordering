package com.shopping.cart.app.service;

import java.util.List;

import com.shopping.cart.app.model.Order;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.request.OrderRequest;

public interface OrderService {
	
	
	public Order createOrder(OrderRequest order,User user) throws Exception;
	
	public Order updateOrder(Long oredrId,String orderStatus) throws Exception;

	public void cancelOrder(Long orderId) throws Exception;
	
	public List<Order> getUsersOrder(Long userId) throws Exception;
	
	public List<Order> getRestaurantsOrder(Long restaurantId,String orderStatus)throws Exception;
	
	public Order findOrderById(Long orderId) throws Exception;
	
}
