package br.com.jumbo.model;

/**
 * @author João Paulo
 *
 * 06 de dez. de 2021
 * 21:34:48
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.jumbo.enums.StatusContaReceber;
import br.com.jumbo.enums.TipoVendaContaReceber;

@Entity
@Table(name = "conta_receber")
@SequenceGenerator(name = "seq_conta_receber", sequenceName = "seq_conta_receber", initialValue = 1, allocationSize = 1)
public class ContaReceber implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_receber")
	private Long id;

	@NotNull(message = "A descrição da conta deve ser informado")
	@Column(nullable = false)
	private String descricao;

	@NotNull(message = "O Status da conta deve ser informado")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusContaReceber status;

	@NotNull(message = "A data de vencimento deve ser informado")
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtVencimento;

	@NotNull(message = "A data de Pagamento deve ser informado")
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtPagamento;

	@NotNull(message = "O valor total deve deve ser informado")
	@Column(nullable = false)
	private BigDecimal valorTotal;

	private BigDecimal valorDesconto;

	@NotNull(message = "O ID da pessoa deve ser informado")
	@ManyToOne(targetEntity = PessoaFisica.class)
	@JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private PessoaFisica pessoa;
	


	@NotNull(message = "A empresa deve ser informada")
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private PessoaJuridica empresa;

	@NotNull(message = "O tipo de Venda deve ser informado")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoVendaContaReceber tipo_venda;

	@NotNull(message = "O ID da venda deve ser informado")
	@Column(nullable = false)
	private Long vendaId;

	public Long getVendaId() {
		return vendaId;
	}

	public void setVendaId(Long vendaId) {
		this.vendaId = vendaId;
	}

	public TipoVendaContaReceber getTipo_venda() {
		return tipo_venda;
	}

	public void setTipo_venda(TipoVendaContaReceber tipo_venda) {
		this.tipo_venda = tipo_venda;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusContaReceber getStatus() {
		return status;
	}

	public void setStatus(StatusContaReceber status) {
		this.status = status;
	}

	public Date getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public Date getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(Date dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}



	public PessoaFisica getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
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
		ContaReceber other = (ContaReceber) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
