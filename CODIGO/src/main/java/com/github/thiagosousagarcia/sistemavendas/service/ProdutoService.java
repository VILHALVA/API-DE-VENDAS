package com.github.thiagosousagarcia.sistemavendas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.thiagosousagarcia.sistemavendas.excpetion.ProdutoNotFoundException;
import com.github.thiagosousagarcia.sistemavendas.model.Produto;
import com.github.thiagosousagarcia.sistemavendas.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	
	public Optional<Produto> findById(Long id){
		return this.repository.findById(id);
	}
	
	public Produto save(Produto produto) {
		return this.repository.save(produto);
	}

	public Page<Produto> findAll(Pageable pageable) {
		return this.repository.findAll(pageable);
	}

	public Produto encontrarProdutoPeloID(Long id) {
		Produto produto = this.repository.findById(id)
										.orElseThrow(()->
											new ProdutoNotFoundException("Não foi encontrado nenhum produto com esse Código: " + id));

		return produto;
	}

	public Page<Produto> findByDescricaoContains(String descricao, Pageable pageable) {
		return this.repository.findByDescricaoContains(descricao, pageable);
	}

	public void deleteProduto(Produto produto) {
		this.repository.delete(produto);
	}

	public void deletarProdutoPeloID(Long id) {
		Produto produto = this.encontrarProdutoPeloID(id);

		this.deleteProduto(produto);
	}

	public Produto updateProduto(Long id, Produto produtoAtualizado) {
		Produto produto = this.encontrarProdutoPeloID(id);

		produtoAtualizado.setId(produto.getId());
		return this.save(produtoAtualizado);
	}

}
