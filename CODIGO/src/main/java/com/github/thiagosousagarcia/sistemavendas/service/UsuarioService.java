package com.github.thiagosousagarcia.sistemavendas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.thiagosousagarcia.sistemavendas.model.Usuario;
import com.github.thiagosousagarcia.sistemavendas.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Usuario salvar(Usuario usuario) {
		String senhaCritpografada = this.passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCritpografada);
		
		return this.repository.save(usuario);
	}
	
	public Optional<Usuario> findByLogin(String login){
		return this.repository.findByLogin(login);
	}
	
	
	public Page<Usuario> findAll(Pageable pageable){
		return this.repository.findAll(pageable);
	}
	
}
