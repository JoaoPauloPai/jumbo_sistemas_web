/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.NotaFiscalCompra;
import br.com.jumbo.repository.NotaFiscalCompraRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:33:57
 */
@Service
public class NotaFiscalCompraService {
	
	@Autowired
	NotaFiscalCompraRepository notaFiscalCompraRepository;
	
	public NotaFiscalCompra save(NotaFiscalCompra notaFiscalCompra) {
		
		return notaFiscalCompraRepository.save(notaFiscalCompra);
	}

}
