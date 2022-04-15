/**
 * 
 */
package br.com.jumbo.model.dto;

import java.io.Serializable;

/**
 * @author João Paulo
 *
 * 15 de abr. de 2022
 * 10:37:54
 */
public class CepDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String ibge;
	private String gia;
	private String ddd;
	private String siafi;
	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}
	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}
	/**
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}
	/**
	 * @param logradouro the logradouro to set
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}
	/**
	 * @param complemento the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}
	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	/**
	 * @return the localidade
	 */
	public String getLocalidade() {
		return localidade;
	}
	/**
	 * @param localidade the localidade to set
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	/**
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}
	/**
	 * @param uf the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}
	/**
	 * @return the ibge
	 */
	public String getIbge() {
		return ibge;
	}
	/**
	 * @param ibge the ibge to set
	 */
	public void setIbge(String ibge) {
		this.ibge = ibge;
	}
	/**
	 * @return the gia
	 */
	public String getGia() {
		return gia;
	}
	/**
	 * @param gia the gia to set
	 */
	public void setGia(String gia) {
		this.gia = gia;
	}
	/**
	 * @return the ddd
	 */
	public String getDdd() {
		return ddd;
	}
	/**
	 * @param ddd the ddd to set
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	/**
	 * @return the siafi
	 */
	public String getSiafi() {
		return siafi;
	}
	/**
	 * @param siafi the siafi to set
	 */
	public void setSiafi(String siafi) {
		this.siafi = siafi;
	}

	

	

}
