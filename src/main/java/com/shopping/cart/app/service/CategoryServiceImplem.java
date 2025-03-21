package com.shopping.cart.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.app.Repository.CategoryRepository;
import com.shopping.cart.app.model.Category;
import com.shopping.cart.app.model.Restaurant;

@Service
public class CategoryServiceImplem implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private RestaurantService restaurantService;

	@Override
	public Category createCategory(String name, Long userId) {
		Restaurant restaurant = null;
		try {
			restaurant = restaurantService.getRestaurantByUserId(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Category category = new Category();
		category.setName(name);
		category.setRestaurant(restaurant);
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
         
		Restaurant restaurantByUserId = restaurantService.getRestaurantByUserId(id);
		return categoryRepository.findByRestaurantId(id);
	}

	@Override
	public Category findCategory(Long id) throws Exception {

		Optional<Category> optionalCategory = categoryRepository.findById(id);

		if (optionalCategory.isEmpty()) {
			throw new Exception("category not found ");
		}

		return optionalCategory.get();
	}

}
