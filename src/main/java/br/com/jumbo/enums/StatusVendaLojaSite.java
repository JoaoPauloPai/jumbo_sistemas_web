/**
 * 
 */
package br.com.jumbo.enums;

/**
 * @author João Paulo
 *
 * 17 de fev. de 2023
 * 20:25:12
 */
public enum StatusVendaLojaSite {
	
	FINALIZADA("Finalizada"),
	CANCELADA("Cancelada"),
	ABANDONOU_CARRINHO("Abandonou Carrinho");
	
	private String descricao = "";

	private StatusVendaLojaSite(String valor) {
		this.descricao = valor;
	}
	
	public void setDescricao(String descricao) {
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
