package com.shopping.cart.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.cart.app.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{

	public List<Category> findByRestaurantId(Long id);
	
}
