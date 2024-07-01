package com.github.thiagosousagarcia.sistemavendas.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
	
	@JsonProperty("Login")
	private String login;
	
	@JsonProperty("Token")
	private String token;
	
}
