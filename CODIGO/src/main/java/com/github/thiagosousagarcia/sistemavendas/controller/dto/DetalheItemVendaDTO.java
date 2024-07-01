package com.github.thiagosousagarcia.sistemavendas.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DetalheItemVendaDTO {
	
	@JsonProperty("Produto")
	private ProdutoDTO produto;
	
	@JsonProperty("Quantidade")
	private Integer quantidade;
}
