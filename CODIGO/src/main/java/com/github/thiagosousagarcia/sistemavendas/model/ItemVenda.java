package com.github.thiagosousagarcia.sistemavendas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "ITEM_VENDA")
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class ItemVenda {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "VENDA_ID")
	private Venda venda;
	
	@ManyToOne
	@JoinColumn(name = "PRODUTO_ID")
	private Produto produto;
	
	@Column(name = "QUANTIDADE")
	private Integer quantidade;
}
