package com.shopping.cart.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.app.Repository.CategoryRepository;
import com.shopping.cart.app.Repository.RestaurantRepo;
import com.shopping.cart.app.model.Category;
import com.shopping.cart.app.model.Restaurant;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.service.CategoryService;
import com.shopping.cart.app.service.RestaurantService;
import com.shopping.cart.app.service.UserService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@PostMapping("/admin/category")
	public ResponseEntity<Category> createCategory(@RequestBody Category category,
			@RequestHeader("Authorization") String jwt) throws Exception {

		User user = userService.findUserByJwtToken(jwt);
		Category createdCategory = categoryService.createCategory(category.getName(), user.getId());

		return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);

	}

	// customer and rest owner

	@GetMapping("/category/restaurant/{id}")
	public ResponseEntity<List<Category>> getRestaurantCategory(@RequestHeader("Authorization") String jwt,
			@PathVariable Long id)
			throws Exception {
		User user = userService.findUserByJwtToken(jwt);
		List<Category> categories = categoryService.findCategoryByRestaurantId(id);
		return new ResponseEntity<>(categories, HttpStatus.CREATED);

	}
	
//	@GetMapping("/category/restaurant/{id}")
//	public ResponseEntity<List<Category>> getRestaurantCategory(
//	        @RequestHeader("Authorization") String jwt,
//	        @PathVariable Long id) throws Exception {
//	    
//	    // Debugging: Check if user is retrieved properly
//	    User user = userService.findUserByJwtToken(jwt);
//	    System.out.println("User: " + user);
//
//	    // Debugging: Check if restaurant exists
//	    Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
//	    if (restaurant == null) {
//	        throw new RuntimeException("No restaurant found for user ID: " + id);
//	    }
//	    System.out.println("Restaurant: " + restaurant);
//
//	    // Debugging: Check if categories are found
//	    List<Category> categories = categoryRepository.findByRestaurantId(restaurant.getId());
//	    System.out.println("Categories found: " + categories);
//
//	    return new ResponseEntity<>(categories, HttpStatus.OK);
//	}

	
//	@GetMapping("/category/restaurant/{id}")
//	public ResponseEntity<?> getRestaurantCategory(
//	        @RequestHeader("Authorization") String jwt,
//	        @PathVariable Long id) {
//	    try {
//	        // Log the received request
//	        System.out.println("Received request for restaurant ID: " + id);
//	        System.out.println("Received JWT: " + jwt);
//
//	        // Check if JWT is valid
//	        User user = userService.findUserByJwtToken(jwt);
//	        if (user == null) {
//	            return new ResponseEntity<>("Invalid JWT Token", HttpStatus.UNAUTHORIZED);
//	        }
//	        System.out.println("User Found: " + user);
//
//	        // Check if restaurant exists
//	        Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
//	        if (restaurant == null) {
//	            return new ResponseEntity<>("Restaurant not found for ID: " + id, HttpStatus.NOT_FOUND);
//	        }
//	        System.out.println("Restaurant Found: " + restaurant);
//
//	        // Fetch categories
//	        List<Category> categories = categoryRepository.findByRestaurantId(restaurant.getId());
//	        if (categories == null || categories.isEmpty()) {
//	            return new ResponseEntity<>("No categories found for restaurant ID: " + id, HttpStatus.NOT_FOUND);
//	        }
//	        System.out.println("Categories Found: " + categories);
//
//	        return new ResponseEntity<>(categories, HttpStatus.OK);
//	    } catch (Exception e) {
//	        e.printStackTrace(); // Print full error stack trace
//	        return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	}


}
