/**
 * 
 */
package br.com.jumbo.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author João Paulo
 *
 *         1 de mai. de 2022 14:41:57
 */
public class ConsultaCnpjDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<AtividadeDto> atividade_principal = new ArrayList<AtividadeDto>();

	private String data_situacao;
	private String tipo;
	private String nome;
	private String uf;
	private String telefone;
	private String email;

	private List<AtividadeDto> atividades_secundarias = new ArrayList<AtividadeDto>();

	private List<QsaDTO> qsa = new ArrayList<QsaDTO>();

	private String situacao;
	private String bairro;
	private String logradouro;
	private String numero;
	private String cep;
	private String municipio;
	private String porte;
	private String abertura;
	private String natureza_juridica;
	private String fantasia;
	private String cnpj;
	private String ultima_atualizacao;
	private String status;
	private String complemento;
	private String efr;
	private String motivo_situacao;
	private String situacao_especial;
	private String data_situacao_especial;
	private String capital_social;

	@JsonIgnore
	private ExtraDTO extra;

	private BillingDTO billing;

	/**
	 * @return the atividade_principal
	 */
	public List<AtividadeDto> getAtividade_principal() {
		return atividade_principal;
	}

	/**
	 * @param atividade_principal the atividade_principal to set
	 */
	public void setAtividade_principal(List<AtividadeDto> atividade_principal) {
		this.atividade_principal = atividade_principal;
	}

	/**
	 * @return the data_situacao
	 */
	public String getData_situacao() {
		return data_situacao;
	}

	/**
	 * @param data_situacao the data_situacao to set
	 */
	public void setData_situacao(String data_situacao) {
		this.data_situacao = data_situacao;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

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
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the atividades_secundarias
	 */
	public List<AtividadeDto> getAtividades_secundarias() {
		return atividades_secundarias;
	}

	/**
	 * @param atividades_secundarias the atividades_secundarias to set
	 */
	public void setAtividades_secundarias(List<AtividadeDto> atividades_secundarias) {
		this.atividades_secundarias = atividades_secundarias;
	}

	/**
	 * @return the qsa
	 */
	public List<QsaDTO> getQsa() {
		return qsa;
	}

	/**
	 * @param qsa the qsa to set
	 */
	public void setQsa(List<QsaDTO> qsa) {
		this.qsa = qsa;
	}

	/**
	 * @return the situacao
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao the situacao to set
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
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
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

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
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return the porte
	 */
	public String getPorte() {
		return porte;
	}

	/**
	 * @param porte the porte to set
	 */
	public void setPorte(String porte) {
		this.porte = porte;
	}

	/**
	 * @return the abertura
	 */
	public String getAbertura() {
		return abertura;
	}

	/**
	 * @param abertura the abertura to set
	 */
	public void setAbertura(String abertura) {
		this.abertura = abertura;
	}

	/**
	 * @return the natureza_juridica
	 */
	public String getNatureza_juridica() {
		return natureza_juridica;
	}

	/**
	 * @param natureza_juridica the natureza_juridica to set
	 */
	public void setNatureza_juridica(String natureza_juridica) {
		this.natureza_juridica = natureza_juridica;
	}

	/**
	 * @return the fantasia
	 */
	public String getFantasia() {
		return fantasia;
	}

	/**
	 * @param fantasia the fantasia to set
	 */
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	/**
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * @return the ultima_atualizacao
	 */
	public String getUltima_atualizacao() {
		return ultima_atualizacao;
	}

	/**
	 * @param ultima_atualizacao the ultima_atualizacao to set
	 */
	public void setUltima_atualizacao(String ultima_atualizacao) {
		this.ultima_atualizacao = ultima_atualizacao;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the efr
	 */
	public String getEfr() {
		return efr;
	}

	/**
	 * @param efr the efr to set
	 */
	public void setEfr(String efr) {
		this.efr = efr;
	}

	/**
	 * @return the motivo_situacao
	 */
	public String getMotivo_situacao() {
		return motivo_situacao;
	}

	/**
	 * @param motivo_situacao the motivo_situacao to set
	 */
	public void setMotivo_situacao(String motivo_situacao) {
		this.motivo_situacao = motivo_situacao;
	}

	/**
	 * @return the situacao_especial
	 */
	public String getSituacao_especial() {
		return situacao_especial;
	}

	/**
	 * @param situacao_especial the situacao_especial to set
	 */
	public void setSituacao_especial(String situacao_especial) {
		this.situacao_especial = situacao_especial;
	}

	/**
	 * @return the data_situacao_especial
	 */
	public String getData_situacao_especial() {
		return data_situacao_especial;
	}

	/**
	 * @param data_situacao_especial the data_situacao_especial to set
	 */
	public void setData_situacao_especial(String data_situacao_especial) {
		this.data_situacao_especial = data_situacao_especial;
	}

	/**
	 * @return the capital_social
	 */
	public String getCapital_social() {
		return capital_social;
	}

	/**
	 * @param capital_social the capital_social to set
	 */
	public void setCapital_social(String capital_social) {
		this.capital_social = capital_social;
	}

	/**
	 * @return the extra
	 */
	public ExtraDTO getExtra() {
		return extra;
	}

	/**
	 * @param extra the extra to set
	 */
	public void setExtra(ExtraDTO extra) {
		this.extra = extra;
	}

	/**
	 * @return the billing
	 */
	public BillingDTO getBilling() {
		return billing;
	}

	/**
	 * @param billing the billing to set
	 */
	public void setBilling(BillingDTO billing) {
		this.billing = billing;
	}

}
