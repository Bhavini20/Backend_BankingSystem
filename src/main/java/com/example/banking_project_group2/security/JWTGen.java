package com.example.banking_project_group2.security;


import java.security.Key;

//i
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.security.Keys;
@Component
public class JWTGen {
//	private String key = "---------------secret------------";
	private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	public String generateToken(Authentication auth) {
		String username = auth.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + 70000000);
		
		String token = Jwts.builder().setSubject(username).setIssuedAt(currentDate).
				setExpiration(expireDate)
//				.signWith(SignatureAlgorithm.HS512, key)
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	
		return token;
	}
	
	public String getUsernameFromToken(String token) {
		Claims claim = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		return claim.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
//			throw new AuthenticationCredentialsNotFoundException("JWT was expired")
			return false;		
		}
	}
	
}
