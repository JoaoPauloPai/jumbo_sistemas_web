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
import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.model.VendaCompraLojaVirtual;
import br.com.jumbo.model.dto.VendaCompraLojaVirtualDTO;
import br.com.jumbo.repository.EnderecoRepository;
import br.com.jumbo.repository.NotaFiscalVendaRepository;
import br.com.jumbo.repository.VendaCompraLojaVirtualRepository;

/**
 * @author João Paulo
 *
 *         24 de jan. de 2022 14:45:47
 */
@Controller
@RestController
public class VendaCompraLojaVirtualController {

	@Autowired
	private VendaCompraLojaVirtualRepository vendaCompraLojaVirtualRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PessoaFisicaController pessoaFisicaController;

	@Autowired
	private NotaFiscalVendaRepository notaFiscalVendaRepository;
	

	@ResponseBody
	@PostMapping(value = "**/salvarVendaLoja")
	public ResponseEntity<VendaCompraLojaVirtualDTO> salvarVendaLoja(
			@RequestBody @Valid VendaCompraLojaVirtual vendaCompraLojaVirtual) throws ExceptionJumboSistemas {

		vendaCompraLojaVirtual.getEmpresa().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
	   // PessoaFisica pessoaFisica = pessoaFisicaController.salvarPessoaFisica(vendaCompraLojaVirtual.getPessoa()).getBody;

	//	vendaCompraLojaVirtual.setPessoa(pessoaFisica);
		
	//	vendaCompraLojaVirtual.getEnderecoEntrega().setPessoa(pessoaFisica);
		vendaCompraLojaVirtual.getEnderecoEntrega().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
	//	Endereco enderecoEntrega = enderecoRepository.save(vendaCompraLojaVirtual.getEnderecoEntrega());
	//	vendaCompraLojaVirtual.setEnderecoEntrega(enderecoEntrega);
		
		vendaCompraLojaVirtual.getNotaFiscalVenda().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		/*Salva primeiro a venda e todo os dados*/
		vendaCompraLojaVirtual = vendaCompraLojaVirtualRepository.saveAndFlush(vendaCompraLojaVirtual);
		
		/*Associa a venda gravada no banco com a nota fiscal*/
		vendaCompraLojaVirtual.getNotaFiscalVenda().setVendaCompraLojaVirtual(vendaCompraLojaVirtual);
		
		/*Persiste novamente as nota fiscal novamente pra ficar amarrada na venda*/
		notaFiscalVendaRepository.saveAndFlush(vendaCompraLojaVirtual.getNotaFiscalVenda());		

		VendaCompraLojaVirtualDTO compraLojaVirtualDTO = new VendaCompraLojaVirtualDTO();
		compraLojaVirtualDTO.setValorTotal(vendaCompraLojaVirtual.getValorTotal());

		return new ResponseEntity<VendaCompraLojaVirtualDTO>(compraLojaVirtualDTO, HttpStatus.OK);
	}
}
