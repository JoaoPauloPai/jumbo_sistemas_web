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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
import br.com.jumbo.model.Acesso;
import br.com.jumbo.model.ItemVendaSite;
import br.com.jumbo.model.dto.ItemVendaLojaDTO;
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
	
	@Autowired
	private ItemVendaLojaDTO itemVendaLojaDTO;

	@ResponseBody
	@PostMapping(value = "**/salvarItemVendaLoja")
	public ResponseEntity<ItemVendaSite> salvarItemVendaLoja(@RequestBody @Valid ItemVendaSite itemVendaLoja)
			throws ExceptionJumboSistemas { /* Recebe o JSON e converte pra Objeto */

		ItemVendaSite itemVendaLojaSalvo = itemVendaLojaRepository.save(itemVendaLoja);

		return new ResponseEntity<ItemVendaSite>(itemVendaLojaSalvo, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "/**listaItemVendaLoja")
	public ResponseEntity<List<ItemVendaSite>> listaItemVenadaLoja() {

		List<ItemVendaSite> itemvdloja = itemVendaLojaRepository.findAll();

		return new ResponseEntity<List<ItemVendaSite>>(itemvdloja, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/buscaItemVendaLojaPorId/{id}")
	public ResponseEntity<ItemVendaLojaDTO> buscaItemVendaLojaPorId(@PathVariable(name = "id") long id)
			throws ExceptionJumboSistemas {

		ItemVendaSite itemVendaLoja = itemVendaLojaRepository.findById(id).orElse(null);

		if (itemVendaLoja == null) {

			throw new ExceptionJumboSistemas("Não encotrado com código " + id);
		}
		
		ItemVendaLojaDTO itemVendaLojaDTO = new ItemVendaLojaDTO();
		itemVendaLojaDTO.setId(itemVendaLoja.getId());
		

		return new ResponseEntity<ItemVendaLojaDTO>(itemVendaLojaDTO, HttpStatus.OK);

	}

}
