/**
 * 
 */
package br.com.jumbo.enums;

/**
 * @author João Paulo
 *
 * 9 de jan. de 2023
 * 10:25:45
 */
public enum StatusItemVendaLoja {

	
	VENDA_SITE("Venda_Site"),
	VENDA_BALCAO("Venda_Balcão");
	
	private String descricao;
	

	private StatusItemVendaLoja(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}

}
