package com.github.thiagosousagarcia.sistemavendas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.thiagosousagarcia.sistemavendas.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	Page<Produto> findByDescricaoContains(String descricao,Pageable pageable);
	
}
