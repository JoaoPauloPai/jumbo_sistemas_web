/**
 * 
 */
package br.com.jumbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.repository.StatusRatreioRepository;

/**
 * @author João Paulo
 *
 * 24 de jan. de 2022
 * 14:39:37
 */
@Controller
@RestController
public class SatatusRastreioController {
	
	@Autowired
	private StatusRatreioRepository statusRatreioRepository;

}
