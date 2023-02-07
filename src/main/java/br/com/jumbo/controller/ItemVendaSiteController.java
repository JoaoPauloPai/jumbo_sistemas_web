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
import br.com.jumbo.model.ItemVendaSite;
import br.com.jumbo.model.dto.ItemVendaLojaDTO;
import br.com.jumbo.repository.ItemVendaSiteRepository;

/**
 * @author João Paulo
 *
 *         17 de jan. de 2022 19:37:46
 */
@Controller
@RestController
public class ItemVendaSiteController {

	@Autowired
	private ItemVendaSiteRepository itemVendaSiteRepository;

	// @Autowired
//	private ItemVendaLojaDTO itemVendaLojaDTO;

	@ResponseBody
	@PostMapping(value = "**/salvarItemVendaSite")
	public ResponseEntity<ItemVendaSite> salvarItemVendaSite(@RequestBody @Valid ItemVendaSite itemVendaLoja)
			throws ExceptionJumboSistemas { /* Recebe o JSON e converte pra Objeto */

		ItemVendaSite itemVendaSiteSalvo = itemVendaSiteRepository.save(itemVendaLoja);

		return new ResponseEntity<ItemVendaSite>(itemVendaSiteSalvo, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/listaItemVendaSite")
	public ResponseEntity<List<ItemVendaSite>> listaItemVenadaSite() {

		List<ItemVendaSite> itemvdloja = itemVendaSiteRepository.findAll();

		return new ResponseEntity<List<ItemVendaSite>>(itemvdloja, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/buscaItemVendaSitePorId/{id}")
	public ResponseEntity<ItemVendaLojaDTO> buscaItemVendaSitePorId(@PathVariable(name = "id") long id)
			throws ExceptionJumboSistemas {

		ItemVendaSite itemVendaSite = itemVendaSiteRepository.findById(id).orElse(null);

		if (itemVendaSite == null) {

			throw new ExceptionJumboSistemas("Não encotrado o Item-Venda-Site com código " + id);
		}

		ItemVendaLojaDTO itemVendaLojaDTO = new ItemVendaLojaDTO();
		itemVendaLojaDTO.setId(itemVendaSite.getId());
		itemVendaLojaDTO.setQuantidade(itemVendaSite.getQuantidade());
		//itemVendaLojaDTO.setItemVendaSite(itemVendaSite.getId());
		//itemVendaLojaDTO.setProduto(itemVendaSite.getProduto());
	

		return new ResponseEntity<ItemVendaLojaDTO>(itemVendaLojaDTO, HttpStatus.OK);

	}

}
