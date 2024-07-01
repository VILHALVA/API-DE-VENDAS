package com.github.thiagosousagarcia.sistemavendas.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.thiagosousagarcia.sistemavendas.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	
	@Query( value = "Select venda "
						+ "from Venda venda "
						+ "join fetch venda.itens", 
			countQuery = "Select count(venda) "
						+ "from Venda venda")
	Page<Venda> findAll(Pageable pageable);
	
	@Query( value = "Select venda "
						+ "from Venda venda "
						+ "join fetch venda.itens "
					+ "where venda.cliente.cpf = :cpf", 
			countQuery = "Select count(venda)"
							+ " from Venda venda"
						 + " where venda.cliente.cpf = :cpf")
	Page<Venda> findByClienteCpf (@Param(value = "cpf") String cpf, Pageable pageable);
	
	@Query( value = "Select venda"
						+ " from Venda venda"
						+ " join fetch venda.itens"
					+ " where venda.id = :id")
	Optional<Venda> findById(@Param(value = "id") Long id);
	
}
