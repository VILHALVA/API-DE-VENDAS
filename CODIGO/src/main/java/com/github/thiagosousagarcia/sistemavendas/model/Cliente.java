package com.github.thiagosousagarcia.sistemavendas.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.ClienteDTO;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.DTOConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "CLIENTE")
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "DATA_NASCIMENTO")
	private LocalDate dataNascimento;
	
	@Column(name = "CPF")
	private String cpf;
	
	@Column(name = "ENDERECO")
	private String endereco;
	
	@Column(name = "BAIRRO")
	private String bairro;
	
	@Column(name = "CIDADE")
	private String cidade;
	
	@Column(name = "COMPLEMENTO")
	private String complemento;
	
	@Column(name = "CEP")
	private String cep;
	
	@Column(name = "TELEFONE")
	private String telefone;
	
	@Column(name = "EMAIL")
	private String email;
	
	public ClienteDTO toDTO() {
		final ClienteDTO dto = DTOConverter.toObject(this, ClienteDTO.class);
		
		return dto;
	}
	
}
