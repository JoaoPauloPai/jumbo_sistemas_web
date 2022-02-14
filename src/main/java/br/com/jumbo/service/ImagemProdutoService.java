/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.ImagemProduto;
import br.com.jumbo.repository.ImagemProdutoRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:17:46
 */
@Service
public class ImagemProdutoService {
	
	@Autowired
	ImagemProdutoRepository imagemProdutoRepository;
	
	public ImagemProduto save(ImagemProduto imagemProduto){
		
		return imagemProdutoRepository.save(imagemProduto);
		
	}

}
