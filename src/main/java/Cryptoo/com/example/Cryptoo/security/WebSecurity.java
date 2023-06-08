package Cryptoo.com.example.Cryptoo.security;


import Cryptoo.com.example.Cryptoo.services.UserService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http
				.cors().and()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers( SecurityConstants.SIGN_UP_URL,"/ResetPassword/email", "/ResetPassword/code","/ResetPassword/newpassword","/homeui","/contact","/homeui/update","/emailphone/checkemailphone","/emailphone/getemailphone","/emailphone/checkcode","/emailphone/sendcode","/images/{id}","/admin/**")
				.permitAll()
				.anyRequest().authenticated()
				.and().
				addFilter(getAuthenticationFilter())
				.addFilter(new AuthorizationFilter(authenticationManager()))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}
	
	
	protected AuthenticationFilter getAuthenticationFilter() throws Exception {
	    final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
	    filter.setFilterProcessesUrl("/users/login");
	    return filter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception  {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
}