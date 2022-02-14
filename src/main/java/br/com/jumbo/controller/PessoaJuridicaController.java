/**
 * 
 */
package br.com.jumbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.repository.PessoaJuridicaRepository;
import br.com.jumbo.service.PessoaJuridicaService;

/**
 * @author João Paulo
 *
 * 30 de jan. de 2022
 * 14:14:11
 */
@Controller
@RestController
public class PessoaJuridicaController {
	
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	
	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;

}
