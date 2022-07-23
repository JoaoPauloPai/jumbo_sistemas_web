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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
import br.com.jumbo.model.Endereco;
import br.com.jumbo.model.ItemVendaLoja;
import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.model.StatusRastreio;
import br.com.jumbo.model.VendaCompraLojaVirtual;
import br.com.jumbo.model.dto.ItemVendaDTO;
import br.com.jumbo.model.dto.VendaCompraLojaVirtualDTO;
import br.com.jumbo.repository.EnderecoRepository;
import br.com.jumbo.repository.NotaFiscalVendaRepository;
import br.com.jumbo.repository.StatusRastreioRepository;
import br.com.jumbo.repository.VendaCompraLojaVirtualRepository;
import br.com.jumbo.service.vendaLojaVirtualServise;

/**
 * @author João Paulo
 *
 *         24 de jan. de 2022 14:45:47
 */
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

	@Autowired
	private StatusRastreioRepository statusRastreioRepository;

	@Autowired
	private vendaLojaVirtualServise vendaLojaVirtualServise;

	@ResponseBody
	@PostMapping(value = "**/salvarVendaLoja")
	public ResponseEntity<VendaCompraLojaVirtualDTO> salvarVendaLoja(
			@RequestBody @Valid VendaCompraLojaVirtual vendaCompraLojaVirtual) throws ExceptionJumboSistemas {

		vendaCompraLojaVirtual.getPessoa().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		PessoaFisica pessoaFisica = pessoaFisicaController.salvarPessoaFisica(vendaCompraLojaVirtual.getPessoa())
				.getBody();
		vendaCompraLojaVirtual.setPessoa(pessoaFisica);

		vendaCompraLojaVirtual.getEnderecoCobranca().setPessoa(pessoaFisica);
		vendaCompraLojaVirtual.getEnderecoCobranca().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		Endereco enderecoCobranca = enderecoRepository.save(vendaCompraLojaVirtual.getEnderecoCobranca());
		vendaCompraLojaVirtual.setEnderecoCobranca(enderecoCobranca);

		vendaCompraLojaVirtual.getEnderecoEntrega().setPessoa(pessoaFisica);
		vendaCompraLojaVirtual.getEnderecoEntrega().setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		Endereco enderecoEntrega = enderecoRepository.save(vendaCompraLojaVirtual.getEnderecoEntrega());
		vendaCompraLojaVirtual.setEnderecoEntrega(enderecoEntrega);

		vendaCompraLojaVirtual.getNotaFiscalVenda().setEmpresa(vendaCompraLojaVirtual.getEmpresa());

		for (int i = 0; i < vendaCompraLojaVirtual.getItemVendaLojas().size(); i++) {
			vendaCompraLojaVirtual.getItemVendaLojas().get(i).setEmpresa(vendaCompraLojaVirtual.getEmpresa());
			vendaCompraLojaVirtual.getItemVendaLojas().get(i).setVendaCompraLojaVirtual(vendaCompraLojaVirtual);
		}

		/* Salva primeiro a venda e todo os dados */
		vendaCompraLojaVirtual = vendaCompraLojaVirtualRepository.saveAndFlush(vendaCompraLojaVirtual);

		StatusRastreio statusRastreio = new StatusRastreio();
		statusRastreio.setCentroDistribuicao("Loja Local");
		statusRastreio.setCidade("Local");
		statusRastreio.setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		statusRastreio.setEstado("Local");
		statusRastreio.setStatus("Inicio Compra");
		statusRastreio.setVendaCompraLojaVirtual(vendaCompraLojaVirtual);

		statusRastreioRepository.save(statusRastreio);

		/* Associa a venda gravada no banco com a nota fiscal */
		vendaCompraLojaVirtual.getNotaFiscalVenda().setVendaCompraLojaVirtual(vendaCompraLojaVirtual);

		/* Persiste novamente as nota fiscal novamente pra ficar amarrada na venda */
		notaFiscalVendaRepository.saveAndFlush(vendaCompraLojaVirtual.getNotaFiscalVenda());

		VendaCompraLojaVirtualDTO compraLojaVirtualDTO = new VendaCompraLojaVirtualDTO();
		compraLojaVirtualDTO.setValorTotal(vendaCompraLojaVirtual.getValorTotal());
		compraLojaVirtualDTO.setPessoa(vendaCompraLojaVirtual.getPessoa());

		compraLojaVirtualDTO.setEntrega(vendaCompraLojaVirtual.getEnderecoEntrega());
		compraLojaVirtualDTO.setCobranca(vendaCompraLojaVirtual.getEnderecoCobranca());

		compraLojaVirtualDTO.setValorDesc(vendaCompraLojaVirtual.getValorDesconto());
		compraLojaVirtualDTO.setValorFrete(vendaCompraLojaVirtual.getValorFrete());
		compraLojaVirtualDTO.setId(vendaCompraLojaVirtual.getId());

		for (ItemVendaLoja item : vendaCompraLojaVirtual.getItemVendaLojas()) {

			ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
			itemVendaDTO.setQuantidade(item.getQuantidade());
			itemVendaDTO.setProduto(item.getProduto());

			compraLojaVirtualDTO.getItemVendaLoja().add(itemVendaDTO);
		}

		return new ResponseEntity<VendaCompraLojaVirtualDTO>(compraLojaVirtualDTO, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/consultaVendaId/{id}")
	public ResponseEntity<VendaCompraLojaVirtualDTO> consultaVendaId(@PathVariable("id") Long idVenda) {

		VendaCompraLojaVirtual vendaLojaVirtual = vendaCompraLojaVirtualRepository.findByIdExclusao(idVenda);

		if (vendaLojaVirtual == null) {
			vendaLojaVirtual = new VendaCompraLojaVirtual();
		}

		VendaCompraLojaVirtualDTO compraLojaVirtualDTO = new VendaCompraLojaVirtualDTO();

		compraLojaVirtualDTO.setValorTotal(vendaLojaVirtual.getValorTotal());
		compraLojaVirtualDTO.setPessoa(vendaLojaVirtual.getPessoa());

		compraLojaVirtualDTO.setEntrega(vendaLojaVirtual.getEnderecoEntrega());
		compraLojaVirtualDTO.setCobranca(vendaLojaVirtual.getEnderecoCobranca());

		compraLojaVirtualDTO.setValorDesc(vendaLojaVirtual.getValorDesconto());
		compraLojaVirtualDTO.setValorFrete(vendaLojaVirtual.getValorFrete());
		compraLojaVirtualDTO.setId(vendaLojaVirtual.getId());

		for (ItemVendaLoja item : vendaLojaVirtual.getItemVendaLojas()) {

			ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
			itemVendaDTO.setQuantidade(item.getQuantidade());
			itemVendaDTO.setProduto(item.getProduto());

			compraLojaVirtualDTO.getItemVendaLoja().add(itemVendaDTO);
		}

		return new ResponseEntity<VendaCompraLojaVirtualDTO>(compraLojaVirtualDTO, HttpStatus.OK);
	}

	@ResponseBody
	@PutMapping(value = "**/ativaRegistroVendaBanco/{idVenda}")
	public ResponseEntity<String> ativaRegistroVendaBanco(@PathVariable(value = "idVenda") Long idVenda) {

		vendaLojaVirtualServise.ativaRegistroVendaBanco(idVenda);

		return new ResponseEntity<String>("Venda ativada com sucesso!.", HttpStatus.OK);

	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteVendaTotalBanco/{idVenda}")
	public ResponseEntity<String> deleteVendaTotalBanco(@PathVariable(value = "idVenda") Long idVenda) {

		vendaLojaVirtualServise.exclusaoTotalVendaBanco(idVenda);

		return new ResponseEntity<String>("Venda excluida com sucesso.", HttpStatus.OK);

	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteVendaTotalBanco2/{idVenda}")//Boolean
	public ResponseEntity<String> deleteVendaTotalBanco2(@PathVariable(value = "idVenda") Long idVenda) {

		vendaLojaVirtualServise.exclusaoTotalVendaBanco2(idVenda);

		return new ResponseEntity<String>("Venda excluida logicamente com sucesso!", HttpStatus.OK);

	}

}
