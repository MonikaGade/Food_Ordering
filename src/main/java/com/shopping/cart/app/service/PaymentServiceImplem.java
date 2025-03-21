package com.shopping.cart.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shopping.cart.app.Response.PaymentResponse;
import com.shopping.cart.app.model.Order;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class PaymentServiceImplem implements PaymentService {

	@Value("${stripe.secret.key}")
	private String stripeSecretKey;

	@Override
	public PaymentResponse createPaymentLink(Order order) throws StripeException {
		// Set API Key
		Stripe.apiKey = stripeSecretKey;

		// Build Stripe Checkout Session parameters correctly
		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl("http://localhost:5173/payment/success/" + order.getId())
				.setCancelUrl(
						"http://localhost:5173/payment/fail")
				.addLineItem(
						SessionCreateParams.LineItem
								.builder().setQuantity(1L).setPriceData(
										SessionCreateParams.LineItem.PriceData.builder().setCurrency("usd")
												// .setUnitAmount((long) (order.getTotalPrice() * 100)) // Convert to
												// cents
												.setUnitAmount((long) (200 * 100))
												.setProductData(SessionCreateParams.LineItem.PriceData.ProductData
														.builder().setName("Yummy Food").build())
												.build())
								.build())
				.build();

		// Create the Stripe session correctly
		Session session = Session.create(params);
		System.out.println("Stripe Payment Session URL: " + session.getUrl());

		// Return response
		PaymentResponse res = new PaymentResponse();
		res.setPayment_url(session.getUrl());
		return res;
	}
}
