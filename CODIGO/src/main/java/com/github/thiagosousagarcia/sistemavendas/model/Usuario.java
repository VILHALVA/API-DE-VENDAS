package com.github.thiagosousagarcia.sistemavendas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.DTOConverter;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.UsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USUARIO")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "LOGIN")
	private String login;
	
	@Column(name = "SENHA")
	private String senha;
	
	@Column(name = "ADMIN")
	private boolean admin;
	
	public UsuarioDTO toDTO() {
		UsuarioDTO dto = DTOConverter.toObject(this,UsuarioDTO.class, "senha");
		
		return dto;
	}
}
