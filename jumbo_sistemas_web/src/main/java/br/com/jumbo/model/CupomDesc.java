package br.com.jumbo.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cupom_desc")
@SequenceGenerator(name = "seq_cupom_desc", sequenceName = "seq_cupom_desc", allocationSize = 1 , initialValue = 1)
public class CupomDesc implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cupom_desc")
	private long Id;
	
	private String codDesconto;
	private Double valorRealDesc;
	private Double valorPercDesc;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getCodDesconto() {
		return codDesconto;
	}
	public void setCodDesconto(String codDesconto) {
		this.codDesconto = codDesconto;
	}
	public Double getValorRealDesc() {
		return valorRealDesc;
	}
	public void setValorRealDesc(Double valorRealDesc) {
		this.valorRealDesc = valorRealDesc;
	}
	public Double getValorPercDesc() {
		return valorPercDesc;
	}
	public void setValorPercDesc(Double valorPercDesc) {
		this.valorPercDesc = valorPercDesc;
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
		CupomDesc other = (CupomDesc) obj;
		return Id == other.Id;
	}
	
}
