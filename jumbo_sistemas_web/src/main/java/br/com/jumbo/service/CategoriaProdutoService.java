/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.stereotype.Service;

import br.com.jumbo.model.CategoriaProduto;
import br.com.jumbo.repository.CategoriaProdutoRepository;

/**
 * @author João Paulo
 *
 *         13 de jan. de 2022 21:06:23
 */
@Service
public class CategoriaProdutoService {

	private CategoriaProdutoRepository categoriaProdutoRepository;

	public CategoriaProduto save(CategoriaProduto categoriaProduto) {

		return categoriaProdutoRepository.save(categoriaProduto);
	}

}
