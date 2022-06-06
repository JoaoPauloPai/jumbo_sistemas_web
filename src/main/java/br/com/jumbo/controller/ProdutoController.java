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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
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

	@Autowired
	private ProdutoRepository produtoRepository;

	@ResponseBody
	@PostMapping(value = "**/salvarProduto")
	public ResponseEntity<Produto> salvarProduto(@RequestBody @Valid Produto produto) throws ExceptionJumboSistemas {

		if (produto.getId() == null) {
			  List<Produto> produtos  = produtoRepository.buscarProdutoNome(produto.getNome().toUpperCase(), produto.getEmpresa().getId());
			  
			  if (!produtos.isEmpty()) {
				  throw new ExceptionJumboSistemas("Já existe Produto com a descrição: " + produto.getNome());
			  }
			}
		
			//if (!acessos.isEmpty()) {
			//	throw new ExceptionJumboSistemas("Já existe Acesso com a descrição: " + acesso.getDescricao());
		//	}
		
		Produto produtoSalvo = produtoRepository.save(produto);
		return new ResponseEntity<Produto>(produtoSalvo, HttpStatus.OK);
		
	}

	@ResponseBody
	@GetMapping(value = "**/listaProduto")
	public ResponseEntity<List<Produto>> listaProduto() {

		List<Produto> prod = produtoRepository.findAll();

		return new ResponseEntity<List<Produto>>(prod, HttpStatus.OK);

	}

}
