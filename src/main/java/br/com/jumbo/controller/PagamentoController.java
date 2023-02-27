/**
 * 
 */
package br.com.jumbo.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author João Paulo
 *
 * 26 de fev. de 2023
 * 18:01:16
 */
@Controller
public class PagamentoController implements Serializable{
	

	@RequestMapping(method = RequestMethod.GET, value = "**/pagamento/{idVendaCompra}")
	public ModelAndView pagamento(@PathVariable(value = "idVendaCompra",
								 required = false) String idVendaCompra) {

		//ModelAndView modelAndView = new ModelAndView("pagamento");
		
		
		return new ModelAndView("pagamento");
	}

}
