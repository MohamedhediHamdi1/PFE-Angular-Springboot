package Cryptoo.com.example.Cryptoo.security;

import Cryptoo.com.example.Cryptoo.SpringApplicationContext;
import Cryptoo.com.example.Cryptoo.requests.UserLoginRequest;
import Cryptoo.com.example.Cryptoo.services.UserService;
import Cryptoo.com.example.Cryptoo.services.impl.LogsService;
import Cryptoo.com.example.Cryptoo.shared.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

private final AuthenticationManager authenticationManager;
	@Autowired
	LogsService logsService;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	private final int MAX_ATTEMPTS = 7;
	private final long BLOCK_TIME_MILLIS = 24 * 60 * 60 * 1000; // 24 hours in milliseconds

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			UserLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginRequest.class);
			String email = creds.getEmail();
			String password = creds.getPassword();


			UserService userService = (UserService) SpringApplicationContext.getBean("userServiceimpl");
			try {
				UserDto userDto1 = userService.getUser(email);
			}catch (Exception e){
				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				res.setContentType("application/json");
				PrintWriter out = res.getWriter();
				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("message", "Incorrect email");
				out.print(jsonResponse.toString());
				out.flush();
				logsService.addToLogs("An email "+email+" without an account attempted to log in.");
				return null;
			}


			UserDto userDto = userService.getUser(email);


			int attempts = userDto.getLoginAttempts();
			Date lastLoginAttempt = userDto.getLastLoginAttempt();

			if (attempts >= MAX_ATTEMPTS && lastLoginAttempt != null) {
				long now = System.currentTimeMillis();
				long diff = now - lastLoginAttempt.getTime();
				if (diff < BLOCK_TIME_MILLIS) {
					res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					res.setContentType("application/json");
					PrintWriter out = res.getWriter();
					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("message","For security reasons, your account has been temporarily disabled for 24 hours due to multiple failed login attempts. Please try again after the designated period has elapsed. If you continue to experience issues accessing your account, please contact our customer support team for further assistance.");
					out.print(jsonResponse.toString());
					out.flush();
					logsService.addToLogs("For security reasons, "+email+" has been temporarily disabled for 24 hours due to multiple failed login attempts.");
					return null;
				}
				// Reset login attempts if the block time has expired
				userDto.setLoginAttempts(0);
				userDto.setLastLoginAttempt(null);
				userService.updateUser(userDto.getUserId(), userDto);
			}


			if (!userService.getPaswword(userDto.getEmail(),password)) {
				userDto.setLoginAttempts(userDto.getLoginAttempts()+1);
				userDto.setLastLoginAttempt(new Date());
				userService.updateUser(userDto.getUserId(), userDto);

				res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				res.setContentType("application/json");
				PrintWriter out = res.getWriter();
				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("message", "Incorrect password");
				out.print(jsonResponse.toString());
				out.flush();
				logsService.addToLogs("An email "+email+" attempted to log in with an incorrect password.");
				return null;
			}

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>()));

			// Reset login attempts on successful authentication
			userDto.setLoginAttempts(0);
			userDto.setLastLoginAttempt(null);
			userService.updateUser(userDto.getUserId(), userDto);
			logsService.addToLogs("Email "+email+" logged in successfully.");
			return authentication;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}






	@Override
	    protected void successfulAuthentication(HttpServletRequest req,
	                                            HttpServletResponse res,
	                                            FilterChain chain,
	                                            Authentication auth) throws IOException, ServletException {
	        
	        String email = ((User) auth.getPrincipal()).getUsername();
	        String token = Jwts.builder()
	                .setSubject(email)
	                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
	                .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET )
	                .compact();
	        
	        UserService userService = (UserService)SpringApplicationContext.getBean("userServiceimpl");
	        
	        UserDto userDto = userService.getUser(email);


		  res.setContentType("application/json"); // set content type as json
		  PrintWriter out = res.getWriter(); // get PrintWriter object to write the response

		  // create a JSON object with the token and other user details
		  JSONObject jsonResponse = new JSONObject();
		  jsonResponse.put("token", token);
		  jsonResponse.put("userId", userDto.getUserId());

		  out.print(jsonResponse.toString()); // write the JSON response to the output stream
		  out.flush(); // flush the output stream




	       

	    }  
}

