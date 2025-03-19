package com.shopping.cart.app.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwt = request.getHeader(JwtConstant.JWT_HEADER);

		String authorizationHeader = request.getHeader("Authorization");

		// 🛑 Debugging: Check what the request is receiving
		System.out.println("Incoming Authorization Header: " + authorizationHeader);
		// Bearer token

		if (jwt != null) {
			jwt = jwt.substring(7);

			try {

				System.out.print("jwt " + jwt);

				SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
				Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();// 2
																												// return

				System.out.println(key + "key");
				String email = String.valueOf(claims.get("email"));
				String authorities = String.valueOf(claims.get("authorities"));

				System.out.println("Extracted email: " + email);
				System.out.println("Extracted authorities: " + authorities);

				List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auth);
				SecurityContextHolder.getContext().setAuthentication(authentication);

			} catch (Exception ex) {
				throw new BadCredentialsException("----invalid token----");
			}
		}

		filterChain.doFilter(request, response);

	}

}
