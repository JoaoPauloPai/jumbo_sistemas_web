package br.com.jumbo.model;

import java.io.Serializable;
import java.util.Objects;

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

@Entity
@Table(name = "item_venda_loja")
@SequenceGenerator(name = "seq_item_venda_loja", sequenceName = "seq_item_venda_loja", allocationSize = 1 , initialValue = 1)
public class ItemVendaLoja implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_venda_loja")
	private long Id;
	
	private Double quantidade;
	
	@ManyToOne(targetEntity = Produto.class)
	@JoinColumn(name = "produto_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "produto_fk"))
	private Produto produto;
	
	@ManyToOne(targetEntity = VdCpLojaVirtual.class)
	@JoinColumn(name = "vd_cp_loja_virtual_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "vd_cp_loja_virtual_fk"))
	private VdCpLojaVirtual vdCpLojaVirtual;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
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

	public VdCpLojaVirtual getVdCpLojaVirtual() {
		return vdCpLojaVirtual;
	}

	public void setVdCpLojaVirtual(VdCpLojaVirtual vdCpLojaVirtual) {
		this.vdCpLojaVirtual = vdCpLojaVirtual;
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
		ItemVendaLoja other = (ItemVendaLoja) obj;
		return Id == other.Id;
	}
	
	
}
