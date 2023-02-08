/**
 * 
 */
package br.com.jumbo.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.jumbo.model.Endereco;
import br.com.jumbo.model.Pessoa;

/**
 * @author João Paulo
 *
 *         25 de jun. de 2022 16:04:07
 */
public class VendaSiteLojaDTO {

	private Long id;

	private BigDecimal valorTotal;

	private BigDecimal valorDesc;

	private Pessoa pessoa;

	private Endereco cobranca;

	private Endereco entrega;

	private BigDecimal valorFrete;

	private List<ItemVendaSiteDTO> itemVendaLoja = new ArrayList<ItemVendaSiteDTO>();

	public void setItemVendaLoja(List<ItemVendaSiteDTO> itemVendaLoja) {
		this.itemVendaLoja = itemVendaLoja;
	}

	public List<ItemVendaSiteDTO> getItemVendaLoja() {
		return itemVendaLoja;
	}
	
	

	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public BigDecimal getValorDesc() {
		return valorDesc;
	}

	public void setValorDesc(BigDecimal valorDesc) {
		this.valorDesc = valorDesc;
	}

	public Endereco getCobranca() {
		return cobranca;
	}

	public void setCobranca(Endereco cobranca) {
		this.cobranca = cobranca;
	}

	public Endereco getEntrega() {
		return entrega;
	}

	public void setEntrega(Endereco entrega) {
		this.entrega = entrega;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

}
