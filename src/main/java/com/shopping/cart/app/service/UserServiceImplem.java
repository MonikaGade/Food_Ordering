package com.shopping.cart.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.app.Repository.UserRepository;
import com.shopping.cart.app.config.JwtProvider;
import com.shopping.cart.app.model.User;

@Service

public class UserServiceImplem implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUserByJwtToken(String jwt) throws Exception {

		String emailFromJwtToken = jwtProvider.getEmailFromJwtToken(jwt);
		User userByEmail = findUserByEmail(emailFromJwtToken);

		return userByEmail;
	}

	@Override
	public User findUserByEmail(String email) throws Exception {

		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new Exception("user not found");
		}

		return user;
	}

}
