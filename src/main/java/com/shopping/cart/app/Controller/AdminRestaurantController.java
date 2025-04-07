package com.shopping.cart.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.app.Response.MessageResponse;
import com.shopping.cart.app.model.Restaurant;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.request.CreateRestaurantRequest;
import com.shopping.cart.app.service.RestaurantService;
import com.shopping.cart.app.service.UserService;

//Admin Restaurant

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private UserService userService;

	@PostMapping()
	public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception {
		// creating the rest by getting the jwt token of the user and creating the rest
		// for that user
		User user = userService.findUserByJwtToken(jwt);
		System.out.println("123455");
		Restaurant restaurant = restaurantService.createRestaurant(req, user);
		System.out.println(restaurant);
		return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest req,
			@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.updateRestaurant(id, req);
		return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteRestaurant(@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		restaurantService.deleteRestuarent(id);
		MessageResponse res = new MessageResponse();
		res.setMessage("restaurant deleted Successfully");
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<Restaurant> updateRestaurantStatus(@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}

	@GetMapping("/user")
	public ResponseEntity<Restaurant> findRestaurantByUserId(@RequestHeader("Authorization") String jwt)
			throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}

}
