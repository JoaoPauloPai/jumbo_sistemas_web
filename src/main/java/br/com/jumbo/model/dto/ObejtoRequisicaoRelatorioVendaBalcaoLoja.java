/**
 * 
 */
package br.com.jumbo.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

/**
 * @author João Paulo
 *
 *         22 de fev. de 2023 20:57:32
 */
public class ObejtoRequisicaoRelatorioVendaBalcaoLoja implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Informa a data inicial")
	private String dataInicial;

	@NotEmpty(message = "Informa a data final")
	private String dataFinal;
	
	private String codigoVenda = "";
	private String dataVenda = "";
	private String valorVenda = "";
	private String quantProduto = "";
	private String nomeProduto = "";
	private String nomeVendedor = "";
	private String nomeCliente = "";
	private String tipoPagamento = "";

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getCodigoVenda() {
		return codigoVenda;
	}

	public void setCodigoVenda(String codigoVenda) {
		this.codigoVenda = codigoVenda;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(String valorVenda) {
		this.valorVenda = valorVenda;
	}

	public String getQuantProduto() {
		return quantProduto;
	}

	public void setQuantProduto(String quantProduto) {
		this.quantProduto = quantProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

}
