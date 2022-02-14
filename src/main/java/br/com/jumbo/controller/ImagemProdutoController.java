/**
 * 
 */
package br.com.jumbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.model.ImagemProduto;
import br.com.jumbo.repository.ImagemProdutoRepository;
import br.com.jumbo.service.ImagemProdutoService;

/**
 * @author João Paulo
 *
 *         17 de jan. de 2022 19:25:00
 */
@Controller
@RestController
public class ImagemProdutoController {

	@Autowired
	private ImagemProdutoRepository imagemProdutoRepository;

	@Autowired
	private ImagemProdutoService imagemProdutoService;

	@ResponseBody
	@GetMapping(value = "**/listaImagemProduto")
	public ResponseEntity<List<ImagemProduto>> ListaImagemProduto() {

		List<ImagemProduto> imgprodut = imagemProdutoRepository.findAll();

		return new ResponseEntity<List<ImagemProduto>>(imgprodut, HttpStatus.OK);

	}

}
