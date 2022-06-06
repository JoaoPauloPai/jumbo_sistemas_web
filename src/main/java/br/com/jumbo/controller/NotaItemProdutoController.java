/**
 * 
 */
package br.com.jumbo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
import br.com.jumbo.model.NotaItemProduto;
import br.com.jumbo.repository.NotaItemProdutoRepository;

/**
 * @author João Paulo
 *
 *         30 de jan. de 2022 14:11:16
 */
@Controller
@RestController
public class NotaItemProdutoController {

	@Autowired
	private NotaItemProdutoRepository notaItemProdutoRepository;

	@ResponseBody
	@PostMapping(value = "**/salvarNotaItemProduto")
	public ResponseEntity<NotaItemProduto> salvarNotaItemProduto(@RequestBody @Valid NotaItemProduto notaItemProduto)
			throws ExceptionJumboSistemas {

		
		//if(notaItemProduto.getId() ==  ) {

		if (notaItemProduto.getProduto() == null || notaItemProduto.getProduto().getId() <= 0) {
			throw new ExceptionJumboSistemas("O produto deve ser informado.");
		}

		if (notaItemProduto.getNotaFiscalCompra() == null || notaItemProduto.getNotaFiscalCompra().getId() <= 0) {
			throw new ExceptionJumboSistemas("A nota fisca deve ser informada.");
		}

		if (notaItemProduto.getEmpresa() == null || notaItemProduto.getEmpresa().getId() <= 0) {
			throw new ExceptionJumboSistemas("A empresa deve ser informada.");
		}

		//List<NotaItemProduto> notaExistente = notaItemProdutoRepository.buscaNotaItemPorProdutoNota(
		//		notaItemProduto.getProduto().getId(), notaItemProduto.getNotaFiscalCompra().getId());

	//	if (!notaExistente.isEmpty()) {
		//	throw new ExceptionJumboSistemas("Já existe este produto cadastrado para esta nota.");
	//	}

		// }

		if (notaItemProduto.getQuantidade() <= 0) {
			throw new ExceptionJumboSistemas("A quantidade do produto deve ser informada.");
		}

		NotaItemProduto notaItemSalva = notaItemProdutoRepository.save(notaItemProduto);

		notaItemSalva = notaItemProdutoRepository.findById(notaItemProduto.getId()).get();

		return new ResponseEntity<NotaItemProduto>(notaItemSalva, HttpStatus.OK);

	}
}
