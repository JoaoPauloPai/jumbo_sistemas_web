/**
 * 
 */
package br.com.jumbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.model.Acesso;
import br.com.jumbo.service.AcessoService;

/**
 * @author João Paulo
 *
 * 11 de jan. de 2022
 * 18:12:35
 */
@Controller
@RestController
public class AcessoController {
	
	@Autowired
	private AcessoService acessoService;
	
	
	@PostMapping("/salvarAcesso")
	public Acesso salvarAcesso(Acesso acesso) {
		
		return acessoService.save(acesso);
	}
	
	

}
