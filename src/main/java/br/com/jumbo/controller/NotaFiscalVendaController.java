/**
 * 
 */
package br.com.jumbo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.model.dto.ObejtoRequisicaoRelatorioProdCompraNotaFiscalDTO;
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
	
	
	//@ResponseBody
	//@PostMapping(value = "**/relatorioStatusCompra")
	/*blic ResponseEntity<List<ObjetoRelatorioStatusCompra>> relatorioStatusCompra (@Valid 
			             @RequestBody  ObjetoRelatorioStatusCompra objetoRelatorioStatusCompra){
		
		List<ObjetoRelatorioStatusCompra> retorno = new ArrayList<ObjetoRelatorioStatusCompra>();
		
		retorno = notaFiscalCompraService.relatorioStatusVendaLojaVirtual(objetoRelatorioStatusCompra);
		
		return new ResponseEntity<List<ObjetoRelatorioStatusCompra>>(retorno, HttpStatus.OK);
		
	}*/
	
	//@ResponseBody
	//@PostMapping(value = "**/relatorioProdCompradoNotaFiscal")
	/*public ResponseEntity<List<ObejtoRequisicaoRelatorioProdCompraNotaFiscalDTO>> relatorioProdCompradoNotaFiscal
	    (@Valid @RequestBody ObejtoRequisicaoRelatorioProdCompraNotaFiscalDTO obejtoRequisicaoRelatorioProdCompraNotaFiscalDto){
		
		List<ObejtoRequisicaoRelatorioProdCompraNotaFiscalDTO> retorno = 
				new ArrayList<ObejtoRequisicaoRelatorioProdCompraNotaFiscalDTO>();
		
		retorno = notaFiscalCompraService.gerarRelatorioProdCompraNota(obejtoRequisicaoRelatorioProdCompraNotaFiscalDto);
		
		
		return new ResponseEntity<List<ObejtoRequisicaoRelatorioProdCompraNotaFiscalDTO>>(retorno, HttpStatus.OK);
		
	}*/

}
