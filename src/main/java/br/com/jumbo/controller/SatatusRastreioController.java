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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.model.StatusRastreio;
import br.com.jumbo.repository.StatusRastreioRepository;

/**
 * @author João Paulo
 *
 * 24 de jan. de 2022
 * 14:39:37
 */
@RestController
public class SatatusRastreioController {
	
	@Autowired
	private StatusRastreioRepository statusRatreioRepository;
	
	@ResponseBody
	@GetMapping(value = "**/listaRastreioVenda/{idVenda}")
	public ResponseEntity<List<StatusRastreio>> listaRastreioVenda (@PathVariable ("idVenda") Long idVenda){
		
	List<StatusRastreio> statusRastreios =	statusRatreioRepository.listaRastreioVenda(idVenda);
	

	return new ResponseEntity<List<StatusRastreio>>(statusRastreios, HttpStatus.OK);
	}

}
