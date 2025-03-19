package com.shopping.cart.app.service;

import org.springframework.stereotype.Service;

import com.shopping.cart.app.model.User;

@Service
public interface UserService {
	
	public User findUserByJwtToken(String jwt) throws Exception;

	public User findUserByEmail(String email) throws Exception;
	
}
