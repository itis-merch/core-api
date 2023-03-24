package com.itis.merch.core.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * We use this class in order to standardize the API response that
 * the server returns when the client requests.
 * <p>
 * The {@code ApiResponse} class can be used, for example, in
 * controllers to form a response to a client calling a specific
 * endpoint. For instance, if a client requests a resource that
 * does not exist on the server, the controller can return an {@code
 * ApiResponse} object with the code {@code 404} and a message
 * indicating that the requested resource was not found.
 * <p>
 * Additionally, the {@code ApiResponse} class can be used in
 * exception handlers that may occur during request processing.
 * In this case, the {@code ApiResponse} class can contain an error
 * code and a message describing the error that will be returned to
 * the client in response to the request.
 */
@AllArgsConstructor
@Data
public final class ApiResponse {

	/*----- Private fields -----*/

	/**
	 * HTTP code that server returns in response to a request from
	 * the client.
	 */
	private final Integer code;

	/**
	 * Additional response status information that can be useful for
	 * the client.
	 */
	private final String message;

}
