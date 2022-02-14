/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.ContaPagar;
import br.com.jumbo.repository.ContaPagarRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 19:57:07
 */
@Service
public class ContaPagarService {
	
	@Autowired
	private ContaPagarRepository contaPagarRepository;
	
	public ContaPagar save(ContaPagar contaPagar) {
		
		return contaPagarRepository.save(contaPagar);
	}

}
