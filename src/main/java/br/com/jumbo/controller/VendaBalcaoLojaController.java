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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
import br.com.jumbo.enums.StatusContaReceber;
import br.com.jumbo.enums.TipoVendaContaReceber;
import br.com.jumbo.model.ContaReceber;
import br.com.jumbo.model.ItemVendaBalcao;
import br.com.jumbo.model.ItemVendaLoja;
import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.model.VendaBalcaoLoja;
import br.com.jumbo.model.dto.ItemVendaBalcaoDTO;
import br.com.jumbo.model.dto.ItemVendaDTO;
import br.com.jumbo.model.dto.VendaBalcaoLojaDto;
import br.com.jumbo.repository.ContaReceberRepository;
import br.com.jumbo.repository.VendaBalcaoLojaRepository;

/**
 * @author João Paulo
 *
 *         28 de jun. de 2022 17:36:41
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
	public ResponseEntity<VendaBalcaoLoja> salvarVendaBalcaoLoja(@RequestBody @Valid VendaBalcaoLoja vendaBalcaoLoja)
			throws ExceptionJumboSistemas {

		vendaBalcaoLoja.getPessoa().setEmpresa(vendaBalcaoLoja.getEmpresa());

		PessoaFisica pessoaFisica = pessoaFisicaController.salvarPessoaFisica(vendaBalcaoLoja.getPessoa()).getBody();
		vendaBalcaoLoja.setPessoa(pessoaFisica);

	
      //Salava a venda
		VendaBalcaoLoja vendaBalcao = vendaBalcaoLojaRepository.saveAndFlush(vendaBalcaoLoja);
		
		VendaBalcaoLojaDto vendaBalcaoLojaDTO = new VendaBalcaoLojaDto();

		for (ItemVendaBalcao item : vendaBalcaoLoja.getItemVendasBalcao()) {

			ItemVendaDTO itemVendaBalcaoDTO = new ItemVendaDTO();
			itemVendaBalcaoDTO.setQuantidade(item.getQuantidade());
			itemVendaBalcaoDTO.setProduto(item.getProduto());
			//itemVendaBalcaoDTO.setVendaBalcaoLoja(item.getVendaBalcaoLoja().getId());
			

			//vendaBalcaoLojaDTO.getItemVenda().add(itemVendaBalcaoDTO);
			

		}

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
		contaReceber.setVendaId(vendaBalcaoLoja.getId());

		contaReceberRepository.saveAndFlush(contaReceber);

		return new ResponseEntity<VendaBalcaoLoja>(vendaBalcao, HttpStatus.OK);
	}

//	@ResponseBody
//	@GetMapping(value = "**/consultaVendaBalcaoId/{id}")
	/**
	 * public ResponseEntity<VendaCompraLojaVirtualDTO>
	 * consultaVendaId(@PathVariable(name = "id") Long idVenda) {
	 * 
	 * //VendaBalcaoLoja vendaBalcaoLoja =
	 * vendaBalcaoLojaRepository.findByIdExclusao(idVenda);
	 * 
	 * VendaBalcaoLoja vendaBalcaoLoja =
	 * vendaBalcaoLojaRepository.findById(idVenda).orElse(null);
	 * 
	 * if (vendaLojaVirtual == null) { vendaLojaVirtual = new
	 * VendaCompraLojaVirtual(); }
	 * 
	 * VendaCompraLojaVirtualDTO compraLojaVirtualDTO = new
	 * VendaCompraLojaVirtualDTO();
	 * 
	 * compraLojaVirtualDTO.setValorTotal(vendaLojaVirtual.getValorTotal());
	 * compraLojaVirtualDTO.setPessoa(vendaLojaVirtual.getPessoa());
	 * 
	 * compraLojaVirtualDTO.setEntrega(vendaLojaVirtual.getEnderecoEntrega());
	 * compraLojaVirtualDTO.setCobranca(vendaLojaVirtual.getEnderecoCobranca());
	 * 
	 * compraLojaVirtualDTO.setValorDesc(vendaLojaVirtual.getValorDesconto());
	 * compraLojaVirtualDTO.setValorFrete(vendaLojaVirtual.getValorFrete());
	 * compraLojaVirtualDTO.setId(vendaLojaVirtual.getId());
	 * 
	 * for (ItemVendaLoja item : vendaLojaVirtual.getItemVendaLojas()) {
	 * 
	 * ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
	 * itemVendaDTO.setQuantidade(item.getQuantidade());
	 * itemVendaDTO.setProduto(item.getProduto());
	 * 
	 * compraLojaVirtualDTO.getItemVendaLoja().add(itemVendaDTO); } return new
	 * ResponseEntity<VendaBalcaoLoja>(vendaBalcaoLoja, HttpStatus.OK);
	 * 
	 * 
	 * }
	 **/

	@ResponseBody
	@GetMapping(value = "**/consultaVendaBalcaoId/{id}")
	public ResponseEntity<VendaBalcaoLoja> consultaVendaBalcaoId(@PathVariable(name = "id") long id)
			throws ExceptionJumboSistemas {

		VendaBalcaoLoja vendaBalcao = vendaBalcaoLojaRepository.findById(id).orElse(null);

		if (vendaBalcao == null) {

			throw new ExceptionJumboSistemas("Não encotrado Venda com código " + id);
		}

		return new ResponseEntity<VendaBalcaoLoja>(vendaBalcao, HttpStatus.OK);

	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteVendaBalcaoPorId/{id}")
	public ResponseEntity<?> deleteVendaBalcaoPorId(@PathVariable("id") Long id) {

		vendaBalcaoLojaRepository.deleteById(id);

		return new ResponseEntity("Acesso deletado por Id com sucesso!", HttpStatus.OK);
	}
}
