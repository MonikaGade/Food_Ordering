package com.shopping.cart.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopping.cart.app.model.Food;

public interface FoodRepository extends JpaRepository<Food, Long>{
	
	List<Food> findByRestaurantId(Long restaurantId);
	
	@Query("SELECT f FROM Food f WHERE f.name LIKE %:Keyword% OR f.foodCategory.name LIKE %:keyword%")
	List<Food> searchFood(@Param("Keyword") String keyword);
	 
}
