package com.shopping.cart.app.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.app.Repository.CartRepository;
import com.shopping.cart.app.Repository.UserRepository;
import com.shopping.cart.app.Response.AuthResponse;
import com.shopping.cart.app.config.JwtProvider;
import com.shopping.cart.app.model.Cart;
import com.shopping.cart.app.model.USER_ROLE;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.request.LoginRequest;
import com.shopping.cart.app.service.CustomerUserDetailService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerUserDetailService customerUserDetailService;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private CartRepository cartRepository;
	@CrossOrigin(origins = "http://localhost:5173")
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse>  createUserHandler(@RequestBody User user) throws Exception{
		
		User byEmail = userRepository.findByEmail(user.getEmail());
		
		if(byEmail!=null) {
			throw new Exception("Email already exist use another account");
		}
		
		User createdUser=new User();
		createdUser.setEmail(user.getEmail());
		createdUser.setFullName(user.getFullName());
		createdUser.setRole(user.getRole());
		createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		
		User saveduser = userRepository.save(createdUser);
		
		
		Cart cart=new Cart();
		cart.setCustomer(saveduser);
		cartRepository.save(cart);
		
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		String jwt = jwtProvider.generateToken(authentication);
		
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Register success");
		authResponse.setUserRole(saveduser.getRole());

		return  new ResponseEntity<>(authResponse,HttpStatus.CREATED);
		
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signup(@RequestBody LoginRequest req){
		
		String username = req.getEmail();
		String password = req.getPassword();
		
		 Authentication authentication=authenticate(username,password);
		 Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		 String role=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
		
		 
		 String jwt = jwtProvider.generateToken(authentication);
			
			
			AuthResponse authResponse = new AuthResponse();
			authResponse.setJwt(jwt);
			authResponse.setMessage("Login success");
			authResponse.setUserRole(USER_ROLE.valueOf(role));
			
			return  new ResponseEntity<>(authResponse,HttpStatus.OK);
		
		
	}


	private Authentication authenticate(String username, String password) {
		
		
		UserDetails userDetails=customerUserDetailService.loadUserByUsername(username);
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid Username....");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password....");
		}
		

		return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
	}
		
	
}

//user is a authenticated user are not
//user email , password check in the server is a authenticated if the email exist r not if  a new account should be created
//if doesn't exist a new account is created ie user, and cart
//Authenticated is added and security is created for it
//find user by jwtToken