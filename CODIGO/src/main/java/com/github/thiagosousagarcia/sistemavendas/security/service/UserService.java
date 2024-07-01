package com.github.thiagosousagarcia.sistemavendas.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.thiagosousagarcia.sistemavendas.excpetion.InvalidPasswordException;
import com.github.thiagosousagarcia.sistemavendas.model.Usuario;
import com.github.thiagosousagarcia.sistemavendas.service.UsuarioService;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UsuarioService usuarioService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioService.findByLogin(username)
								.orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado"));
		
		String[] rolesUsuario = usuario.isAdmin() ? 
									new String[] {"ADMIN","USER"} : new String[] {"USER"} ;
		
		return User.
					builder().
					username(usuario.getLogin()).
					password(usuario.getSenha()).
					roles(rolesUsuario).
					build();
	}
	
	public UserDetails autenticate(Usuario usuario) {
		UserDetails user = loadUserByUsername(usuario.getLogin());
		if(passwordEncoder.matches(usuario.getSenha(), user.getPassword())) {
			return user;
		}
		
		throw new InvalidPasswordException("A senha está invalida");
	}

}
