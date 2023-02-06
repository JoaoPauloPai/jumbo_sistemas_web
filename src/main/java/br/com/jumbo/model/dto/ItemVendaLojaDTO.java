/**
 * 
 */
package br.com.jumbo.model.dto;

import br.com.jumbo.model.ItemVendaSite;
import br.com.jumbo.model.Produto;
import br.com.jumbo.model.VendaSiteLoja;

/**
 * @author João Paulo
 *
 *         1 de jul. de 2022 21:20:26
 */
public class ItemVendaLojaDTO {

	private Long id;

	private Double quantidade;

	private Produto produto;

	private VendaSiteLoja vendaSite;

	private ItemVendaSite itemVendaSite;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VendaSiteLoja getVendaSite() {
		return vendaSite;
	}

	public void setVendaSite(VendaSiteLoja vendaSite) {
		this.vendaSite = vendaSite;
	}

	public ItemVendaSite getItemVendaSite() {
		return itemVendaSite;
	}

	public void setItemVendaSite(ItemVendaSite itemVendaSite) {
		this.itemVendaSite = itemVendaSite;
	}

}
