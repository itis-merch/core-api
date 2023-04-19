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

@Component
@RequiredArgsConstructor
public class FilterExceptionHandler extends OncePerRequestFilter {
	private final ObjectMapper objectMapper;

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
