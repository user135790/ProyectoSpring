package com.proyecto.main.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.proyecto.main.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthorizationFilter extends OncePerRequestFilter{

	@Autowired
	TokenService tokenService;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String auth = request.getHeader("Authorization");
		if(auth != null) {
			String token = auth.replace("Bearer ", "");
			String subject = tokenService.validarToken(token);
			
			if(subject != null) {
				UserDetails usuario= usuarioRepository.findByNombre(subject);
				Authentication authentication = new UsernamePasswordAuthenticationToken(usuario.getUsername(), 
						null, usuario.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
		}
		filterChain.doFilter(request, response);
	}

}
