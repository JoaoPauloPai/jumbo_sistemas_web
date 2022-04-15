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

import br.com.jumbo.model.Endereco;
import br.com.jumbo.repository.EnderecoRepository;
import br.com.jumbo.service.EnderecoService;

/**
 * @author João Paulo
 *
 * 15 de jan. de 2022
 * 18:41:04
 */
@Controller
@RestController
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private EnderecoRepository endereçoRepository;

	
	@ResponseBody
	@GetMapping(value = "**/listaEndereco")
	public ResponseEntity<List<Endereco>> listaEndereco() {

		List<Endereco> end = endereçoRepository.findAll();

		return new ResponseEntity<List<Endereco>>(end, HttpStatus.OK);

	}	
}
