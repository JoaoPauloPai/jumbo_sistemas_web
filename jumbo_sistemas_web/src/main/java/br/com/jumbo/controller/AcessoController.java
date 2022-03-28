/**
 * 
 */
package br.com.jumbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.model.Acesso;
import br.com.jumbo.repository.AcessoRepository;
import br.com.jumbo.service.AcessoService;

/**
 * @author João Paulo
 *
 *         11 de jan. de 2022 18:12:35
 */

 //teste
@Controller
@RestController
public class AcessoController {

	@Autowired
	private AcessoService acessoService;

	@Autowired
	private AcessoRepository acessoRepository;

	@ResponseBody
	@PostMapping(value = "**/salvarAcesso")
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {

		Acesso acessoSalvo = acessoService.save(acesso);

		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "**/deleteAcesso")
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) {

		acessoRepository.deleteById(acesso.getId());

		return new ResponseEntity("Acesso deletado com sucesso!", HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteAcessoPorId/{id}")
	public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id) {

		acessoRepository.deleteById(id);

		return new ResponseEntity("Acesso deletado por Id com sucesso!", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/listaAcesso")
	public ResponseEntity<List<Acesso>> listaAcesso() {

		List<Acesso> acess = acessoRepository.findAll();

		return new ResponseEntity<List<Acesso>>(acess, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/buscaAcessoPorId/{id}")
	public ResponseEntity<Acesso> buscaacessoid(@RequestParam(name = "id") long id) {

		Acesso acess = acessoRepository.findById(id).get();

		return new ResponseEntity<Acesso>(acess, HttpStatus.OK);

	}
	
	@ResponseBody
	@GetMapping(value = "**/buscaAcessoPorDesc/{desc}")
	public ResponseEntity<List<Acesso>> buscarPorDesc(@PathVariable("desc") String desc) { 
		
		List<Acesso> acess = acessoRepository.buscaAcessoDesc(desc);
		
		return new ResponseEntity<List<Acesso>>(acess,HttpStatus.OK);
	}
	
	@GetMapping(value = "**/outraBuscaAcessoPorId") 
	@ResponseBody
	public ResponseEntity<Acesso> buscaUsuarioPorId(@RequestParam(name = "id") long id) {

	
		Acesso acess = acessoRepository.findById(id).get();
			

		return new ResponseEntity<Acesso>(acess, HttpStatus.OK);
	}


}
