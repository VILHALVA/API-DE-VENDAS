package com.github.thiagosousagarcia.sistemavendas.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.github.thiagosousagarcia.sistemavendas.security.service.UserService;

public class JwtFilter extends OncePerRequestFilter {

	private JwtService jwtService;
	private UserService userService;

	public JwtFilter(JwtService jwtService, UserService userService) {
		this.jwtService = jwtService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String auth = request.getHeader("Authorization");

		if (auth != null && auth.startsWith("Bearer")) {
			String token = auth.split(" ")[1];

			if (jwtService.isTokenValid(token)) {
				/* Colocando usuário dentro do contexto do Spring security */
				String login = jwtService.getUserLogin(token);
				UserDetails user = userService.loadUserByUsername(login);
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						user, null, user.getAuthorities());
				
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		filterChain.doFilter(request, response);
	}

}
