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
@Table(name = "forma_pagamento")
@SequenceGenerator(name = "seq_forma_pagam", sequenceName = "seq_forma_pagam", allocationSize = 1 , initialValue = 1)
public class FormaPagamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_forma_pagam")
	private Long Id;
	
	@Column(nullable = false)
	private String pagamDesc;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getPagamDesc() {
		return pagamDesc;
	}

	public void setPagamDesc(String pagamDesc) {
		this.pagamDesc = pagamDesc;
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
		FormaPagamento other = (FormaPagamento) obj;
		return Objects.equals(Id, other.Id);
	}

}
