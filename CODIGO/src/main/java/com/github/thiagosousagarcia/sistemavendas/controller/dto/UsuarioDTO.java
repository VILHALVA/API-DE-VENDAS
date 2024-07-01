package com.github.thiagosousagarcia.sistemavendas.controller.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.thiagosousagarcia.sistemavendas.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode (of = {"id"})
public class UsuarioDTO {
	
	private Long id;
	
	@NotBlank(message = "{login.obrigatorio}")
	@JsonProperty("Login")
	private String login;
	
	@NotBlank(message = "{senha.obrigatorio}")
	@JsonProperty("Senha")
	private String senha;
	
	@JsonProperty("Admin")
	private boolean admin;
	
	public Usuario toEntity() {
		Usuario usuario = DTOConverter.toObject(this, Usuario.class);
		
		return usuario;
	}
	
}
