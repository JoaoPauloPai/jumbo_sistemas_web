/**
 * 
 */
package br.com.jumbo.model.dto;

import br.com.jumbo.model.Produto;
import br.com.jumbo.model.VendaBalcaoLoja;

/**
 * @author João Paulo
 *
 * 19 de jan. de 2023
 * 21:36:58
 */
public class ItemVendaBalcaoDTO {
	
	private Double quantidade;
	

	private Produto produto;
	
	private VendaBalcaoLoja vendaBalcaoLoja;

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public VendaBalcaoLoja getVendaBalcaoLoja() {
		return vendaBalcaoLoja;
	}

	public void setVendaBalcaoLoja(VendaBalcaoLoja vendaBalcaoLoja) {
		this.vendaBalcaoLoja = vendaBalcaoLoja;
	}
	
	
	
	

}
