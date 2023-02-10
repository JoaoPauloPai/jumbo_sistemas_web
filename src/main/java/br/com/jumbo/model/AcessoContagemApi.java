/**
 * 
 */
package br.com.jumbo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author João Paulo
 *
 *         9 de fev. de 2023 19:40:31
 */

@Entity
@Table(name = "acesso_contagem_api")
@SequenceGenerator(name = "seq_acesso_contagem_api", sequenceName = "seq_acesso_contagem_api", allocationSize = 1, initialValue = 1)

public class AcessoContagemApi implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acesso_contagem_api")
	private Long id;

	@NotNull(message = "E obrigatório informar o nome-end-point")
	@Column(nullable = false)
	private String nome_end_point;

	@Column(nullable = false)
	private Integer qtde_acesso_end_point;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome_end_point() {
		return nome_end_point;
	}

	public void setNome_end_point(String nome_end_point) {
		this.nome_end_point = nome_end_point;
	}

	public Integer getQtde_acesso_end_point() {
		return qtde_acesso_end_point;
	}

	public void setQtde_acesso_end_point(Integer qtde_acesso_end_point) {
		this.qtde_acesso_end_point = qtde_acesso_end_point;
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
		AcessoContagemApi other = (AcessoContagemApi) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
