/**
 * 
 */
package br.com.jumbo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author João Paulo
 *
 * 27 de jun. de 2022
 * 16:55:41
 */
public class VendaBalcaoLoja implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private PessoaFisica pessoa;
	
	private Long VendedorId;
	
	private BigDecimal valorTotal;
	
	private BigDecimal valorDesconto;
	
	private FormaPagamento formaPagamento;
		
	private Date dataVenda;
	
	private PessoaJuridica empresa;

}
