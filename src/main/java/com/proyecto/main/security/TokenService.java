package com.proyecto.main.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class TokenService {

	public String createJWT(String usuario,String role) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secreto");
		    String token = JWT.create()
		    	.withSubject(usuario)
		        .withIssuer("gestion_usuarios")
				.withClaim("role", role)
		        .withExpiresAt(this.getExpireInstant())
		        .sign(algorithm);
		    return token;
		} catch (JWTCreationException exception){
			throw new RuntimeException(exception);
		}
	}
	
	public String validarToken(String token) {
		DecodedJWT decodedJWT;
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secreto");
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("gestion_usuarios")
		        .build();
		        
		    decodedJWT = verifier.verify(token);
		} catch (JWTVerificationException exception){
		    throw new RuntimeException(exception);
		}
		return decodedJWT.getSubject();
	}
	
	private Instant getExpireInstant() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
	}
}
