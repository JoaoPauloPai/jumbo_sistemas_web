/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.PessoaJuridica;
import br.com.jumbo.repository.PessoaJuridicaRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:38:41
 */
@Service
public class PessoaJuridicaService {
	
	@Autowired
	PessoaJuridicaRepository pessoaJuridicaRepository;
	
	public PessoaJuridica save(PessoaJuridica pessoaJuridica) {
		
		
		return pessoaJuridicaRepository.save(pessoaJuridica);
	}

}
