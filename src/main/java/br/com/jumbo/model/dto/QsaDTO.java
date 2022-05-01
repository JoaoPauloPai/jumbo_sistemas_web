/**
 * 
 */
package br.com.jumbo.model.dto;

import java.io.Serializable;

/**
 * @author João Paulo
 *
 *         1 de mai. de 2022 14:44:46
 */
public class QsaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String qual;
	private String pais_origem;
	private String nome_rep_legal;
	private String qual_rep_legal;
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the qual
	 */
	public String getQual() {
		return qual;
	}
	/**
	 * @param qual the qual to set
	 */
	public void setQual(String qual) {
		this.qual = qual;
	}
	/**
	 * @return the pais_origem
	 */
	public String getPais_origem() {
		return pais_origem;
	}
	/**
	 * @param pais_origem the pais_origem to set
	 */
	public void setPais_origem(String pais_origem) {
		this.pais_origem = pais_origem;
	}
	/**
	 * @return the nome_rep_legal
	 */
	public String getNome_rep_legal() {
		return nome_rep_legal;
	}
	/**
	 * @param nome_rep_legal the nome_rep_legal to set
	 */
	public void setNome_rep_legal(String nome_rep_legal) {
		this.nome_rep_legal = nome_rep_legal;
	}
	/**
	 * @return the qual_rep_legal
	 */
	public String getQual_rep_legal() {
		return qual_rep_legal;
	}
	/**
	 * @param qual_rep_legal the qual_rep_legal to set
	 */
	public void setQual_rep_legal(String qual_rep_legal) {
		this.qual_rep_legal = qual_rep_legal;
	}
	
	
}
