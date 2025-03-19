package com.shopping.cart.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.app.Repository.FoodRepository;
import com.shopping.cart.app.Response.MessageResponse;
import com.shopping.cart.app.model.Food;
import com.shopping.cart.app.model.Restaurant;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.request.CreateFoodRequest;
import com.shopping.cart.app.service.FoodService;
import com.shopping.cart.app.service.RestaurantService;
import com.shopping.cart.app.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

	@Autowired
	private FoodRepository foodRepository;

	@Autowired
	private FoodService foodService;

	@Autowired
	private UserService userService;

	@Autowired
	private RestaurantService restaurantService;

	@PostMapping
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception {

		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
		Food food = foodService.createFood(req, req.getCategory(), restaurant);

		return new ResponseEntity<>(food, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id, @RequestHeader("Authorization") String jwt)
			throws Exception {

		User user = userService.findUserByJwtToken(jwt);

		foodService.deleteFood(id);

		MessageResponse res = new MessageResponse();
		res.setMessage("Food deleted successfully");

		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Food> updateFoodAvaibiltyStatus(@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		Food food = foodService.updateAvailibityStatus(id);
		
		return new ResponseEntity<>(food, HttpStatus.CREATED);
	}

}
