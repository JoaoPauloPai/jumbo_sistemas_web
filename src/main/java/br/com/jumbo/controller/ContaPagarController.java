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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
import br.com.jumbo.model.Acesso;
import br.com.jumbo.model.ContaPagar;
import br.com.jumbo.repository.ContaPagarRepository;
import br.com.jumbo.service.ContaPagarService;

/**
 * @author João Paulo
 *
 *         15 de jan. de 2022 10:25:34
 */
@Controller
@RestController
public class ContaPagarController {

	@Autowired
	private ContaPagarService contaPagarService;

	@Autowired
	private ContaPagarRepository contaPagarRepository;

	@ResponseBody
	@GetMapping(value = "**/listaContaPagar")
	public ResponseEntity<List<ContaPagar>> listaContaPagar() {

		List<ContaPagar> contapag = contaPagarRepository.findAll();

		return new ResponseEntity<List<ContaPagar>>(contapag, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/buscaContaPagarPorId/{id}")
	public ResponseEntity<ContaPagar> buscaContaPagarPorId(@RequestParam(name = "id") long id) throws ExceptionJumboSistemas {
		
	}

	// Acesso acess = acessoRepository.findById(id).orElse(null);

	// if(acess == null) {

	throw new ExceptionJumboSistemas("Não encotrado Acesso com código "+id);
	// }

	// return new ResponseEntity<Acesso>(acess, HttpStatus.OK);

//	}

}

}
