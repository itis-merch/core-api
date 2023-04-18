/**
 * The WebSecurityConfiguration class is a configuration class that enables and configures web security for the application.
 * It extends the WebSecurityConfigurerAdapter class provided by Spring Security.
 */
package com.itis.merch.core.security.config;

import com.itis.merch.core.security.filters.JWTRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final JWTRequestFilter jwtRequestFilter;

	/**
	 * This method creates and returns an AuthenticationManager bean that will be used by the application.
	 *
	 * @return the authentication manager bean
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * This method configures the HttpSecurity object to specify security constraints for HTTP requests.
	 * The "/auth/**" endpoint is permitted for all requests while all other requests are authenticated.
	 * The "/auth/logout" endpoint is used to log out the user, and the user's session is invalidated,
	 * the authentication is cleared, and the JSESSIONID cookie is deleted.
	 *
	 * @param http the HttpSecurity object to configure
	 * @throws Exception if an error occurs while configuring the HttpSecurity object
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
						.csrf().disable()
						.sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						.and()
						.authorizeRequests()
						.antMatchers("/**").permitAll()
						.anyRequest().authenticated()
						.and()
						.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
