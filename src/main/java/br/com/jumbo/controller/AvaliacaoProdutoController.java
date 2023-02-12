/**
 * 
 */
package br.com.jumbo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
import br.com.jumbo.model.Acesso;
import br.com.jumbo.model.AvaliacaoProduto;
import br.com.jumbo.repository.AvaliacaoProdutoRepository;
import br.com.jumbo.service.AcessoContagemApiService;

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

	@Autowired
	private AcessoContagemApiService acessoContagemApiService;

	@ResponseBody
	@PostMapping(value = "**/salvaAvaliacaoProduto")
	public ResponseEntity<AvaliacaoProduto> savalAvaliacaoProduto(@RequestBody @Valid AvaliacaoProduto avaliacaoProduto)
			throws ExceptionJumboSistemas {

		if (avaliacaoProduto.getEmpresa() == null
				|| (avaliacaoProduto.getEmpresa() != null && avaliacaoProduto.getEmpresa().getId() <= 0)) {
			throw new ExceptionJumboSistemas("Informa a empresa dona do registro");
		}

		if (avaliacaoProduto.getProduto() == null
				|| (avaliacaoProduto.getProduto() != null && avaliacaoProduto.getProduto().getId() <= 0)) {
			throw new ExceptionJumboSistemas("A avaliação deve conter o produto associado.");
		}

		if (avaliacaoProduto.getPessoa() == null
				|| (avaliacaoProduto.getPessoa() != null && avaliacaoProduto.getPessoa().getId() <= 0)) {
			throw new ExceptionJumboSistemas("A avaliação deve conter a pessoa ou cliente associado.");
		}

		avaliacaoProduto = avaliacaoProdutoRepository.saveAndFlush(avaliacaoProduto);

		String msg = "1";

		acessoContagemApiService.atualizaContagemEndPoint(msg);
	

		return new ResponseEntity<AvaliacaoProduto>(avaliacaoProduto, HttpStatus.OK);

	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteAvalicaoPessoa/{idAvaliacao}")
	public ResponseEntity<?> deleteAvalicaoPessoa(@PathVariable("idAvaliacao") Long idAvaliacao) {

		avaliacaoProdutoRepository.deleteById(idAvaliacao);

		return new ResponseEntity<String>("Avaliacao Removida", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/avaliacaoProduto/{idProduto}")
	public ResponseEntity<List<AvaliacaoProduto>> avaliacaoProduto(@PathVariable("idProduto") Long idProduto) {

		List<AvaliacaoProduto> avaliacaoProdutos = avaliacaoProdutoRepository.avaliacaoProduto(idProduto);

		return new ResponseEntity<List<AvaliacaoProduto>>(avaliacaoProdutos, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/avaliacaoProdutoPorPessoa/{idPessoa}")
	public ResponseEntity<List<AvaliacaoProduto>> avaliacaoPessoa(@PathVariable("idPessoa") Long idPessoa) {

		List<AvaliacaoProduto> avaliacaoProdutos = avaliacaoProdutoRepository.avaliacaoPessoa(idPessoa);

		return new ResponseEntity<List<AvaliacaoProduto>>(avaliacaoProdutos, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/listaAvaliacaoProduto")
	public ResponseEntity<List<AvaliacaoProduto>> listaAvaliacaoProduto() {

		List<AvaliacaoProduto> avaprod = avaliacaoProdutoRepository.findAll();

		return new ResponseEntity<List<AvaliacaoProduto>>(avaprod, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/buscaAvaliacaoProduto")
	public ResponseEntity<AvaliacaoProduto> buscaavaliacaporid(@RequestParam(name = "id") long id) {

		AvaliacaoProduto avaprod = avaliacaoProdutoRepository.findById(id).get();

		return new ResponseEntity<AvaliacaoProduto>(avaprod, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/buscaAvaliacaoProdutoPorId/{id}")
	public ResponseEntity<AvaliacaoProduto> buscaAvaliacaoProdutoPorId(@PathVariable(name = "id") long id)
			throws ExceptionJumboSistemas {

		AvaliacaoProduto avaliacaoProduto = avaliacaoProdutoRepository.findById(id).orElse(null);

		if (avaliacaoProduto == null) {

			throw new ExceptionJumboSistemas("Não encotrado Avaliação do produto com o código " + id);
		}

		return new ResponseEntity<AvaliacaoProduto>(avaliacaoProduto, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/buscaAvaliacaoProdutoPorNota/{nota}")
	public ResponseEntity<AvaliacaoProduto> buscaAvaliacaoProdutoPorNota(@PathVariable(name = "nota") Long id)
			throws ExceptionJumboSistemas {

		AvaliacaoProduto avaliacaoProdutoSalvo = avaliacaoProdutoRepository.findById(id).orElse(null);

		if (avaliacaoProdutoSalvo == null) {

			throw new ExceptionJumboSistemas("Não encotrado Avaliação-Produto com código " + id);
		}

		return new ResponseEntity<AvaliacaoProduto>(avaliacaoProdutoSalvo, HttpStatus.OK);

	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteAvaliacaoProdutoPorId/{id}")
	public ResponseEntity<?> deleteAvaliacaoProdutoPorId(@PathVariable("id") Long id) throws ExceptionJumboSistemas {

		AvaliacaoProduto avaliacaoProduto = avaliacaoProdutoRepository.findById(id).orElse(null);

		if (avaliacaoProduto == null) {

			throw new ExceptionJumboSistemas("Não encotrado Avaliação do produto com o código " + id);
		}

		avaliacaoProdutoRepository.deleteById(id);

		return new ResponseEntity("Avalicação-Produto deletado por Id com sucesso!", HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "**/deleteAvaliacaoProduto")
	public ResponseEntity<AvaliacaoProduto> deleteAvaliacaoProduto(@RequestBody AvaliacaoProduto avaliacaoProduto) {

		avaliacaoProdutoRepository.deleteById(avaliacaoProduto.getId());

		return new ResponseEntity("AvaliacaoProduto deletado com sucesso!", HttpStatus.OK);
	}

}
