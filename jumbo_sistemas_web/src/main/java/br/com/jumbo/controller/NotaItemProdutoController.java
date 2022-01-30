/**
 * 
 */
package br.com.jumbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.repository.NotaItemProdutoRepository;
import br.com.jumbo.service.NotaItemProdutoService;

/**
 * @author João Paulo
 *
 * 30 de jan. de 2022
 * 14:11:16
 */
@Controller
@RestController
public class NotaItemProdutoController {
	
	@Autowired
	private NotaItemProdutoRepository notaItemProdutoRepository;

	@Autowired
	private NotaItemProdutoService notaItemProdutoService;
}
