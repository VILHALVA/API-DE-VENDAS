package com.github.thiagosousagarcia.sistemavendas.controller.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.thiagosousagarcia.sistemavendas.validation.NotEmptyList;

import lombok.Data;
import lombok.EqualsAndHashCode;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(of = {"id"})
public class VendaDTO {
	
	private Long id;
	
	@NotNull(message = "{codigo_cliente.obrigatorio}")
	@JsonProperty ("Cliente")
	private Long cliente;
	
	@NotNull(message = "{data_venda.obrigatorio}")
	@JsonProperty ("DataVenda")
	private LocalDate dataVenda;
	
	@NotEmptyList(message = "{itens.obrigatorio}")
	@JsonProperty("Itens")
	List<ItemVendaDTO> itens;
	
}
