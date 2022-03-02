package br.com.jumbo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "produto")
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1, initialValue = 1)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
	private long Id;

	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private Pessoa empresa;

	@Column(nullable = false)
	private String tipoUnidade;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private Boolean ativo = Boolean.TRUE;

	@Column(columnDefinition = "text", length = 2000, nullable = false)
	private String descricao;

	@Column(nullable = false)
	private Double peso;

	@Column(nullable = false)
	private Double largura;

	@Column(nullable = false)
	private Double Altura;

	@Column(nullable = false)
	private Double profundidade;

	@Column(nullable = false)
	private BigDecimal valorVenda = BigDecimal.ZERO;

	@Column(nullable = false)
	private Integer qtdEstoque;

	private Integer qtdAlertaEstoque;

	private String linkYoutube;

	private Boolean alertaQtdEstoque = Boolean.FALSE;

	private Integer qtdClique;

	/**
	 * @return the id
	 */
	public long getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		Id = id;
	}

	/**
	 * @return the empresa
	 */
	public Pessoa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(Pessoa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the tipoUnidade
	 */
	public String getTipoUnidade() {
		return tipoUnidade;
	}

	/**
	 * @param tipoUnidade the tipoUnidade to set
	 */
	public void setTipoUnidade(String tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
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
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the peso
	 */
	public Double getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}

	/**
	 * @return the largura
	 */
	public Double getLargura() {
		return largura;
	}

	/**
	 * @param largura the largura to set
	 */
	public void setLargura(Double largura) {
		this.largura = largura;
	}

	/**
	 * @return the altura
	 */
	public Double getAltura() {
		return Altura;
	}

	/**
	 * @param altura the altura to set
	 */
	public void setAltura(Double altura) {
		Altura = altura;
	}

	/**
	 * @return the profundidade
	 */
	public Double getProfundidade() {
		return profundidade;
	}

	/**
	 * @param profundidade the profundidade to set
	 */
	public void setProfundidade(Double profundidade) {
		this.profundidade = profundidade;
	}

	/**
	 * @return the valorVenda
	 */
	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	/**
	 * @param valorVenda the valorVenda to set
	 */
	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	/**
	 * @return the qtdEstoque
	 */
	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	/**
	 * @param qtdEstoque the qtdEstoque to set
	 */
	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	/**
	 * @return the qtdAlertaEstoque
	 */
	public Integer getQtdAlertaEstoque() {
		return qtdAlertaEstoque;
	}

	/**
	 * @param qtdAlertaEstoque the qtdAlertaEstoque to set
	 */
	public void setQtdAlertaEstoque(Integer qtdAlertaEstoque) {
		this.qtdAlertaEstoque = qtdAlertaEstoque;
	}

	/**
	 * @return the linkYoutube
	 */
	public String getLinkYoutube() {
		return linkYoutube;
	}

	/**
	 * @param linkYoutube the linkYoutube to set
	 */
	public void setLinkYoutube(String linkYoutube) {
		this.linkYoutube = linkYoutube;
	}

	/**
	 * @return the alertaQtdEstoque
	 */
	public Boolean getAlertaQtdEstoque() {
		return alertaQtdEstoque;
	}

	/**
	 * @param alertaQtdEstoque the alertaQtdEstoque to set
	 */
	public void setAlertaQtdEstoque(Boolean alertaQtdEstoque) {
		this.alertaQtdEstoque = alertaQtdEstoque;
	}

	/**
	 * @return the qtdClique
	 */
	public Integer getQtdClique() {
		return qtdClique;
	}

	/**
	 * @param qtdClique the qtdClique to set
	 */
	public void setQtdClique(Integer qtdClique) {
		this.qtdClique = qtdClique;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Id == other.Id;
	}

}
