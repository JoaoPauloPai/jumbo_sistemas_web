/**
 * 
 */
package br.com.jumbo.model.dto;

import br.com.jumbo.model.Produto;

/**
 * @author João Paulo
 *
 * 1 de jul. de 2022
 * 21:20:26
 */
public class ItemVendaDTO {

	private Double quantidade;

	private Produto produto;

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

}
