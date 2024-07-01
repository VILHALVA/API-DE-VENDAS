package com.github.thiagosousagarcia.sistemavendas.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.ClienteDTO;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.DTOConverter;
import com.github.thiagosousagarcia.sistemavendas.model.Cliente;
import com.github.thiagosousagarcia.sistemavendas.service.ClienteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@ApiOperation("Método que cadastra um novo cliente")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cliente cadastrado com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação")
	})
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@RequestBody @Valid ClienteDTO clienteDTO, final UriComponentsBuilder uriBuilder){
		Cliente novoCliente = this.clienteService.salvarCliente(clienteDTO.toEntity());
		
		ClienteDTO novoClienteDTO = novoCliente.toDTO();
		final Long id = novoClienteDTO.getId();
		final URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).body(novoClienteDTO);

	}
	
	@ApiOperation("Método que busca todos os clientes cadastrados")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Clientes carregados com sucesso")})
	@GetMapping
	public Page<ClienteDTO> findAll(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
		Page<Cliente> clientes = this.clienteService.findAll(pageable);
		
		return DTOConverter.toPage(clientes, ClienteDTO.class);
		
	}
	
	@ApiOperation("Método que busca todos os clientes cadastrados que possuem o nome (ou parte do nome) informado")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Clientes carregados com sucesso")})
	@GetMapping("/byNomeContains")
	public Page<ClienteDTO> findByNomeLike(@RequestParam String nome, @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
		Page<Cliente> clientes = this.clienteService.findByNomeContains(nome, pageable);
		
		return DTOConverter.toPage(clientes, ClienteDTO.class);
	}
	
	@ApiOperation("Método que busca cliente pelo ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cliente encontrado com sucesso"),
		@ApiResponse(code = 404, message = "Não existe cliente com o ID informado")})
	@GetMapping("/byId")
	public ResponseEntity<ClienteDTO> getClienteById(@RequestParam Long id){
		Cliente cliente = this.clienteService.encontrarClientePeloId(id);
		
		ClienteDTO clienteDTO = cliente.toDTO();
		return ResponseEntity.ok(clienteDTO);
		
	}
	
	@ApiOperation("Método que busca cliente pelo CPF")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cliente encontrado com sucesso"),
		@ApiResponse(code = 404, message = "Não existe cliente com o CPF informado")})
	@GetMapping("/byCpf")
	public ResponseEntity<ClienteDTO> getClienteByCpf(@RequestParam String cpf){
		Cliente cliente = this.clienteService.encontrarClientePeloCpf(cpf);
		
		ClienteDTO clienteDTO = cliente.toDTO();
		return ResponseEntity.ok(clienteDTO);

	}
	
	@ApiOperation("Exclui cliente pelo ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cliente excluído com sucesso"),
		@ApiResponse(code = 404, message = "Não existe cliente com o ID informado que possa ser excluído")
	})
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity deleteCliente(@PathVariable Long id) {
		
		this.clienteService.deletarClientePeloID(id);
		return ResponseEntity.noContent().build();
	
	}
	
	@ApiOperation("Atualiza cliente pelo ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cliente atualizado com sucesso"),
		@ApiResponse(code = 404, message = "Não existe cliente com o ID informado que possa ser atualizado")
	})
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) {
		Cliente clienteAtualizado = this.clienteService.updateCliente(id, clienteDTO.toEntity());
		
		return ResponseEntity.ok(clienteAtualizado.toDTO());
	
	}
	
}
