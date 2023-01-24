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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
import br.com.jumbo.model.CategoriaProduto;
import br.com.jumbo.model.ContaPagar;
import br.com.jumbo.model.ContaReceber;
import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.repository.ContaReceberRepository;
import br.com.jumbo.repository.PessoaFisicaRepository;
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
	
	@Autowired
	private PessoaFisicaRepository  pessoaFisicaRepository;	
	

	@ResponseBody
	@PostMapping(value = "**/salvarContaReceber")
	public ResponseEntity<ContaReceber> salvarContaReceber(
			@RequestBody @Valid ContaReceber contaReceber)
			throws ExceptionJumboSistemas { /* Recebe o JSON e converte pra Objeto */

		if (contaReceber.getEmpresa() == null || (contaReceber.getEmpresa().getId() == null)) {
			throw new ExceptionJumboSistemas("A empresa deve ser informada.");
		}
		
		if (contaReceber.getPessoa().getId() != null && pessoaFisicaRepository.existeIdCadastrado(contaReceber.getPessoa().getId()) == null) {
			throw new ExceptionJumboSistemas("Não existe Pessoa cadastrado com o ID: " + contaReceber.getPessoa().getId());
		}

	

		ContaReceber contaRecebe = contaReceberRepository.save(contaReceber);

		return new ResponseEntity<ContaReceber>(contaRecebe, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/listaContaReceber")
	public ResponseEntity<List<ContaReceber>> listaContaReceber() {

		List<ContaReceber> contarec = contaReceberRepository.findAll();

		return new ResponseEntity<List<ContaReceber>>(contarec, HttpStatus.OK);

	}	
	
	@ResponseBody
	@GetMapping(value = "**/buscaContaReceberDesc/{desc}")
	public ResponseEntity<List<ContaReceber>> buscaContaReceberDesc(@PathVariable("desc") String desc) {

		List<ContaReceber> contaReceberDesc = contaReceberRepository.buscaContaReceberTipo(desc.toUpperCase());

		return new ResponseEntity<List<ContaReceber>>(contaReceberDesc, HttpStatus.OK);
	}
}
