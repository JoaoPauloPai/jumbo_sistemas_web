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

import br.com.jumbo.model.MarcaProduto;
import br.com.jumbo.repository.MarcaProdutoRepository;
import br.com.jumbo.service.MarcaProdutoSevice;

/**
 * @author João Paulo
 *
 * 17 de jan. de 2022
 * 20:17:21
 */
@Controller
@RestController
public class MarcaProdutoController {
	
	@Autowired
	private MarcaProdutoRepository marcaProdutoRepository;
	
	@Autowired
	private MarcaProdutoSevice marcaProdutoSevice;
	
	@ResponseBody
	@GetMapping(value = "**/listaMarcaProduto")
	public ResponseEntity<List<MarcaProduto>> listaMarcaProduto(){
		
		List<MarcaProduto> marcprod = marcaProdutoRepository.findAll();
		
		return new ResponseEntity<List<MarcaProduto>>(marcprod, HttpStatus.OK);
	}
	

}
