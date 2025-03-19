package com.shopping.cart.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.cart.app.model.IngredientCategory;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {

	List<IngredientCategory> findByRestaurantId(Long id);
	
}
