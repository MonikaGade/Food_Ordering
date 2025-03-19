package com.shopping.cart.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.cart.app.model.IngredientsItem;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem,Long>{

	List<IngredientsItem> findByRestaurantId(Long id);
	
	
}
