package com.ipartek.componente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String path = request.getServletPath();
		return path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs") || path.startsWith("/api-docs")
				|| path.startsWith("/swagger-resources") || path.startsWith("/webjars") || path.startsWith("/api/auth")
				||

				path.equals("/api/v1/usuarios/validar/") ||

				path.equals("/login") || path.equals("/register");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);

			if (jwtUtil.isTokenValid(token)) {
				Claims claims = jwtUtil.extractClaims(token);
				String username = claims.getSubject();
				String rol = claims.get("rol", String.class);

				List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + rol));
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
						authorities);

				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		try {
			if (SecurityContextHolder.getContext().getAuthentication() != null) {
			    System.out.println("DEBUG - Usuario: " + SecurityContextHolder.getContext().getAuthentication().getName());
			    System.out.println("DEBUG - Autoridades: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
			} else {
			    System.out.println("DEBUG - No hay nadie autenticado");
			}
			
			chain.doFilter(request, response); // le puse trycatch
		} catch (java.io.IOException | ServletException e) {
			e.printStackTrace();
		}
	}
}