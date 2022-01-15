/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.NotaItemProduto;
import br.com.jumbo.repository.NotaItemProdutoRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:36:13
 */
@Service
public class NotaItemProdutoService {
	
	@Autowired
	NotaItemProdutoRepository notaItemProdutoRepository;
	
	public NotaItemProduto save(NotaItemProduto notaItemProduto) {
		
		return notaItemProdutoRepository.save(notaItemProduto);
		
	}

}
