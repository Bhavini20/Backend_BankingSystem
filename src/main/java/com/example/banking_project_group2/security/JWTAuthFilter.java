package com.example.banking_project_group2.security;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.banking_project_group2.service.CustomerAuthService;

public class JWTAuthFilter extends OncePerRequestFilter {

	
	@Autowired
	private JWTGen jwtgen;
	
	@Autowired
	private CustomerAuthService customUserDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String token = getJWTFromRequest(request);
		System.out.println(token);
		if(StringUtils.hasText(token) && jwtgen.validateToken(token)) {
			String username = jwtgen.getUsernameFromToken(token);
			
			UserDetails user = customUserDetailService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	private String getJWTFromRequest(HttpServletRequest request) {
		String bearer = request.getHeader("Authorization");
		System.out.println(bearer);
		if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
			return bearer.substring(7, bearer.length());
			
//			UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
			
		}
		return "";
	}

		
	
}
