package com.shopping.cart.app.Response;

import com.shopping.cart.app.model.USER_ROLE;


public class AuthResponse {
	
	private String jwt;
	
	private String message;
	
	private USER_ROLE userRole;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public USER_ROLE getUserRole() {
		return userRole;
	}

	public void setUserRole(USER_ROLE userRole) {
		this.userRole = userRole;
	}

}
