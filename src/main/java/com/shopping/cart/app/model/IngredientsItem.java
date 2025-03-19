package com.shopping.cart.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class IngredientsItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@ManyToOne
	private IngredientCategory category;
	
	@ManyToOne
	private Restaurant restaurant;
	
	private boolean inStroke=true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IngredientCategory getCategory() {
		return category;
	}

	public void setCategory(IngredientCategory category) {
		this.category = category;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public boolean isInStroke() {
		return inStroke;
	}

	public void setInStroke(boolean inStroke) {
		this.inStroke = inStroke;
	}
	
}
