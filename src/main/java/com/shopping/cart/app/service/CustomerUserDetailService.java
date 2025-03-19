package com.shopping.cart.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopping.cart.app.Repository.UserRepository;
import com.shopping.cart.app.model.USER_ROLE;
import com.shopping.cart.app.model.User;

@Service
public class CustomerUserDetailService implements UserDetailsService  { //spring-security
	
	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings("null")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("User not found with email "+username);
		}
		
		
		USER_ROLE role = user.getRole();
		
		
		List<GrantedAuthority> authorities=new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(role.toString()));
	    
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
	
	
	

}
