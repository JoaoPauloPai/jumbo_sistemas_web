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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.model.AvaliacaoProduto;
import br.com.jumbo.repository.AvaliacaoProdutoRepository;

/**
 * @author João Paulo
 *
 *         12 de jan. de 2022 14:34:01
 */
@Controller
@RestController
public class AvaliacaoProdutoController {

	@Autowired
	private AvaliacaoProdutoRepository avaliacaoProdutoRepository;

	@ResponseBody
	@GetMapping(value = "**/listaAvaliacaoProduto")
	public ResponseEntity<List<AvaliacaoProduto>> listaAvaliacaoProduto() {

		List<AvaliacaoProduto> avaprod = avaliacaoProdutoRepository.findAll();

		return new ResponseEntity<List<AvaliacaoProduto>>(avaprod, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/buscaAvaliacaoProdutoPorId")
	public ResponseEntity<AvaliacaoProduto> buscaavaliacaporid(@RequestParam(name = "id") long id) {

		AvaliacaoProduto avaprod = avaliacaoProdutoRepository.findById(id).get();

		return new ResponseEntity<AvaliacaoProduto>(avaprod, HttpStatus.OK);

	}

	@ResponseBody
	@PostMapping(value = "**/deleteAvaliacaoProduto")
	public ResponseEntity<AvaliacaoProduto> deleteAvaliacaoProduto(@RequestBody AvaliacaoProduto avaliacaoProduto) {

		avaliacaoProdutoRepository.deleteById(avaliacaoProduto.getId());

		return new ResponseEntity("AvaliacaoProduto deletado com sucesso!", HttpStatus.OK);
	}

}
