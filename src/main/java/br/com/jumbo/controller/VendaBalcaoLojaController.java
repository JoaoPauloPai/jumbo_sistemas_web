/**
 * 
 */
package br.com.jumbo.controller;

import java.util.Calendar;

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
import br.com.jumbo.enums.StatusContaReceber;
import br.com.jumbo.enums.TipoVendaContaReceber;
import br.com.jumbo.model.ContaReceber;
import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.model.VendaBalcaoLoja;
import br.com.jumbo.repository.ContaReceberRepository;
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
	private PessoaFisicaController pessoaFisicaController;
	
	@Autowired
	VendaBalcaoLojaRepository vendaBalcaoLojaRepository;
	
	@Autowired
	ContaReceberRepository contaReceberRepository;
	
	@ResponseBody
	@PostMapping(value = "**/salvarVendaBalcaoLoja")
	public ResponseEntity<VendaBalcaoLoja> salvarVendaBalcaoLoja(
			@RequestBody @Valid VendaBalcaoLoja vendaBalcaoLoja) throws ExceptionJumboSistemas {

		vendaBalcaoLoja.getPessoa().setEmpresa(vendaBalcaoLoja.getEmpresa());
		
		PessoaFisica pessoaFisica = pessoaFisicaController.salvarPessoaFisica(vendaBalcaoLoja.getPessoa())
				.getBody();
		vendaBalcaoLoja.setPessoa(pessoaFisica);
		
		VendaBalcaoLoja vendaBalcao	= vendaBalcaoLojaRepository.saveAndFlush(vendaBalcaoLoja);
		
		ContaReceber contaReceber = new ContaReceber();
		contaReceber.setDescricao("Venda Balcão loja  nº: " + vendaBalcaoLoja.getId());
		contaReceber.setDtPagamento(Calendar.getInstance().getTime());
		contaReceber.setDtVencimento(Calendar.getInstance().getTime());
		contaReceber.setEmpresa(vendaBalcaoLoja.getEmpresa());
		contaReceber.setPessoa(vendaBalcaoLoja.getPessoa());
		contaReceber.setStatus(StatusContaReceber.QUITADA);
		contaReceber.setValorDesconto(vendaBalcaoLoja.getValorDesconto());
		contaReceber.setValorTotal(vendaBalcaoLoja.getValorTotal());
		contaReceber.setTipo_venda(TipoVendaContaReceber.BALCAO);
		
		contaReceberRepository.saveAndFlush(contaReceber);
		
		
		return new ResponseEntity<VendaBalcaoLoja>(vendaBalcao, HttpStatus.OK);
	}
}
