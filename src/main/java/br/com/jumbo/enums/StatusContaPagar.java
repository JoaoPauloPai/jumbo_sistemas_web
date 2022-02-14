package br.com.jumbo.enums;
/**
 * @author João Paulo
 *
 * 01 de dez. de 2021
 * 21:34:48
 */

public enum StatusContaPagar {
	
	
	COBRANCA("Pagar"),
	VENCIDA("Vencida"),
	ABERTA("Aberta"),
	QUITADA("Quitada"),
	ALUGUEL("Aluguel"),
	FUNCIONARIO("Funcionário"),
	NEGOCIADA("Renegociada");
	
	
	private String descricao;
	
	private StatusContaPagar(String descricao) {
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
