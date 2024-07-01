package com.github.thiagosousagarcia.sistemavendas.controller.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.thiagosousagarcia.sistemavendas.model.Cliente;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"id"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {
	
	private Long id;
	
	@NotBlank(message = "{nome.obrigatorio}")
	@JsonProperty("Nome")
	private String nome;
	
	@NotNull(message = "{data_nascimento.obrigatorio}")
	@JsonFormat(pattern = "dd-MM-yyyy")
	@JsonProperty("DataNascimento")
	private LocalDate dataNascimento;
	
	@NotBlank(message = "{cpf.obrigatorio}")
	@CPF(message = "{cpf.valido}")
	@JsonProperty("Cpf")
	private String cpf;
	
	@NotBlank(message = "{endereco.obrigatorio}")
	@JsonProperty("Endereco")
	private String endereco;
	
	@NotBlank(message = "{bairro.obrigatorio}")
	@JsonProperty("Bairro")
	private String bairro;
	
	@NotBlank(message = "{cidade.obrigatorio}")
	@JsonProperty("Cidade")
	private String cidade;
	
	@JsonProperty("Complemento")
	private String complemento;
	
	@NotBlank(message = "{cep.obrigatorio}")
	@JsonProperty("Cep")
	private String cep;
	
	@NotBlank(message = "{telefone.obrigatorio}")
	@JsonProperty("Telefone")
	private String telefone;
	
	@NotBlank(message = "{email.obrigatorio}")
	@Email(message = "{email.valido}")
	@JsonProperty("Email")
	private String email;
	
	public Cliente toEntity() {
		final Cliente cliente = DTOConverter.toObject(this, Cliente.class);
		
		return cliente;
	}
}
