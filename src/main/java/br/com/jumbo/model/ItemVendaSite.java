package br.com.jumbo.model;

import java.io.Serializable;

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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item_venda_site")
@SequenceGenerator(name = "seq_item_venda_site", sequenceName = "seq_item_venda_site", allocationSize = 1, initialValue = 1)
public class ItemVendaSite implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_venda_site")
	private Long id;

	@NotNull(message = "A Quantidade de Itens deve ser informada")
	@Column(nullable = false)
	private Double quantidade;

	@NotNull(message = "O ID do Produto deve ser informado")
	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "produto_fk"))
	private Produto produto;

	@NotNull(message = "O ID da Venda deve ser informado")
	@ManyToOne
	@JoinColumn(name = "venda_site_loja_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "venda_site_loja_fk"))
	private VendaSiteLoja vendaSiteLoja;

	@NotNull(message = "A Empresa deve ser informada")
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private PessoaJuridica empresa;

	public PessoaJuridica getEmpresa() {
		return empresa;
	}

	public void setEmpresa(PessoaJuridica empresa) {
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public VendaSiteLoja getVendaSiteLoja() {
		return vendaSiteLoja;
	}

	public void setVendaSiteLoja(VendaSiteLoja vendaSiteLoja) {
		this.vendaSiteLoja = vendaSiteLoja;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVendaSite other = (ItemVendaSite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
