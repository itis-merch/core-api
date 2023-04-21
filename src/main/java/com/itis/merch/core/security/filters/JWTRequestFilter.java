package com.itis.merch.core.security.filters;

import com.itis.merch.core.security.JWTUtilService;
import com.itis.merch.core.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * The {@code JWTRequestFilter} class is responsible for filtering incoming HTTP
 * requests and extracting JWT tokens from their authorization headers. It uses
 * the {@code JWTUtilService} to validate and extract the username from the JWT
 * token, and then uses the {@code AppUserService} to retrieve the corresponding
 * {@code UserDetails} object. If the token is valid, it sets the authentication
 * token for the current security context.
 */
@Component
@RequiredArgsConstructor
public class JWTRequestFilter extends OncePerRequestFilter {

	/**
	 * The {@code AppUserService} object used to retrieve {@code UserDetails} objects.
	 */
	private final AppUserService appUserService;

	/**
	 * The {@code JWTUtilService} object used to validate and extract JWT tokens.
	 */
	private final JWTUtilService jwtUtilService;

	/**
	 * Filters the incoming HTTP request and extracts the JWT token from the authorization
	 * header. If the token is valid and not expired, it sets the authentication token for
	 * the current security context.
	 *
	 * @param request     the HTTP request.
	 * @param response    the HTTP response.
	 * @param filterChain the filter chain.
	 * @throws ServletException if the request cannot be handled.
	 * @throws IOException      if an I/O error occurs.
	 */
	@Override
	protected void doFilterInternal(
					final HttpServletRequest request,
					final HttpServletResponse response,
					final FilterChain filterChain) throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");

		final String username;
		final String jwt;

		if (Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		jwt = authorizationHeader.substring(7);
		username = jwtUtilService.extractUsername(jwt);

		if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
			UserDetails userDetails = this.appUserService.loadUserByUsername(username);

			if (jwtUtilService.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
								new UsernamePasswordAuthenticationToken(
												userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		filterChain.doFilter(request, response);
	}
}
