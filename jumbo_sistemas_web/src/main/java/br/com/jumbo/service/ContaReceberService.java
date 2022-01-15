/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.ContaReceber;
import br.com.jumbo.repository.ContaPagarRepository;
import br.com.jumbo.repository.ContaReceberRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:01:30
 */
@Service
public class ContaReceberService {
	
	@Autowired
	private ContaReceberRepository contaReceberRepository;
	
	public ContaReceber save(ContaReceber contaReceber) {
		
		return contaReceberRepository.save(contaReceber);
		
	}

}
