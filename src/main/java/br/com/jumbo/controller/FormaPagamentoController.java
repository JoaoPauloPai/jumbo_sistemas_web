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

import br.com.jumbo.model.FormaPagamento;
import br.com.jumbo.repository.FormaPagamentoRepository;
import br.com.jumbo.service.FormaPagamentoService;

/**
 * @author João Paulo
 *
 * 17 de jan. de 2022
 * 19:14:11
 */
@Controller
@RestController
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@ResponseBody
	@GetMapping(value = "**/listaFormaPagamento")
	public ResponseEntity<List<FormaPagamento>> listaFormaPagamento() {

		List<FormaPagamento> formpag = formaPagamentoRepository.findAll();

		return new ResponseEntity<List<FormaPagamento>>(formpag, HttpStatus.OK);

	}

}
