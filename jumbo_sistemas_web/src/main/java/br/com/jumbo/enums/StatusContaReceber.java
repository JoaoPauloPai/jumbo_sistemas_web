package br.com.jumbo.enums;
/**
 * @author João Paulo
 *
 * 21 de dez. de 2021
 * 21:34:48
 */

public enum StatusContaReceber {
	
	
	COBRANCA("Pagar"),
	VENCIDA("Vencida"),
	ABERTA("Aberta"),
	QUITADA("Quitada");
	
	private String descricao;
	
	private StatusContaReceber(String descricao) {
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
