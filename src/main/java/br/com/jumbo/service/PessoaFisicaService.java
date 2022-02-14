/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.repository.PessoaFisicaRepository;

/**
 * @author João Paulo
 *
 *         12 de jan. de 2022 15:20:58
 */
@Service
public class PessoaFisicaService {

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	public PessoaFisica save(PessoaFisica pessoaFisica) {

		/* Qualquer tipo de validação */

		return pessoaFisicaRepository.save(pessoaFisica);

	}

}
