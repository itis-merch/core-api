package com.itis.merch.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * This class provides utility methods for working with JWT (JSON Web Tokens).
 */
@Service
public class JWTUtilService {

	/**
	 * The secret key used to sign and verify JWTs.
	 */
	@Value("${jwt.secret}")
	private String SECRET_KEY;

	@Value("${jwt.expiration.time}")
	private long EXPIRATION_TIME;


	/**
	 * Extracts the username from the given token.
	 *
	 * @param token the JWT token to extract the username from
	 * @return the username extracted from the token
	 */
	public String extractUsername(final String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Extracts the expiration date from the given token.
	 *
	 * @param token the JWT token to extract the expiration date from
	 * @return the expiration date extracted from the token
	 */
	public Date extractExpiration(final String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	/**
	 * Extracts a claim from the given token using the specified claims resolver function.
	 *
	 * @param token          the JWT token to extract the claim from
	 * @param claimsResolver the function used to resolve the claim
	 * @param <T>            the type of the claim
	 * @return the claim extracted from the token
	 */
	public <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaim(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * Extracts all claims from the given token.
	 *
	 * @param token the JWT token to extract the claims from
	 * @return all claims extracted from the token
	 */
	private Claims extractAllClaim(final String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	/**
	 * Checks if the given token is expired.
	 *
	 * @param token the JWT token to check for expiration
	 * @return true if the token is expired, false otherwise
	 */
	private boolean isTokenExpired(final String token) {
		return extractExpiration(token).before(new Date());
	}

	/**
	 * Generates a JWT token for the given user details.
	 *
	 * @param userDetails the user details to generate the token for
	 * @return the generated JWT token
	 */
	@Deprecated
	public String generateToken(final UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	/**
	 * Generates a JWT token for the given user details.
	 *
	 * @param emailAddress the user e-mail address to generate the token
	 *                     for.
	 * @param role         the user role to be encoded in the JWT.
	 * @return the generated JWT token
	 */
	public String generateToken(final String emailAddress, final String role) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", role);
		return createToken(claims, emailAddress);
	}

	/**
	 * Creates a JWT based on the provided claims and subject.
	 *
	 * @param claims  The claims to include in the JWT.
	 * @param subject The subject of the JWT.
	 * @return The JWT as a string.
	 */
	public String createToken(final Map<String, Object> claims, final String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
						.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	/**
	 * Validates the provided JWT against the provided user details.
	 *
	 * @param token       The JWT to validate.
	 * @param userDetails The user details to validate the JWT against.
	 * @return True if the JWT is valid, false otherwise.
	 */
	public Boolean validateToken(final String token, final UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
