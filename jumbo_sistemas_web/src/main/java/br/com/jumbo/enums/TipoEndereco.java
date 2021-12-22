package br.com.jumbo.enums;
/**
 * @author João Paulo
 *
 * 21 de dez. de 2021
 * 21:34:48
 */

public enum TipoEndereco {
	
	COBRANCA("Cobrança"),
	ENTREGA("Entrega");
	

	private String descricao;

	
	private TipoEndereco(String descricao){
		this.descricao = descricao;
		
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		
		return this.toString();
	}
}
 