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

import com.github.thiagosousagarcia.sistemavendas.controller.dto.DTOConverter;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.ProdutoDTO;
import com.github.thiagosousagarcia.sistemavendas.model.Produto;
import com.github.thiagosousagarcia.sistemavendas.service.ProdutoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService service;
	
	@ApiOperation("Cadastra um novo produto")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Produto cadastrado com sucesso")
	})
	@PostMapping
	public ResponseEntity<ProdutoDTO> create(@RequestBody @Valid ProdutoDTO produtoDTO, final UriComponentsBuilder uriBuilder){
		Produto novoProduto = this.service.save(produtoDTO.toEntity());
		ProdutoDTO novoProdutoDTO = novoProduto.toDTO();
		final Long id = novoProdutoDTO.getId();
		final URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).body(novoProdutoDTO);
	}
	
	@ApiOperation("Busca todos os produtos cadastrados")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Produtos carregados com sucesso")
	})
	@GetMapping
	public Page<ProdutoDTO> findAll(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
		Page<Produto> produtos = this.service.findAll(pageable);
		
		return DTOConverter.toPage(produtos, ProdutoDTO.class);
	}
	
	@ApiOperation("Busca produto pelo ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cliente encontrado com sucesso"),
		@ApiResponse(code = 404, message = "Não existe produto com o ID informado")
	})
	@GetMapping("byId")
	public ResponseEntity<ProdutoDTO> findById(@RequestParam Long id){
		Produto produto = this.service.encontrarProdutoPeloID(id);

		ProdutoDTO produtoDTO = produto.toDTO();
		return ResponseEntity.ok(produtoDTO);
	}
	
	@ApiOperation("Busca todos os produtos cadastrados que possuem o(parte) da descrição informado")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Produtos carregados com sucesso")
	})
	@GetMapping("byDescricaoContains")
	public Page<ProdutoDTO> findByDescricaoContains(@RequestParam String descricao, @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable){
		Page<Produto> produtos = this.service.findByDescricaoContains(descricao, pageable);
		
		return DTOConverter.toPage(produtos, ProdutoDTO.class);
	}
	
	@ApiOperation("Atualiza produto pelo ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Produto atualizado com sucesso"),
		@ApiResponse(code = 404, message = "Não existe produto com o ID informado que possa ser atualizado")
	})
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDTO> updateProduto(@PathVariable Long id, @RequestBody @Valid ProdutoDTO produtoDTO){
		Produto produtoAtualizado = this.service.updateProduto(id, produtoDTO.toEntity());
		
		return ResponseEntity.ok(produtoAtualizado.toDTO());
	}
	
	@ApiOperation("Exclui produto pelo ID")
	@ApiResponses({
		@ApiResponse(code = 204,message = "Produto excluído com sucesso"),
		@ApiResponse(code = 404, message = "Não existe produto com o ID informado que possa ser excluído")
	})
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity deleteProduto(@PathVariable Long id) {
		this.service.deletarProdutoPeloID(id);
		return ResponseEntity.noContent().build();	
	}
}
