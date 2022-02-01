package com.skilldistillery.lettucemeet.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// this you get for free when you configure the db connection in application.properties file
	@Autowired
    private DataSource dataSource;

	// this bean is created in the application starter class if you're looking for it
	@Autowired
	private PasswordEncoder encoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // For CORS, the preflight request
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.POST, "/api/users/").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.POST, "/api/login/").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.POST, "/api/users").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.GET, "/api/types/").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.GET, "/api/types").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.GET, "/api/markets").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.GET, "/api/markets/**").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.GET, "/api/marketcomments").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.GET, "/api/sellerRatings").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.GET, "/api/productRatings/**").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.GET, "/api/productRatings/").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.GET, "/api/productratings/**").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.GET, "/api/marketcomments/**").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.GET, "/api/products").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.GET, "/api/products/**").permitAll() // will hit the OPTIONS on the route
				.antMatchers(HttpMethod.GET, "/api/market/**").permitAll() 
				.antMatchers(HttpMethod.GET, "/api/marketcomments").permitAll() 
				.antMatchers(HttpMethod.GET, "/api/sellerRatings/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/marketratings/**").permitAll() 
				.antMatchers(HttpMethod.GET, "/api/products").permitAll() 
				.antMatchers(HttpMethod.GET, "/api/products/**").permitAll() 
				.antMatchers(HttpMethod.GET, "/api/productcomments").permitAll() 
				.antMatchers(HttpMethod.GET, "/api/productcomments/**").permitAll() 
				.antMatchers(HttpMethod.DELETE, "/api/productcomments/**").permitAll() 
				.antMatchers(HttpMethod.GET, "/api/marketratings/avegrating/**").permitAll() 
				.antMatchers(HttpMethod.OPTIONS, "/api/users/*").hasAuthority("admin") // return true or false 
				.antMatchers(HttpMethod.PUT, "/api/**").hasAuthority("standard") // return true or false 
				.antMatchers(HttpMethod.POST, "/api/**").hasAuthority("standard") // return true or false 
				.antMatchers(HttpMethod.POST, "/api/**").hasAuthority("user") // return true or false 
				.antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("user") // return true or false 
				.antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("standard") // return true or false 
				.antMatchers("/api/**").authenticated() // Requests for our REST API must be authorized.
				.anyRequest().permitAll() // All other requests are allowed without authorization.
				.and().httpBasic(); // Use HTTP Basic Authentication

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		String userQuery = "SELECT username, password, enabled FROM User WHERE username=?";
		String authQuery = "SELECT username, role FROM User WHERE username=?";
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(userQuery)
				.authoritiesByUsernameQuery(authQuery).passwordEncoder(encoder);
	}

}
