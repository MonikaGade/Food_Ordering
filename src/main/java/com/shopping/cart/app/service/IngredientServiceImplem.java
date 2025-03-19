package com.shopping.cart.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.app.Repository.IngredientCategoryRepository;
import com.shopping.cart.app.Repository.IngredientItemRepository;
import com.shopping.cart.app.model.IngredientCategory;
import com.shopping.cart.app.model.IngredientsItem;
import com.shopping.cart.app.model.Restaurant;

@Service
public class IngredientServiceImplem implements IngredientService{

	@Autowired
	private IngredientCategoryRepository ingredientCategoryRepo;
	
	@Autowired
	private IngredientItemRepository ingredientItemRepo;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Override
	public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
		
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		
		IngredientCategory ingredientCategory = new IngredientCategory();
		ingredientCategory.setRestaurant(restaurant);
		ingredientCategory.setName(name);
		return ingredientCategoryRepo.save(ingredientCategory);
	}

	@Override
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
		Optional<IngredientCategory> opt = ingredientCategoryRepo.findById(id);
		if(opt.isEmpty()) {
			throw new Exception("Ingredient Category is not found");
		}
		return opt.get();
	}

	@Override
	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
		
		restaurantService.findRestaurantById(id);
		
		
		return ingredientCategoryRepo.findByRestaurantId(id);
	}

	@Override
	public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) {
		
		return ingredientItemRepo.findByRestaurantId(restaurantId);
	}

	@Override
	public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)
			throws Exception {
		
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		IngredientCategory category = findIngredientCategoryById(categoryId);
		IngredientsItem   item= new IngredientsItem();
		
		item.setName(ingredientName);
		item.setRestaurant(restaurant);
		item.setCategory(category);
		
		IngredientsItem ingredient = ingredientItemRepo.save(item);
		category.getIngredients().add(ingredient);
		
		
		
		return ingredient;
	}

	@Override
	public IngredientsItem updateStock(Long id) throws Exception {
		 Optional<IngredientsItem> optionalIngredientsItem = ingredientItemRepo.findById(id);
		if(optionalIngredientsItem.isEmpty()) {
			throw new Exception("Ingredient not found");
		}
		
	 IngredientsItem ingredientsItem = optionalIngredientsItem.get();
	 ingredientsItem.setInStroke(!ingredientsItem.isInStroke());
	 return ingredientItemRepo.save(ingredientsItem);
	}

}
