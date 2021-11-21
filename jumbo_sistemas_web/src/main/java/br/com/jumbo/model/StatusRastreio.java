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
@Table(name = "status_rastreio")
@SequenceGenerator(name = "seq_status_rastreio", sequenceName = "seq_status_rastreio", allocationSize = 1 , initialValue = 1)
public class StatusRastreio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_status_rastreio")
	private long Id;
	
    private String centroDistribuicao;
    private String cidade;
    private String estado;
    private String Status;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getCentroDistribuicao() {
		return centroDistribuicao;
	}
	public void setCentroDistribuicao(String centroDistribuicao) {
		this.centroDistribuicao = centroDistribuicao;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
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
		StatusRastreio other = (StatusRastreio) obj;
		return Id == other.Id;
	}
    
   
}
