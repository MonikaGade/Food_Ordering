package com.shopping.cart.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.app.Dto.RestaurentDto;
import com.shopping.cart.app.model.Restaurant;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.service.RestaurantService;
import com.shopping.cart.app.service.UserService;

//customer side
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private UserService userService;

	@GetMapping("/search")
	public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestHeader("Authorization") String jwt,
			@RequestParam String keyword) throws Exception {
		// creating the rest by getting the jwt token of the user and creating the rest
		// for that user
		User user = userService.findUserByJwtToken(jwt);
		List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<Restaurant>> getAllRestaurant(@RequestHeader("Authorization") String jwt) throws Exception {
		// creating the rest by getting the jwt token of the user and creating the rest
		// for that user
		User user = userService.findUserByJwtToken(jwt);
		List<Restaurant> restaurant = restaurantService.getAllRestuarent();
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> findRestaurantById(@RequestHeader("Authorization") String jwt,
			@PathVariable Long id)
			throws Exception {
		// creating the rest by getting the jwt token of the user and creating the rest
		// for that user
		User user = userService.findUserByJwtToken(jwt);

		Restaurant restaurant = restaurantService.findRestaurantById(id);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}/add-favorites")
	public ResponseEntity<RestaurentDto> addToFavorites(@RequestHeader("Authorization") String jwt,
			@PathVariable Long id)
			throws Exception { 
		// creating the rest by getting the jwt token of the user and creating the rest
		// for that user
		User user = userService.findUserByJwtToken(jwt);

		RestaurentDto restaurent= restaurantService.addToFavorities(id, user);
		
		
		return new ResponseEntity<>(restaurent, HttpStatus.OK);
	}
	
	
	

}
