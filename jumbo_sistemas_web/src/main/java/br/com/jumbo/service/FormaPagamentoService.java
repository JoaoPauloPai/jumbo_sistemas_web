/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.FormaPagamento;
import br.com.jumbo.repository.FormaPagamentoRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:13:44
 */
@Service
public class FormaPagamentoService {

	@Autowired
	FormaPagamentoRepository formaPagamentoRepository;
	
	public FormaPagamento saave(FormaPagamento formaPagamento) {
		
		return
				formaPagamentoRepository.save(formaPagamento);
		
	}
}
