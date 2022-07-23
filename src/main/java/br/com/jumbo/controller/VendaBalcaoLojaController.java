/**
 * 
 */
package br.com.jumbo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
import br.com.jumbo.model.VendaBalcaoLoja;
import br.com.jumbo.model.VendaCompraLojaVirtual;
import br.com.jumbo.model.dto.VendaCompraLojaVirtualDTO;
import br.com.jumbo.repository.VendaBalcaoLojaRepository;

/**
 * @author João Paulo
 *
 * 28 de jun. de 2022
 * 17:36:41
 */
@Controller
@RestController
public class VendaBalcaoLojaController {
	
	@Autowired
	VendaBalcaoLojaRepository vendaBalcaoLojaRepository;
	
	@ResponseBody
	@PostMapping(value = "**/salvarVendaBalcaoLoja")
	public ResponseEntity<VendaBalcaoLoja> salvarVendaBalcaoLoja(
			@RequestBody @Valid VendaBalcaoLoja vendaBalcaoLoja) throws ExceptionJumboSistemas {

        
	     VendaBalcaoLoja vendaBalcao	= vendaBalcaoLojaRepository.saveAndFlush(vendaBalcaoLoja);
		
		//VendaCompraLojaVirtualDTO compraLojaVirtualDTO = new VendaCompraLojaVirtualDTO();
		//compraLojaVirtualDTO.setValorTotal(vendaBalcaoLoja.getValorTotal());
		
		return new ResponseEntity<VendaBalcaoLoja>(vendaBalcao, HttpStatus.OK);
	}
}
