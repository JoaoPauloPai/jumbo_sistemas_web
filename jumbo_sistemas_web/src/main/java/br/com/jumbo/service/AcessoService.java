/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.Acesso;
import br.com.jumbo.repository.AcessoRepository;

/**
 * @author João Paulo
 *
 *         11 de jan. de 2022 18:17:43
 */
@Service
public class AcessoService {

	@Autowired
	private AcessoRepository acessoRepository;

	public Acesso save(Acesso acesso) {

		/* Qualquer tipo de validação */

		return acessoRepository.save(acesso);
	}

}
