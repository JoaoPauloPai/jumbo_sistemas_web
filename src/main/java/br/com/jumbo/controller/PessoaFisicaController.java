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

import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.repository.PessoaFisicaRepository;

/**
 * @author João Paulo
 *
 *         12 de jan. de 2022 15:25:01
 */
@Controller
@RestController
public class PessoaFisicaController {

	private PessoaFisicaRepository pessoaFisicaRepository;

	@ResponseBody
	@GetMapping(value = "**/listaPessoaFisica")
	public ResponseEntity<List<PessoaFisica>> listaPessoaFisica() {

		List<PessoaFisica> pessfis = (List<PessoaFisica>) pessoaFisicaRepository.findAll();

		return new ResponseEntity<List<PessoaFisica>>(pessfis, HttpStatus.OK);

	}

}
