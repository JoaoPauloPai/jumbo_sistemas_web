/**
 * 
 */
package br.com.jumbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.repository.VendaCompraLojaVirtualRepository;

/**
 * @author João Paulo
 *
 * 24 de jan. de 2022
 * 14:45:47
 */
@Controller
@RestController
public class VendaCompraLojaVirtualController {
	
	private VendaCompraLojaVirtualRepository vendaCompraLojaVirtualRepository;

}
