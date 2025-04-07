package com.shopping.cart.app.service;

import java.util.List;

import com.shopping.cart.app.model.Category;

public interface CategoryService {

	public Category createCategory(String name, Long userId) throws Exception;

	public List<Category> findCategoryByRestaurantId(Long id) throws Exception;

	public Category findCategory(Long id) throws Exception;
}
