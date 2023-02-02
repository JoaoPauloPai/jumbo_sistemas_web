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
import br.com.jumbo.model.FormaPagamento;
import br.com.jumbo.repository.FormaPagamentoRepository;


/**
 * @author João Paulo
 *
 *         17 de jan. de 2022 19:14:11
 */
@Controller
@RestController
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@ResponseBody
	@PostMapping(value = "**/salvarFormaPagamento")
	public ResponseEntity<FormaPagamento> salvarFormaPagamento(@RequestBody @Valid FormaPagamento formaPagamento)
			throws ExceptionJumboSistemas {
		if (formaPagamento.getId() == null) {

			List<FormaPagamento> formaPagamentos = formaPagamentoRepository.buscarFormaPagPorNome(
					formaPagamento.getDescricao().toUpperCase(), formaPagamento.getEmpresa().getId());

			if (!formaPagamentos.isEmpty()) {
				throw new ExceptionJumboSistemas(
						"Já existe Forma-Pagamento com essa descrição: " + formaPagamento.getDescricao());
			}
		}

		formaPagamento = formaPagamentoRepository.save(formaPagamento);

		return new ResponseEntity<FormaPagamento>(formaPagamento, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/listaFormaPagamento")
	public ResponseEntity<List<FormaPagamento>> listaFormaPagamento() {

		List<FormaPagamento> formpag = formaPagamentoRepository.findAll();

		return new ResponseEntity<List<FormaPagamento>>(formpag, HttpStatus.OK);

	}
	
	@ResponseBody
	@GetMapping(value = "**/listaFormaPagamento/{idEmpresa}")
	public ResponseEntity<List<FormaPagamento>> listaFormaPagamentodEmpresa(@PathVariable(value = "idEmpresa") Long idEmpresa){
		
		//return new ResponseEntity<List<FormaPagamento>>(formaPagamentoRepository.findAll(idEmpresa), HttpStatus.OK);
		
		return new ResponseEntity<List<FormaPagamento>>(formaPagamentoRepository.findAll(idEmpresa), HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteFormaPagamentoPorId/{id}")
	public ResponseEntity<?> deleteAcessoPorId(@PathVariable("id") Long id) {

		formaPagamentoRepository.deleteById(id);

		return new ResponseEntity("Forma-Pagamento deletado por Id com sucesso!", HttpStatus.OK);
		
	}

	@ResponseBody
	@GetMapping(value = "**/buscaFormaPagamentoPorId/{id}")
	public ResponseEntity<FormaPagamento> buscaFormaPagamentoPorId(@PathVariable(name = "id") long id)
			throws ExceptionJumboSistemas {

		FormaPagamento formaPagamento = formaPagamentoRepository.findById(id).orElse(null);

		if (formaPagamento == null) {

			throw new ExceptionJumboSistemas("Não encotrado Forma-Pagamento com código " + id);
		}

		return new ResponseEntity<FormaPagamento>(formaPagamento, HttpStatus.OK);

	}

}
