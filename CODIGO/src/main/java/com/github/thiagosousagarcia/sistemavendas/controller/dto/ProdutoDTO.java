package com.github.thiagosousagarcia.sistemavendas.controller.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.thiagosousagarcia.sistemavendas.model.Produto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(of = {"id"})
public class ProdutoDTO {
	
	private Long id;
	
	@NotBlank(message = "{descricao.obrigatorio}")
	@JsonProperty("Descricao")
	private String descricao;
	
	@NotNull(message = "{preco.obrigatorio}")
	@JsonProperty("Preco")
	private BigDecimal preco;
	
	public Produto toEntity() {
		Produto produto = DTOConverter.toObject(this, Produto.class);
		
		return produto;
	}
	
}
