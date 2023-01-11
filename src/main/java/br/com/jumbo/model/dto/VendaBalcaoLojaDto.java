/**
 * 
 */
package br.com.jumbo.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.jumbo.model.Pessoa;

/**
 * @author João Paulo
 *
 * 10 de jan. de 2023
 * 11:33:27
 */
public class VendaBalcaoLojaDto {
	
	private Long id;

	private BigDecimal valorTotal;

	private BigDecimal valorDesconto;
	
	private Pessoa pessoa;


	private List<ItemVendaDTO> itemVendaLoja = new ArrayList<ItemVendaDTO>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public BigDecimal getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}


	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}


	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public List<ItemVendaDTO> getItemVendaLoja() {
		return itemVendaLoja;
	}


	public void setItemVendaLoja(List<ItemVendaDTO> itemVendaLoja) {
		this.itemVendaLoja = itemVendaLoja;
	}
	
	
	

}
