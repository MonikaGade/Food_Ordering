package com.shopping.cart.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.app.Dto.RestaurentDto;
import com.shopping.cart.app.Repository.AddressRepository;
import com.shopping.cart.app.Repository.RestaurantRepo;
import com.shopping.cart.app.Repository.UserRepository;
import com.shopping.cart.app.model.Address;
import com.shopping.cart.app.model.Restaurant;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImple implements RestaurantService {

	@Autowired
	private RestaurantRepo restaurantRepo;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	//creating a restaurant -> rest details enter
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

		Address address = addressRepository.save(req.getAddress());

		Restaurant restaurant = new Restaurant();
		restaurant.setAddress(address);
		restaurant.setContactInformation(req.getContactInformation());
		restaurant.setCuisineType(req.getCuisinetype());
		restaurant.setDescription(req.getDescription());
		restaurant.setImages(req.getImages());
		restaurant.setName(req.getName());
		restaurant.setOpeningHours(req.getOpeningHours());
		restaurant.setRegistrationDate(LocalDateTime.now());
		restaurant.setOwner(user);
		return restaurantRepo.save(restaurant);
	}


	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {

		Restaurant restaurantById = findRestaurantById(restaurantId);

		if (restaurantById.getCuisineType() != null) {
			restaurantById.setCuisineType(updatedRestaurant.getCuisinetype());
		}
		if (restaurantById.getDescription() != null) {
			restaurantById.setDescription(updatedRestaurant.getDescription());
		}
		if (restaurantById.getName() != null) {
			restaurantById.setName(updatedRestaurant.getName());
		}

		return restaurantRepo.save(restaurantById);
	}

	@Override
	public void deleteRestuarent(Long restarantId) throws Exception {

		Restaurant restaurantById = findRestaurantById(restarantId);
		restaurantRepo.delete(restaurantById);
		
	}

	@Override
	public List<Restaurant> getAllRestuarent() {

		return restaurantRepo.findAll();
	}

	@Override
	public List<Restaurant> searchRestaurant(String keyword) {

		return restaurantRepo.findBySearchQuery(keyword);
	}

	@Override
	public Restaurant findRestaurantById(Long id) throws Exception {
		Optional<Restaurant>  opt=restaurantRepo.findById(id);
		
		if(opt.isEmpty()) {
			throw new Exception("Restaurant not found with id"+id);
		}
		return opt.get();
	}

//	@Override
//	public Restaurant getRestaurantByUserId(Long userId) throws Exception {
//         
//		Restaurant restaurant=restaurantRepo.findByOwnerId(userId);
//		
//		if(restaurant==null) {
//			throw new Exception("Restaurant not found with owner id"+userId);
//		}
//		return restaurant;
//	}
	
	public Restaurant getRestaurantByUserId(Long id) {
	    System.out.println("Fetching restaurant for user ID: " + id);
	    Optional<Restaurant> restaurant = restaurantRepo.findById(id);

	    if (restaurant.isEmpty()) {
	        System.out.println("No restaurant found for ID: " + id);
	        return null;
	    }

	    System.out.println("Fetched Restaurant: " + restaurant.get());
	    return restaurant.get();
	}


	@Override
	public RestaurentDto addToFavorities(Long restaurantId, User user) throws Exception {
        // check if exist -> remove from the fav . if not present -> add into fav 
		
		 Restaurant restaurant=findRestaurantById(restaurantId);
		 
		 RestaurentDto dto=new RestaurentDto();
		 dto.setDescription(restaurant.getDescription());
		 dto.setImages(restaurant.getImages());
		 dto.setTitle(restaurant.getName());
		 dto.setId(restaurantId);
		 
		boolean isFavorited=false;
		List<RestaurentDto> favorites=user.getFavorites();
		for(RestaurentDto favorite:favorites) {
			if(favorite.getId().equals(restaurantId)) {
				isFavorited=true;
				break;
			}
		}
		 
		if(isFavorited) {
			favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
		}else {
			favorites.add(dto);
		}
		
		
		 userRepository.save(user);
		
		return dto;
	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws Exception {
		Restaurant restaurant=findRestaurantById(id);
		restaurant.setOpen(!restaurant.isOpen());
		
		
		return restaurantRepo.save(restaurant);
	}

}
