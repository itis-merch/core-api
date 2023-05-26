package com.itis.merch.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class is responsible for configuring CORS (Cross-Origin
 * Resource Sharing) in a Spring application.
 * <p>
 * {@code @EnableWebMvc} tells Spring that we want to configure
 * the web application using {@code WebMvcConfigurer}.
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	/**
	 * {@code addCorsMappings()} is a method of {@code WebMvcConfigurer}
	 * that allows us to add CORS settings to the application.
	 * <p>
	 * We add CORS configuration for all URLs starting with {@code
	 * /api/v1/}, allowing only {@code GET} and {@code POST} methods, an
	 * any domain with {@code allowedOrigins("**")}. This way, the
	 * application can handle requests from any domain that uses these methods.
	 *
	 * @param registry an instance of the {@code CorsRegistry} class that is
	 *                 used to register the CORS configuration for the endpoint
	 */
	@Override
	public void addCorsMappings(final CorsRegistry registry) {
		registry.addMapping("/api/v1/**")
						.allowedOrigins("*")
						.allowedHeaders("*")
						.allowedMethods("GET", "POST");
	}
}
