/**
 * 
 */
package br.com.jumbo.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
import javax.validation.constraints.Min;

/**
 * @author João Paulo
 *
 *         27 de jun. de 2022 17:04:36
 */

@Entity
@Table(name = "vendedor")
@SequenceGenerator(name = "seq_vendedor", sequenceName = "seq_vendedor", allocationSize = 1, initialValue = 1)
public class Vendedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vendedor")
	private long id;

	@ManyToOne(targetEntity = PessoaFisica.class)
	@JoinColumn(name = "pessoa_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private PessoaFisica pessoa;

	@Min(value = 1, message = "Valor da comissão deve sder informada")
	@Column(nullable = false)
	private BigDecimal valorComissao;

	/**
	 * @return the pessoa
	 */
	public PessoaFisica getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return the valorComissao
	 */
	public BigDecimal getValorComissao() {
		return valorComissao;
	}

	/**
	 * @param valorComissao the valorComissao to set
	 */
	public void setValorComissao(BigDecimal valorComissao) {
		this.valorComissao = valorComissao;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Vendedor other = (Vendedor) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
