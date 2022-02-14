/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.AvaliacaoProduto;
import br.com.jumbo.repository.AvaliacaoProdutoRepository;

/**
 * @author João Paulo
 *
 *         12 de jan. de 2022 14:27:09
 */
@Service
public class AvaliacaoProdutoService {

	@Autowired
	private AvaliacaoProdutoRepository avaliacaoProdutoRepository;

	public AvaliacaoProduto save(AvaliacaoProduto avaliacaoProduto) {

		return avaliacaoProdutoRepository.save(avaliacaoProduto);

	}
}
