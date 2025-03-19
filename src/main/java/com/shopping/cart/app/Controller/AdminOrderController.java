package com.shopping.cart.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.app.model.Order;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.service.OrderService;
import com.shopping.cart.app.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/order/restaurant/{id}")
	public ResponseEntity<List<Order>> getOrderHistory(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id,
			@RequestParam(required = false) String order_status) throws Exception {

		User user=userService.findUserByJwtToken(jwt);
		List<Order> orders = orderService.getRestaurantsOrder(id,order_status);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
	
	@PutMapping("/order/{id}/{orderStatus}")
	public ResponseEntity<Order> updateOrderStatus(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id,
			@PathVariable String orderStatus) throws Exception {

		User user=userService.findUserByJwtToken(jwt);
		Order order = orderService.updateOrder(id, orderStatus);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
}
