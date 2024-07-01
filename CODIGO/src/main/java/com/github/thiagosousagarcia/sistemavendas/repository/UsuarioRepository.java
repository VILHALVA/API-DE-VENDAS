package com.github.thiagosousagarcia.sistemavendas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thiagosousagarcia.sistemavendas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByLogin(String login);
}
