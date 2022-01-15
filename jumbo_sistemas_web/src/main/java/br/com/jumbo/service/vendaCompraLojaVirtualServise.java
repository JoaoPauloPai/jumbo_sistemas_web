/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.VendaCompraLojaVirtual;
import br.com.jumbo.repository.VendaCompraLojaVirtualRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:47:18
 */
@Service
public class vendaCompraLojaVirtualServise {
	
	@Autowired
	VendaCompraLojaVirtualRepository vendaCompraLojaVirtualRepository;
	
	public VendaCompraLojaVirtual save(VendaCompraLojaVirtual vendaCompraLojaVirtual) {
		
		return vendaCompraLojaVirtualRepository.save(vendaCompraLojaVirtual);
		
	}

}
