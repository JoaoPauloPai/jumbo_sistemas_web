/**
 * 
 */
package br.com.jumbo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.model.CategoriaProduto;
import br.com.jumbo.repository.CategoriaProdutoRepository;
import br.com.jumbo.service.CategoriaProdutoService;

/**
 * @author João Paulo
 *
 *         13 de jan. de 2022 21:11:37
 */
@Controller
@RestController
public class CategoriaProdutoController {

	private CategoriaProdutoRepository categoriaProdutoRepository;

	private CategoriaProdutoService categoriaProdutoService;

	@ResponseBody
	@GetMapping(value = "**/listaCategoriaProduto")
	public ResponseEntity<List<CategoriaProduto>> listaCategoriaProduto() {

		List<CategoriaProduto> catprod = categoriaProdutoRepository.findAll();

		return new ResponseEntity<List<CategoriaProduto>>(catprod, HttpStatus.OK);

	}

}
