package com.github.thiagosousagarcia.sistemavendas.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredenciaisDTO {
	
	@JsonProperty("Login")
	private String login;
	
	@JsonProperty("Senha")
	private String senha;
	
}
