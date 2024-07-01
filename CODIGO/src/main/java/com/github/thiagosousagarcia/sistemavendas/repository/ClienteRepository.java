package com.github.thiagosousagarcia.sistemavendas.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.thiagosousagarcia.sistemavendas.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	Page<Cliente> findByNomeContains(String nome, Pageable pageable);	
	
	Optional<Cliente> findByCpf(String cpf);
}
