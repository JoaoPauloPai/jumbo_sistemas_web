/**
 * 
 */
package br.com.jumbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.repository.NotaFiscalVendaRepository;
import br.com.jumbo.service.NotaFiscalVendaService;

/**
 * @author João Paulo
 *
 * 30 de jan. de 2022
 * 14:01:59
 */
@Controller
@RestController
public class NotaFiscalVendaController {
	
	@Autowired
	private NotaFiscalVendaRepository notaFiscalVendaRepository;
	
	@Autowired
	private NotaFiscalVendaService notaFiscalVendaService;

}
