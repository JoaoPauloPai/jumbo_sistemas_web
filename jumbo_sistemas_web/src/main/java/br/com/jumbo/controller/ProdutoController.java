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


import br.com.jumbo.model.Produto;
import br.com.jumbo.repository.ProdutoRepository;

/**
 * @author João Paulo
 *
 *         12 de jan. de 2022 13:48:54
 */
@Controller
@RestController
public class ProdutoController {

	private ProdutoRepository produtoRepository;

	@ResponseBody
	@GetMapping(value = "**/listaProduto")
	public ResponseEntity<List<Produto>> listaProduto() {

		List<Produto> prod = produtoRepository.findAll();

		return new ResponseEntity<List<Produto>>(prod, HttpStatus.OK);

	}
}
