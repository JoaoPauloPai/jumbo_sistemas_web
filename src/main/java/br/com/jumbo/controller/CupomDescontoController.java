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

import br.com.jumbo.model.CupomDesc;
import br.com.jumbo.repository.CupomDescontoRepository;
import br.com.jumbo.service.CupomDescontoService;

/**
 * @author João Paulo
 *
 * 15 de jan. de 2022
 * 18:21:34
 */
@Controller
@RestController
public class CupomDescontoController {
	
	@Autowired
	private CupomDescontoService cupomDescontoService;
	
	@Autowired
	private CupomDescontoRepository cupomDescontoRepository;
	
	@ResponseBody
	@GetMapping(value = "**/listaCupomDesconto")
	public ResponseEntity<List<CupomDesc>> listaCupomDesconto() {

		List<CupomDesc> cupomdesc = cupomDescontoRepository.findAll();

		return new ResponseEntity<List<CupomDesc>>(cupomdesc, HttpStatus.OK);

	}	

}
