/**
 * 
 */
package br.com.jumbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.model.Acesso;
import br.com.jumbo.repository.AcessoRepository;
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
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@ResponseBody
	@PostMapping(value = "**/salvarAcesso")
	public ResponseEntity<Acesso>salvarAcesso(@RequestBody Acesso acesso) {
		
		Acesso acessoSalvo = acessoService.save(acesso);
		
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "**/deleteAcesso")
	public ResponseEntity<Acesso>deleteAcesso(@RequestBody Acesso acesso) {
		
		
		 acessoRepository.deleteById(acesso.getId());
		
		return new ResponseEntity("Acesso deletado com sucesso!", HttpStatus.OK);
	}
	
	

}
