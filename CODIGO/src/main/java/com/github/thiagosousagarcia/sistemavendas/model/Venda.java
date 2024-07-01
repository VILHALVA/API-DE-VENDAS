package com.github.thiagosousagarcia.sistemavendas.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.github.thiagosousagarcia.sistemavendas.controller.dto.ClienteDTO;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.DTOConverter;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.DetalheItemVendaDTO;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.DetalheVendaDTO;
import com.github.thiagosousagarcia.sistemavendas.controller.dto.ProdutoDTO;
import com.github.thiagosousagarcia.sistemavendas.model.enums.StatusVenda;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "VENDA")
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Venda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente cliente;
	
	@Column(name =  "DATA_VENDA")
	private LocalDate dataVenda;
	
	@Column(name = "VALOR_VENDA")
	private BigDecimal valorVenda;
	
	@OneToMany(mappedBy = "venda")
	List<ItemVenda> itens;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	StatusVenda status;
	
	public DetalheVendaDTO toDetalheVendaDTO() {
		DetalheVendaDTO dto = new DetalheVendaDTO();
		List<DetalheItemVendaDTO> detalheItensDTO = new ArrayList<DetalheItemVendaDTO>();
		
		dto.setCliente(DTOConverter.toObject(this.cliente, ClienteDTO.class));
		dto.setDataVenda(this.dataVenda);
		dto.setValorVenda(this.valorVenda);
		dto.setStatus(status);
		
		if(this.itens != null) {
			this.itens.forEach(item -> {
				DetalheItemVendaDTO detalheItemVendaDTO = new DetalheItemVendaDTO();
				detalheItemVendaDTO.setProduto(DTOConverter.toObject(item.getProduto(), ProdutoDTO.class));
				detalheItemVendaDTO.setQuantidade(item.getQuantidade());
				
				detalheItensDTO.add(detalheItemVendaDTO);
			});
		}
		
		dto.setItens(detalheItensDTO);
		
		return dto;
	}
	
}
