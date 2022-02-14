/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.MarcaProduto;
import br.com.jumbo.repository.MarcaProdutoRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:23:57
 */
@Service
public class MarcaProdutoSevice {
	
	@Autowired
	MarcaProdutoRepository marcaProdutoRepository;
	
	public MarcaProduto save(MarcaProduto marcaProduto) {
		
		return marcaProdutoRepository.save(marcaProduto);
				
	}

}
