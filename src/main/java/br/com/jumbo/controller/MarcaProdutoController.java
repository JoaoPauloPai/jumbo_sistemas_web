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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
import br.com.jumbo.model.Acesso;
import br.com.jumbo.model.CategoriaProduto;
import br.com.jumbo.model.MarcaProduto;
import br.com.jumbo.model.dto.CategoriaProdutoDto;
import br.com.jumbo.repository.MarcaProdutoRepository;
import br.com.jumbo.service.MarcaProdutoSevice;

/**
 * @author João Paulo
 *
 *         17 de jan. de 2022 20:17:21
 */
@Controller
@RestController
public class MarcaProdutoController {

	@Autowired
	private MarcaProdutoRepository marcaProdutoRepository;

	@Autowired
	private MarcaProdutoSevice marcaProdutoSevice;
	
	@ResponseBody 
	@PostMapping(value = "**/salvarMarcaProduto")
	public ResponseEntity<MarcaProduto> salvarMarca(@RequestBody @Valid MarcaProduto marcaProduto ) throws ExceptionJumboSistemas { /*Recebe o JSON e converte pra Objeto*/
		
		if (marcaProduto.getId() == null) {
		  List<MarcaProduto> marcaProdutos  = marcaProdutoRepository.buscarMarcaDesc(marcaProduto.getNomeDesc().toUpperCase());
		  
		  if (!marcaProdutos.isEmpty()) {
			  throw new ExceptionJumboSistemas("Já existe Marca com a descrição: " + marcaProduto.getNomeDesc());
		  }
		}
		
		
		MarcaProduto marcaProdutoSalvo = marcaProdutoRepository.save(marcaProduto);
		
		return new ResponseEntity<MarcaProduto>(marcaProdutoSalvo, HttpStatus.OK);
	}
	
	
	
	@ResponseBody /*Poder dar um retorno da API*/
	@PostMapping(value = "**/deleteMarcaProduto") /*Mapeando a url para receber JSON*/
	public ResponseEntity<?> deleteMarca(@RequestBody MarcaProduto marcaProduto) { /*Recebe o JSON e converte pra Objeto*/
		
		marcaProdutoRepository.deleteById(marcaProduto.getId());
		
		return new ResponseEntity("Marca produto Removido",HttpStatus.OK);
	}
	

	//@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	@ResponseBody
	@DeleteMapping(value = "**/deleteMarcaProdutoPorId/{id}")
	public ResponseEntity<?> deleteMarcaPorId(@PathVariable("id") Long id) { 
		
		marcaProdutoRepository.deleteById(id);
		
		return new ResponseEntity("Marca Produto Removido",HttpStatus.OK);
	}
	
	
	
	@ResponseBody
	@GetMapping(value = "**/buscarMarcaProduto/{id}")
	public ResponseEntity<MarcaProduto> obterMarcaProduto(@PathVariable("id") Long id) throws ExceptionJumboSistemas { 
		
		MarcaProduto marcaProduto = marcaProdutoRepository.findById(id).orElse(null);
		
		if (marcaProduto == null) {
			throw new ExceptionJumboSistemas("Não encontrou Marca Produto com código: " + id);
		}
		
		return new ResponseEntity<MarcaProduto>(marcaProduto,HttpStatus.OK);
	}
	
	
	
	@ResponseBody
	@GetMapping(value = "**/buscarMarcaProdutoPorDesc/{desc}")
	public ResponseEntity<List<MarcaProduto>> buscarMarcaProdutoPorDesc(@PathVariable("desc") String desc) { 
		
		List<MarcaProduto>  marcaProdutos = marcaProdutoRepository.buscarMarcaDesc(desc.toUpperCase().trim());
		
		return new ResponseEntity<List<MarcaProduto>>(marcaProdutos,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/listaMarcaProduto")
	public ResponseEntity<List<MarcaProduto>> listaMarcaProduto() {

		List<MarcaProduto> prod = marcaProdutoRepository.findAll();

		return new ResponseEntity<List<MarcaProduto>>(prod, HttpStatus.OK);

	}
	
}
