package com.shopping.cart.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.cart.app.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

	public User findByEmail(String userName);
	
	
}
