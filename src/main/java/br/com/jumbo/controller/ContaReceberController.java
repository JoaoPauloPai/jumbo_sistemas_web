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
import br.com.jumbo.model.ContaReceber;
import br.com.jumbo.repository.ContaReceberRepository;
import br.com.jumbo.service.ContaReceberService;

/**
 * @author João Paulo
 *
 *         15 de jan. de 2022 14:12:30
 */
@Controller
@RestController
public class ContaReceberController {

	@Autowired
	private ContaReceberRepository contaReceberRepository;

	@Autowired
	private ContaReceberService contaReceberService;

	@ResponseBody
	@PostMapping(value = "**/salvarContaReceber")
	public ResponseEntity<ContaReceber> salvarContaReceber(@RequestBody @Valid ContaReceber contaReceber)
			throws ExceptionJumboSistemas { /* Recebe o JSON e converte pra Objeto */

		if (contaReceber.getEmpresa() == null || (contaReceber.getEmpresa().getId() == null)) {
			throw new ExceptionJumboSistemas("A empresa deve ser informada.");
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

	@ResponseBody
	@GetMapping(value = "**/buscaContaReceberPorId/{id}")
	public ResponseEntity<ContaReceber> buscaContaReceberPorId(@PathVariable(name = "id") long id)
			throws ExceptionJumboSistemas {

		ContaReceber contaRec = contaReceberRepository.findById(id).orElse(null);

		if (contaRec == null) {

			throw new ExceptionJumboSistemas("Não encotrado Conta Receber com código " + id);
		}

		return new ResponseEntity<ContaReceber>(contaRec, HttpStatus.OK);

	}

	@ResponseBody
	@PostMapping(value = "**/deleteContaReceber")
	public ResponseEntity<?> deleteContaReceber(@RequestBody ContaReceber contaReceber) throws ExceptionJumboSistemas {

		if (contaReceberRepository.findById(contaReceber.getId()).isPresent() == false) {
			throw new ExceptionJumboSistemas(
					"O Código: " + contaReceber.getId() + ", da Conta Receber não foi encotrado no banco de dados");
		}

		contaReceberRepository.deleteById(contaReceber.getId());

		return new ResponseEntity("Conta Receber deletado com sucesso!", HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteContaReceberPorId/{id}")
	public ResponseEntity<?> deleteContaReceberPorId(@PathVariable("id") Long id) throws ExceptionJumboSistemas {

		if (contaReceberRepository.findById(id).isPresent() == false) {
			throw new ExceptionJumboSistemas(
					"O Código: " + id + ", da Conta Receber não foi encotrado no banco de dados");
		}

		contaReceberRepository.deleteById(id);

		return new ResponseEntity("Conta Receber deletado por Id com sucesso!", HttpStatus.OK);
	}

}
