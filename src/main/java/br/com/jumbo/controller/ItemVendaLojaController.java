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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
import br.com.jumbo.model.ItemVendaLoja;
import br.com.jumbo.repository.ItemVendaLojaRepository;
import br.com.jumbo.service.ItemVendaLojaService;

/**
 * @author João Paulo
 *
 *         17 de jan. de 2022 19:37:46
 */
@Controller
@RestController
public class ItemVendaLojaController {

	@Autowired
	private ItemVendaLojaRepository itemVendaLojaRepository;

	@Autowired
	private ItemVendaLojaService itemVendaLojaService;

	@ResponseBody
	@PostMapping(value = "**/salvarItemVendaLoja")
	public ResponseEntity<ItemVendaLoja> salvarItemVendaLoja(@RequestBody @Valid ItemVendaLoja itemVendaLoja)
			throws ExceptionJumboSistemas { /* Recebe o JSON e converte pra Objeto */

		ItemVendaLoja itemVendaLojaSalvo = itemVendaLojaRepository.save(itemVendaLoja);

		return new ResponseEntity<ItemVendaLoja>(itemVendaLojaSalvo, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "/**listaItemVendaLoja")
	public ResponseEntity<List<ItemVendaLoja>> listaItemVenadaLoja() {

		List<ItemVendaLoja> itemvdloja = itemVendaLojaRepository.findAll();
		
		

		return new ResponseEntity<List<ItemVendaLoja>>(itemvdloja, HttpStatus.OK);

	}

}
