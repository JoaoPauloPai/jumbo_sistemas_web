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
import br.com.jumbo.model.CupomDesc;
import br.com.jumbo.model.FormaPagamento;
import br.com.jumbo.model.Produto;
import br.com.jumbo.repository.CupomDescontoRepository;
import br.com.jumbo.service.CupomDescontoService;

/**
 * @author João Paulo
 *
 *         15 de jan. de 2022 18:21:34
 */
@Controller
@RestController
public class CupomDescontoController {

	@Autowired
	private CupomDescontoRepository cupomDescontoRepository;

	@ResponseBody
	@PostMapping(value = "**/salvarCupomDesconto")
	public ResponseEntity<CupomDesc> salvarCupomDesc(@RequestBody @Valid CupomDesc cupomDesc)
			throws ExceptionJumboSistemas {
		if (cupomDesc.getId() == null) {

			List<CupomDesc> cupomDescList = cupomDescontoRepository.buscarCupomPorDesc(
					cupomDesc.getCodDesc().toUpperCase(), cupomDesc.getEmpresa().getId());

			if (!cupomDescList.isEmpty()) {
				throw new ExceptionJumboSistemas(
						"Já existe Cupom-Desconto com essa descrição: " + cupomDesc.getCodDesc());
			}
		}


	

		CupomDesc cupomDescSalvo = cupomDescontoRepository.save(cupomDesc);

		return new ResponseEntity<CupomDesc>(cupomDescSalvo, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/listaCupomDesconto")
	public ResponseEntity<List<CupomDesc>> listaCupomDesconto() {

		List<CupomDesc> cupomdesc = cupomDescontoRepository.findAll();

		return new ResponseEntity<List<CupomDesc>>(cupomdesc, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/buscaCupomDescPorId/{id}")
	public ResponseEntity<CupomDesc> buscaCupomDescPorId(@PathVariable("id") Long id) throws ExceptionJumboSistemas {

		CupomDesc cupomDesc = cupomDescontoRepository.findById(id).orElse(null);

		if (cupomDesc == null) {
			throw new ExceptionJumboSistemas("Não encontrou o Cupom de Desconto com código: " + id);
		}

		return new ResponseEntity<CupomDesc>(cupomDesc, HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteCupomDescPorId/{id}")
	public ResponseEntity<?> deleteCupomDescPorId(@PathVariable("id") Long id) throws ExceptionJumboSistemas {

		CupomDesc cupomDesc = cupomDescontoRepository.findById(id).orElse(null);

		if (cupomDesc == null) {
			throw new ExceptionJumboSistemas("Não encontrou o Cupom de Desconto com código: " + id);
		}

		cupomDescontoRepository.deleteById(id);

		return new ResponseEntity("Cupom-Desconto deletado por Id com sucesso!", HttpStatus.OK);
	}

}
