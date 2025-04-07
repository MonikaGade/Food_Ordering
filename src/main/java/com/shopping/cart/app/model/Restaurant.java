package com.shopping.cart.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
	
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", owner=" + owner + ", name=" + name + ", description=" + description
				+ ", cuisineType=" + cuisineType + ", address=" + address + ", contactInformation=" + contactInformation
				+ ", openingHours=" + openingHours + ", order=" + order + ", images=" + images + ", registrationDate="
				+ registrationDate + ", categories=" + categories + ", food=" + food + ", open=" + open + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private User owner;
	
	private String name;
	
	private String description;
	
	private String cuisineType;
	
	@OneToOne
	private Address address;
	
	@Embedded
	private ContactInformation contactInformation;
	
	private String openingHours;
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Order> order=new ArrayList<>();
	
	@ElementCollection
	@Column(length = 1000)
	private List<String> images;
	
	private LocalDateTime registrationDate;
	
	 @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Category> categories = new ArrayList<>();
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "restaurant",fetch = FetchType.EAGER)
	private List<Food> food=new ArrayList<>();
	
	private boolean open;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
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

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public List<Food> getFood() {
		return food;
	}

	public void setFood(List<Food> food) {
		this.food = food;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	

}
