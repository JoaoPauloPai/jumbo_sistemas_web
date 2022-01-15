/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.Endereco;
import br.com.jumbo.repository.EndereçoRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:11:14
 */
@Service
public class EnderecoService {
	
	@Autowired
	EndereçoRepository endereçoRepository;
	
	public Endereco save(Endereco endereco) {
		
		return endereçoRepository.save(endereco);
	}

}
