/**
 * 
 */
package br.com.jumbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.repository.NotaFiscalCompraRepository;
import br.com.jumbo.service.NotaFiscalCompraService;

/**
 * @author João Paulo
 *
 *         30 de jan. de 2022 13:58:27
 */
@Controller
@RestController
public class NotaFiscalCompraController {

	@Autowired
	private NotaFiscalCompraRepository notaFiscalCompraRepository;

	@Autowired
	private NotaFiscalCompraService notaFiscalCompraService;

}
