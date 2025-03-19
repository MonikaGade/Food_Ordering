package com.shopping.cart.app.config;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

	SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

//	public String generateToken(Authentication authentication) {
//		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//		
//
//		String roles = populateAuthorities(authorities);
//		System.out.println(roles + "roles");
//		String jwt = Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 86400000))
//				.claim("email", authentication.getName()).claim("authorities", roles).signWith(key).compact();
//		return jwt;
//	}
	
	public String generateToken(Authentication authentication) {
	    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

	    // Print extracted roles from authentication
	    System.out.println("Original authorities: " + authorities);

	    String roles = populateAuthorities(authorities);

	    // Ensure roles are prefixed with "ROLE_" (if not already)
	    String prefixedRoles = roles.replaceAll("(?!ROLE_)(\\b\\w+\\b)", "ROLE_$1"); 

	    // Print roles after modification
	    System.out.println("Prefixed roles stored in JWT: " + prefixedRoles);

	    String jwt = Jwts.builder()
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(new Date().getTime() + 86400000))
	            .claim("email", authentication.getName())
	            .claim("authorities", prefixedRoles) // Ensure roles are correctly prefixed
	            .signWith(key)
	            .compact();

	    System.out.println("Generated JWT: " + jwt);
	    return jwt;
	}


	public String getEmailFromJwtToken(String jwt) {
		
		System.out.println("jwt token "+jwt);
		jwt = jwt.substring(7);
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		String email = String.valueOf(claims.get("email"));
		return email;
	}

	private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {

		Set<String> auths = new HashSet<>();
		System.out.println("Auths"+auths);
		for (GrantedAuthority authority : authorities) {
			auths.add(authority.getAuthority());
		}
		return String.join(",", auths);
	}
	public SecretKey getKey() {
		return key;
	}

	public void setKey(SecretKey key) {
		this.key = key;
	}
}

//generate token
//getemail from the JwtToken
