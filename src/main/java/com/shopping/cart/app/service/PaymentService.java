package com.shopping.cart.app.service;

import com.shopping.cart.app.Response.PaymentResponse;
import com.shopping.cart.app.model.Order;
import com.stripe.exception.StripeException;

public interface PaymentService {
	
	public PaymentResponse createPaymentLink(Order order) throws StripeException;
	

}
