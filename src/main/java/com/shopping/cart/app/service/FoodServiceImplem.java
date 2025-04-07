package com.shopping.cart.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.app.Repository.FoodRepository;
import com.shopping.cart.app.model.Category;
import com.shopping.cart.app.model.Food;
import com.shopping.cart.app.model.Restaurant;
import com.shopping.cart.app.request.CreateFoodRequest;

@Service
public class FoodServiceImplem implements FoodService {

	@Autowired
	private FoodRepository foodRepository;

	@Override
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
		Food food = new Food();
		food.setFoodCategory(category);
		food.setRestaurant(restaurant);
		food.setDescription(req.getDescription());
		food.setImages(req.getImages());
		food.setName(req.getName());
		food.setPrice(req.getPrice());
		food.setIngredients(req.getIngredients());
		food.setSeasonal(req.isSeasional());
		food.setVegetian(req.isVegetarin());
		food.setCreationDate(new Date());
		Food savedFood = foodRepository.save(food);
		restaurant.getFood().add(savedFood);
		return savedFood;
	}

	@Override
	public void deleteFood(Long foodId) throws Exception {

		Food food = findFoodById(foodId);
		food.setRestaurant(null);
		foodRepository.save(food);

		// Food will be present in the database
		// But from the Rest it is removed

	}

	@Override
	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarian, boolean isNonveg, boolean isSeasonal,
			String foodCategory) {

		List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

		if (isVegitarian) {
			foods = filterBYVegetarian(foods, isVegitarian);
		}

		if (isNonveg) {
			foods = filterByNonVeg(foods, isNonveg);
		}

		if (isSeasonal) {
			foods = filterBySeasonal(foods, isSeasonal);
		}

		if (foodCategory != null && !foodCategory.equals("")) {
			foods = filterByCategory(foods, foodCategory);
		}
  System.out.println(foods+" foods");
		return foods;
	}

	private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
	    return foods.stream()
	            .filter(food -> {
	                if (food.getFoodCategory() != null && food.getFoodCategory().getName() != null) {
	                    System.out.println("Checking category: " + food.getFoodCategory().getName());
	                    return food.getFoodCategory().getName().equals(foodCategory);
	                }
	                return false;
	            })
	            .collect(Collectors.toList());
	}


	private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {

		return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
	}

	private List<Food> filterByNonVeg(List<Food> foods, boolean isNonveg) {

		return foods.stream().filter(food -> food.isVegetian() == false).collect(Collectors.toList());
	}

	private List<Food> filterBYVegetarian(List<Food> foods, boolean isVegitarian) {

		return foods.stream().filter(food -> food.isVegetian() == isVegitarian).collect(Collectors.toList());
	}

	@Override
	public List<Food> searchFood(String keyword) {

		return foodRepository.searchFood(keyword);
	}

	@Override
	public Food findFoodById(Long foodId) throws Exception {
		  Optional<Food> optionalFood = foodRepository.findById(foodId);
		  
		  if(optionalFood.isEmpty()) {
			  throw new Exception("Food not exist");
		  }
		  
		  
		return optionalFood.get();
	}

	@Override
	public Food updateAvailibityStatus(Long foodId) throws Exception {
	    Food food= findFoodById(foodId);
	    food.setAvailable(!food.isAvailable());
		return foodRepository.save(food);
	}

}
