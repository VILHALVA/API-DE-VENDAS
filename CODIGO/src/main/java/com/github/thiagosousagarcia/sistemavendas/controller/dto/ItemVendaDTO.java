package com.github.thiagosousagarcia.sistemavendas.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"id"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemVendaDTO {
	
	private Long id;
	
	@JsonProperty("Produto")
	private Long produto;
	
	@JsonProperty("Quantidade")
	private Integer quantidade;
	
}
