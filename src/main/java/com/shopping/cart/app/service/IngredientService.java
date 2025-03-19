package com.shopping.cart.app.service;

import java.util.List;

import com.shopping.cart.app.model.IngredientCategory;
import com.shopping.cart.app.model.IngredientsItem;

//handled ingredient items and ingredients category in one group
public interface IngredientService {

	public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

	public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

	public List<IngredientsItem> findRestaurantIngredients(Long restaurantId);

	public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)
			throws Exception;

	public IngredientsItem updateStock(Long id) throws Exception;
}
