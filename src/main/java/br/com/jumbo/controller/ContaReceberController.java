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

import br.com.jumbo.model.ContaReceber;
import br.com.jumbo.repository.ContaReceberRepository;
import br.com.jumbo.service.ContaReceberService;

/**
 * @author João Paulo
 *
 * 15 de jan. de 2022
 * 14:12:30
 */
@Controller
@RestController
public class ContaReceberController {
	
	@Autowired
	private ContaReceberRepository contaReceberRepository;

	@Autowired
	private ContaReceberService contaReceberService;
	
	@ResponseBody
	@GetMapping(value = "**/listaContaReceber")
	public ResponseEntity<List<ContaReceber>> listaContaReceber() {

		List<ContaReceber> contarec = contaReceberRepository.findAll();

		return new ResponseEntity<List<ContaReceber>>(contarec, HttpStatus.OK);

	}	
}
