/**
 * 
 */
package br.com.jumbo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import br.com.jumbo.model.ItemVendaSite;
import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.model.VendaBalcaoLoja;
import br.com.jumbo.model.dto.ItemVendaBalcaoDTO;
import br.com.jumbo.model.dto.ItemVendaSiteDTO;
import br.com.jumbo.model.dto.ObejtoRequisicaoRelatorioVendaBalcaoLoja;
import br.com.jumbo.model.dto.VendaBalcaoLojaDto;
import br.com.jumbo.repository.ContaReceberRepository;
import br.com.jumbo.repository.VendaBalcaoLojaRepository;
import br.com.jumbo.service.VendaBalcaoLojaService;

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
	VendaBalcaoLojaService vendaBalcaoLojaService;

	@Autowired
	ContaReceberRepository contaReceberRepository;

	@ResponseBody
	@PostMapping(value = "**/salvarVendaBalcaoLoja")
	public ResponseEntity<VendaBalcaoLoja> salvarVendaBalcaoLoja(@RequestBody @Valid VendaBalcaoLoja vendaBalcaoLoja)
			throws ExceptionJumboSistemas {

		vendaBalcaoLoja.getPessoa().setEmpresa(vendaBalcaoLoja.getEmpresa());

		PessoaFisica pessoaFisica = pessoaFisicaController.salvarPessoaFisica(vendaBalcaoLoja.getPessoa()).getBody();
		vendaBalcaoLoja.setPessoa(pessoaFisica);

		// for (int i = 0; i < vendaCompraLojaVirtual.getItemVendaLojas().size(); i++) {
		// vendaCompraLojaVirtual.getItemVendaLojas().get(i).setEmpresa(vendaCompraLojaVirtual.getEmpresa());
		// vendaCompraLojaVirtual.getItemVendaLojas().get(i).setVendaCompraLojaVirtual(vendaCompraLojaVirtual);
		// }
		for (int i = 0; i < vendaBalcaoLoja.getItemVendasBalcao().size(); i++) {
			vendaBalcaoLoja.getItemVendasBalcao().get(i).setEmpresa(vendaBalcaoLoja.getEmpresa());
			vendaBalcaoLoja.getItemVendasBalcao().get(i).setVendaBalcaoLoja(vendaBalcaoLoja);
		}

		// Salava a venda
		VendaBalcaoLoja vendaBalcao = vendaBalcaoLojaRepository.saveAndFlush(vendaBalcaoLoja);

		VendaBalcaoLojaDto vendaBalcaoLojaDto = new VendaBalcaoLojaDto();

		for (ItemVendaBalcao item : vendaBalcao.getItemVendasBalcao()) {

			ItemVendaBalcaoDTO itemVendaDTO = new ItemVendaBalcaoDTO();
			itemVendaDTO.setQuantidade(item.getQuantidade());
			itemVendaDTO.setProduto(item.getProduto());

			vendaBalcaoLojaDto.getItemVenda().add(itemVendaDTO);

		}

		VendaBalcaoLojaDto vendaBalcaoLojaDTO = new VendaBalcaoLojaDto();

	

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


	@ResponseBody
	@GetMapping(value = "**/consultaVendaBalcaoId/{id}")
	public ResponseEntity<VendaBalcaoLoja> consultaVendaBalcaoId(@PathVariable(name = "id") long id)
			throws ExceptionJumboSistemas {

		VendaBalcaoLoja vendaBalcao = vendaBalcaoLojaRepository.findById(id).orElse(null);

		if (vendaBalcao == null) {

			throw new ExceptionJumboSistemas("Não encotrado Venda-Balcão com o código " + id);
		}

		return new ResponseEntity<VendaBalcaoLoja>(vendaBalcao, HttpStatus.OK);

	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteVendaBalcaoPorId/{id}")
	public ResponseEntity<?> deleteVendaBalcaoPorId(@PathVariable("id") Long id) throws ExceptionJumboSistemas {

		VendaBalcaoLoja vendaBalcao = vendaBalcaoLojaRepository.findById(id).orElse(null);

		if (vendaBalcao == null) {

			throw new ExceptionJumboSistemas("Não encotrado Venda com código " + id);
		}

		vendaBalcaoLojaRepository.deleteById(id);

		return new ResponseEntity("Venda-Balcão deletado por Id com sucesso!", HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "**/relatorioVendaBalcaLoja")
	public ResponseEntity<List<ObejtoRequisicaoRelatorioVendaBalcaoLoja>> relatorioVendaBalca(@Valid 
			             @RequestBody  ObejtoRequisicaoRelatorioVendaBalcaoLoja obejtoRequisicaoRelatorioVendaBalcaoLoja){
		
		List<ObejtoRequisicaoRelatorioVendaBalcaoLoja> retorno = new ArrayList<ObejtoRequisicaoRelatorioVendaBalcaoLoja>();
		
		retorno = vendaBalcaoLojaService.relatorioVendaBalcaoLoja(obejtoRequisicaoRelatorioVendaBalcaoLoja);
		
		return new ResponseEntity<List<ObejtoRequisicaoRelatorioVendaBalcaoLoja>>(retorno, HttpStatus.OK);
		
	}
}
