/**
 * 
 */
package br.com.jumbo.enums;

/**
 * @author João Paulo
 *
 *         1 de mai. de 2022 15:04:03
 */
public enum TipoPessoa {

	JURIDICA("Jurídica"), 
	JURIDICA_FORNECEDOR("Jurídica e Fornecedor"),
	FISICA("Física");

	private String descricao;

	private TipoPessoa(String descrica) {
		this.descricao = descrica;

	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return this.descricao;
	}

}
