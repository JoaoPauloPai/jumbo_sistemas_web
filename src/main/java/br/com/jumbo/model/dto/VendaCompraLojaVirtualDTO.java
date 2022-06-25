/**
 * 
 */
package br.com.jumbo.model.dto;

import java.math.BigDecimal;

/**
 * @author João Paulo
 *
 *         25 de jun. de 2022 16:04:07
 */
public class VendaCompraLojaVirtualDTO {

	private BigDecimal valorTotal;

	/**
	 * @return the valorTotal
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
