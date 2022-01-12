/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.Produto;
import br.com.jumbo.repository.ProdutoRepository;

/**
 * @author João Paulo
 *
 *         12 de jan. de 2022 13:54:30
 */
@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto save(Produto produto) {

		return produtoRepository.save(produto);
	}
}
