package com.itis.merch.core.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.merch.core.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A filter to handle exceptions thrown by other filters in the filter chain.
 * <p>
 * If any runtime exception is caught during the filter chain execution, this filter
 * sets the response status to error code and returns a JSON response with the error message.
 */
@Component
@RequiredArgsConstructor
public class FilterExceptionHandler extends OncePerRequestFilter {

	/**
	 * The object mapper used to convert the {@code ApiResponse} object into a JSON response.
	 */
	private final ObjectMapper objectMapper;

	/**
	 * Filters the request and response objects, catching any runtime exceptions thrown by the
	 * filter chain and returning a JSON response with the error message.
	 *
	 * @param request     the HTTP servlet request object.
	 * @param response    the HTTP servlet response object.
	 * @param filterChain the filter chain to be executed.
	 * @throws ServletException if a servlet error occurs.
	 * @throws IOException      if an I/O error occurs.
	 */
	@Override
	protected void doFilterInternal(
					final HttpServletRequest request,
					final HttpServletResponse response,
					final FilterChain filterChain) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (RuntimeException e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);

			objectMapper.writeValue(response.getWriter(), new ApiResponse(false, e.getMessage()));
		}
	}
}
