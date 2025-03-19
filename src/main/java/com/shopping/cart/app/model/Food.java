package com.shopping.cart.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Food {

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Category getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(Category foodCategory) {
		this.foodCategory = foodCategory;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public boolean isVegetian() {
		return isVegetian;
	}

	public void setVegetian(boolean isVegetian) {
		this.isVegetian = isVegetian;
	}

	public boolean isSeasonal() {
		return isSeasonal;
	}

	public void setSeasonal(boolean isSeasonal) {
		this.isSeasonal = isSeasonal;
	}

	public List<IngredientsItem> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientsItem> ingredients) {
		this.ingredients = ingredients;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String description;

	private Long price;

	@ManyToOne
	private Category foodCategory;

	@ElementCollection
	@Column(length = 1000)
	private List<String> images;

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@ManyToOne
	private Restaurant restaurant;

	private boolean isVegetian;

	private boolean isSeasonal;

	@ManyToMany
	private List<IngredientsItem> ingredients = new ArrayList<>();

	private Date creationDate;

	private boolean available;
	
	
}
