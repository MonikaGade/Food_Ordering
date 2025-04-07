package com.shopping.cart.app.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class Appconfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("http   " + http);

		http.sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/api/admin/**")
						.hasAnyRole("RESTAURANT_OWNER", "ADMIN").requestMatchers("/api/**").authenticated() // JWT token
						.anyRequest().permitAll()) // signup,signin
				.addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class).csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(corsConfigurationSource()));// access
		return http.build();

		// 
		// first when the
	}

	private CorsConfigurationSource corsConfigurationSource() {
		return new CorsConfigurationSource() {
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

				System.out.println("request " + request);
				CorsConfiguration cfg = new CorsConfiguration();
				cfg.setAllowedOrigins(Arrays.asList("https://zosh-food.vercel.app/", "http://localhost:5173"));

				cfg.setAllowedMethods(Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				cfg.setExposedHeaders(Arrays.asList("Authorization"));
				cfg.setMaxAge(3600L);
				return cfg;
			}
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
