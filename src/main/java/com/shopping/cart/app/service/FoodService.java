package com.shopping.cart.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopping.cart.app.model.Category;
import com.shopping.cart.app.model.Food;
import com.shopping.cart.app.model.Restaurant;
import com.shopping.cart.app.request.CreateFoodRequest;

@Service
public interface FoodService {
	
	public Food createFood(CreateFoodRequest req,Category category,Restaurant restaurant);
	
	void deleteFood(Long foodId) throws Exception;
	
	public List<Food> getRestaurantsFood(Long restaurantId,
			    boolean isVegitarian,
				boolean isNonveg,
				boolean isSeasonal,
				String foodCategory);
	
	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId) throws Exception;
	
	public Food updateAvailibityStatus(Long foodId) throws Exception;
		
}

//Food service
//createFood
