/**
 * 
 */
package br.com.jumbo.enums;

/**
 * @author João Paulo
 *
 * 9 de jan. de 2023
 * 16:32:35
 */
public enum TipoVendaContaReceber {
	
	SITE("site"), 
	BALCAO("balcão");
	
	private String descricao;
	
	private TipoVendaContaReceber(String descricao) {
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
