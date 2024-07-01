package com.github.thiagosousagarcia.sistemavendas.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.DTOConverter;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.ProdutoDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "PRODUTO")
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "PRECO")
	private BigDecimal preco;
	
	public ProdutoDTO toDTO() {
		ProdutoDTO dto = DTOConverter.toObject(this,ProdutoDTO.class);
		
		return dto;
	}
}
