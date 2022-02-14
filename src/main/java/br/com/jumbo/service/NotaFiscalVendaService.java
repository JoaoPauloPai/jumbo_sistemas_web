/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.NotaFiscalVenda;
import br.com.jumbo.repository.NotaFiscalVendaRepository;

/**
 * @author João Paulo
 *
 * 30 de jan. de 2022
 * 14:04:59
 */
@Service
public class NotaFiscalVendaService {
	
	@Autowired
	NotaFiscalVendaRepository notaFiscalVendaRepository;
	
	public NotaFiscalVenda save(NotaFiscalVenda notaFiscalVenda) {
		
		return notaFiscalVendaRepository.save(notaFiscalVenda);
	}

}
