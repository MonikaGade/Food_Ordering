package com.shopping.cart.app.service;

import java.util.List;

import com.shopping.cart.app.Dto.RestaurentDto;
import com.shopping.cart.app.model.Restaurant;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.request.CreateRestaurantRequest;

public interface RestaurantService {
	
	
	public Restaurant createRestaurant(CreateRestaurantRequest req,User user);
	
	public Restaurant updateRestaurant(Long restaurantId,CreateRestaurantRequest updatedRestaurant) throws Exception;
	
	public void deleteRestuarent(Long restarantId) throws Exception;
	
	public List<Restaurant> getAllRestuarent();
	
	public List<Restaurant> searchRestaurant(String keyword);
	
	public Restaurant findRestaurantById(Long id) throws Exception;
	
	
	
	public Restaurant getRestaurantByUserId(Long userId) throws Exception;
	
	
	public RestaurentDto addToFavorities(Long restaurantId , User user) throws Exception;
	
	//status of the restaurant is open or close 
	public Restaurant updateRestaurantStatus(Long id)throws Exception;
	

}
