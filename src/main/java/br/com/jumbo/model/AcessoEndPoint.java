/**
 * 
 */
package br.com.jumbo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author João Paulo
 *
 *         23 de abr. de 2022 18:00:30
 */
@Entity
@Table(name = "acesso_end_point")
@SequenceGenerator(name = "seq_acesso_end_point", sequenceName = "seq_acesso_end_point", allocationSize = 1, initialValue = 1)
public class AcessoEndPoint implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acesso_end_point")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String nomeEndPoint;

	private Integer qntdAcesso;

	public String getNomeEndPoint() {
		return nomeEndPoint;
	}

	public void setNomeEndPoint(String nomeEndPoint) {
		this.nomeEndPoint = nomeEndPoint;
	}

	public Integer getQntdAcesso() {
		return qntdAcesso;
	}

	public void setQntdAcesso(Integer qntdAcesso) {
		this.qntdAcesso = qntdAcesso;
	}

}
