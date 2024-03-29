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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
import br.com.jumbo.model.Acesso;
import br.com.jumbo.model.CategoriaProduto;
import br.com.jumbo.repository.AcessoRepository;
import br.com.jumbo.service.AcessoService;

/**
 * @author João Paulo
 *
 *         11 de jan. de 2022 18:12:35
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
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody @Valid Acesso acesso) throws ExceptionJumboSistemas {

		if (acesso.getId() == null) {
			List<Acesso> acessos = acessoRepository.buscaAcessoDesc(acesso.getDescricao().toUpperCase());

			if (!acessos.isEmpty()) {
				throw new ExceptionJumboSistemas("Já existe Acesso com a descrição: " + acesso.getDescricao());
			}
		}

		Acesso acessoSalvo = acessoService.save(acesso);

		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "**/deleteAcesso")
	public ResponseEntity<?> deleteAcesso(@RequestBody Acesso acesso) throws ExceptionJumboSistemas {

		if (acessoRepository.findById(acesso.getId()).isPresent() == false) {
			throw new ExceptionJumboSistemas(
					"O Código: " + acesso.getId() + ", da categoria do produto não foi encotrado no banco de dados");
		}

		acessoRepository.deleteById(acesso.getId());

		return new ResponseEntity("Acesso deletado com sucesso!", HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteAcessoPorId/{id}")
	public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id) throws ExceptionJumboSistemas {

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
	public ResponseEntity<Acesso> buscaAcessoPorId(@PathVariable(name = "id") long id) throws ExceptionJumboSistemas {

		Acesso acess = acessoRepository.findById(id).orElse(null);

		if (acess == null) {

			throw new ExceptionJumboSistemas("Não encotrado Acesso com código " + id);
		}

		return new ResponseEntity<Acesso>(acess, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/buscaAcessoDesc/{desc}")
	public ResponseEntity<List<Acesso>> buscaAcessoDesc(@PathVariable("desc") String desc) {

		List<Acesso> acess = acessoRepository.buscaAcessoDesc(desc.toUpperCase());

		return new ResponseEntity<List<Acesso>>(acess, HttpStatus.OK);
	}

}
