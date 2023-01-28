/**
 * 
 */
package br.com.jumbo.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;
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
import br.com.jumbo.enums.StatusContaReceber;
import br.com.jumbo.enums.TipoVendaContaReceber;
import br.com.jumbo.model.ContaReceber;
import br.com.jumbo.model.Endereco;
import br.com.jumbo.model.ItemVendaLoja;
import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.model.StatusRastreio;
import br.com.jumbo.model.VendaSiteLoja;
import br.com.jumbo.model.dto.ItemVendaDTO;
import br.com.jumbo.model.dto.VendaSiteLojaDTO;
import br.com.jumbo.repository.ContaReceberRepository;
import br.com.jumbo.repository.EnderecoRepository;
import br.com.jumbo.repository.NotaFiscalVendaRepository;
import br.com.jumbo.repository.StatusRastreioRepository;
import br.com.jumbo.repository.VendaSiteLojaRepository;
import br.com.jumbo.service.ServiceSendEmail;
import br.com.jumbo.service.VendaSitelojaServise;

/**
 * @author João Paulo
 *
 *         24 de jan. de 2022 14:45:47
 */
@RestController
public class VendaSiteLojaController {

	@Autowired
	private VendaSiteLojaRepository vendaSitelojaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PessoaFisicaController pessoaFisicaController;

	@Autowired
	private NotaFiscalVendaRepository notaFiscalVendaRepository;

	@Autowired
	private StatusRastreioRepository statusRastreioRepository;

	@Autowired
	private VendaSitelojaServise vendaSiteLojaServise;

	@Autowired
	private ContaReceberRepository contaReceberRepository;

	@Autowired
	private ServiceSendEmail serviceSendEmail;

	@ResponseBody
	@PostMapping(value = "**/salvarVendaOnLine")
	public ResponseEntity<VendaSiteLojaDTO> salvarVendaOnLine(@RequestBody @Valid VendaSiteLoja vendaSiteLoja)
			throws ExceptionJumboSistemas, UnsupportedEncodingException, MessagingException {

		vendaSiteLoja.getPessoa().setEmpresa(vendaSiteLoja.getEmpresa());
		PessoaFisica pessoaFisica = pessoaFisicaController.salvarPessoaFisica(vendaSiteLoja.getPessoa()).getBody();
		vendaSiteLoja.setPessoa(pessoaFisica);

		vendaSiteLoja.getEnderecoCobranca().setPessoa(pessoaFisica);
		vendaSiteLoja.getEnderecoCobranca().setEmpresa(vendaSiteLoja.getEmpresa());
		Endereco enderecoCobranca = enderecoRepository.save(vendaSiteLoja.getEnderecoCobranca());
		vendaSiteLoja.setEnderecoCobranca(enderecoCobranca);

		vendaSiteLoja.getEnderecoEntrega().setPessoa(pessoaFisica);
		vendaSiteLoja.getEnderecoEntrega().setEmpresa(vendaSiteLoja.getEmpresa());
		Endereco enderecoEntrega = enderecoRepository.save(vendaSiteLoja.getEnderecoEntrega());
		vendaSiteLoja.setEnderecoEntrega(enderecoEntrega);

		vendaSiteLoja.getNotaFiscalVenda().setEmpresa(vendaSiteLoja.getEmpresa());

		for (int i = 0; i < vendaSiteLoja.getItemVendaLojas().size(); i++) {
			vendaSiteLoja.getItemVendaLojas().get(i).setEmpresa(vendaSiteLoja.getEmpresa());
			vendaSiteLoja.getItemVendaLojas().get(i).setVendaSiteLoja(vendaSiteLoja);
		}

		/* Salva primeiro a venda e todo os dados */
		vendaSiteLoja = vendaSitelojaRepository.saveAndFlush(vendaSiteLoja);

		StatusRastreio statusRastreio = new StatusRastreio();
		statusRastreio.setCentroDistribuicao("Loja Local");
		statusRastreio.setCidade("Local");
		statusRastreio.setEmpresa(vendaSiteLoja.getEmpresa());
		statusRastreio.setEstado("Local");
		statusRastreio.setStatus("Inicio Compra");
		statusRastreio.setVenda_site_loja(vendaSiteLoja);

		statusRastreioRepository.save(statusRastreio);

		/* Associa a venda gravada no banco com a nota fiscal */
		vendaSiteLoja.getNotaFiscalVenda().setVendaSiteLoja(vendaSiteLoja);

		/* Persiste novamente as nota fiscal novamente pra ficar amarrada na venda */
		notaFiscalVendaRepository.saveAndFlush(vendaSiteLoja.getNotaFiscalVenda());

		VendaSiteLojaDTO vendaSiteLojaDTO = new VendaSiteLojaDTO();
		vendaSiteLojaDTO.setValorTotal(vendaSiteLoja.getValorTotal());
		vendaSiteLojaDTO.setPessoa(vendaSiteLoja.getPessoa());

		vendaSiteLojaDTO.setEntrega(vendaSiteLoja.getEnderecoEntrega());
		vendaSiteLojaDTO.setCobranca(vendaSiteLoja.getEnderecoCobranca());

		vendaSiteLojaDTO.setValorDesc(vendaSiteLoja.getValorDesconto());
		vendaSiteLojaDTO.setValorFrete(vendaSiteLoja.getValorFrete());
		vendaSiteLojaDTO.setId(vendaSiteLoja.getId());

		for (ItemVendaLoja item : vendaSiteLoja.getItemVendaLojas()) {

			ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
			  itemVendaDTO.setQuantidade(item.getQuantidade());
			  itemVendaDTO.setProduto(item.getProduto());
			
	

			vendaSiteLojaDTO.getItemVendaLoja().add(itemVendaDTO);
		}

		ContaReceber contaReceber = new ContaReceber();
		contaReceber.setDescricao("Venda da loja virtual nº: " + vendaSiteLoja.getId());
		contaReceber.setDtPagamento(Calendar.getInstance().getTime());
		contaReceber.setDtVencimento(Calendar.getInstance().getTime());
		contaReceber.setEmpresa(vendaSiteLoja.getEmpresa());
		contaReceber.setPessoa(vendaSiteLoja.getPessoa());
		contaReceber.setStatus(StatusContaReceber.QUITADA);
		contaReceber.setValorDesconto(vendaSiteLoja.getValorDesconto());
		contaReceber.setValorTotal(vendaSiteLoja.getValorTotal());
		contaReceber.setTipo_venda(TipoVendaContaReceber.SITE);
		contaReceber.setVendaId(vendaSiteLoja.getId());

		contaReceberRepository.saveAndFlush(contaReceber);

		/* Emil para o comprador */
		StringBuilder msgemail = new StringBuilder();
		msgemail.append("Olá, ").append(pessoaFisica.getNome()).append("!").append("<br/>");
		msgemail.append("Você realizou a compra de nº: ").append(vendaSiteLoja.getId()).append("<br/>");
		msgemail.append("Na loja ").append(vendaSiteLoja.getEmpresa().getNomeFantasia()).append("<br/>");
		msgemail.append("Agradecemos por nos escolher");
		/* assunto, msg, destino */
		serviceSendEmail.enviarEmailHtml("Compra Realizada", msgemail.toString(), pessoaFisica.getEmail());

		/* Email para o vendedor */
		msgemail = new StringBuilder();
		msgemail.append("Você realizou uma venda, nº ").append(vendaSiteLoja.getId());
		serviceSendEmail.enviarEmailHtml("Venda Realizada", msgemail.toString(), vendaSiteLoja.getEmpresa().getEmail());

		return new ResponseEntity<VendaSiteLojaDTO>(vendaSiteLojaDTO, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/consultaVendaId/{id}")
	public ResponseEntity<VendaSiteLojaDTO> consultaVendaId(@PathVariable("id") Long idVenda) {

		VendaSiteLoja vendaSiteLoja = vendaSitelojaRepository.findByIdExclusao(idVenda);

		if (vendaSiteLoja == null) {
			vendaSiteLoja = new VendaSiteLoja();
		}

		VendaSiteLojaDTO vendaSitelojaDTO = new VendaSiteLojaDTO();

		vendaSitelojaDTO.setValorTotal(vendaSiteLoja.getValorTotal());
		vendaSitelojaDTO.setPessoa(vendaSiteLoja.getPessoa());

		vendaSitelojaDTO.setEntrega(vendaSiteLoja.getEnderecoEntrega());
		vendaSitelojaDTO.setCobranca(vendaSiteLoja.getEnderecoCobranca());

		vendaSitelojaDTO.setValorDesc(vendaSiteLoja.getValorDesconto());
		vendaSitelojaDTO.setValorFrete(vendaSiteLoja.getValorFrete());
		vendaSitelojaDTO.setId(vendaSiteLoja.getId());

		for (ItemVendaLoja item : vendaSiteLoja.getItemVendaLojas()) {

			ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
			itemVendaDTO.setQuantidade(item.getQuantidade());
			itemVendaDTO.setProduto(item.getProduto());

			vendaSitelojaDTO.getItemVendaLoja().add(itemVendaDTO);
		}

		return new ResponseEntity<VendaSiteLojaDTO>(vendaSitelojaDTO, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/consultaVendaDinamica/{valor}/{tipoconsulta}")
	public ResponseEntity<List<VendaSiteLojaDTO>> consultaVendaDinamica(@PathVariable("valor") String valor,
			@PathVariable("tipoconsulta") String tipoconsulta) {

		List<VendaSiteLoja> vendaSiteLoja = null;

		if (tipoconsulta.equalsIgnoreCase("POR_ID_PROD")) {
			vendaSiteLoja = vendaSitelojaRepository.vendaPorProduto(Long.parseLong(valor));

			vendaSiteLoja = vendaSiteLoja = new ArrayList<VendaSiteLoja>();
		}

		if (vendaSiteLoja == null) {
			vendaSiteLoja = new ArrayList<VendaSiteLoja>();
		}

		List<VendaSiteLojaDTO> vendaSitelojaDTOList = new ArrayList<VendaSiteLojaDTO>();

		for (VendaSiteLoja vsl : vendaSiteLoja) {

			VendaSiteLojaDTO vendaSitelojaDTO = new VendaSiteLojaDTO();

			vendaSitelojaDTO.setValorTotal(vsl.getValorTotal());
			vendaSitelojaDTO.setPessoa(vsl.getPessoa());

			vendaSitelojaDTO.setEntrega(vsl.getEnderecoEntrega());
			vendaSitelojaDTO.setCobranca(vsl.getEnderecoCobranca());

			vendaSitelojaDTO.setValorDesc(vsl.getValorDesconto());
			vendaSitelojaDTO.setValorFrete(vsl.getValorFrete());
			vendaSitelojaDTO.setId(vsl.getId());

			for (ItemVendaLoja item : vsl.getItemVendaLojas()) {

				ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
				itemVendaDTO.setQuantidade(item.getQuantidade());
				itemVendaDTO.setProduto(item.getProduto());

				vendaSitelojaDTO.getItemVendaLoja().add(itemVendaDTO);

			}
			vendaSitelojaDTOList.add(vendaSitelojaDTO);
		}

		// return new ResponseEntity<List<VendaSiteLojaDTO>>(vendaSitelojaDTOList,
		// HttpStatus.OK);
		return new ResponseEntity<List<VendaSiteLojaDTO>>(vendaSitelojaDTOList, HttpStatus.OK);
	}

	@ResponseBody
	@PutMapping(value = "**/ativaRegistroVendaBanco/{idVenda}")
	public ResponseEntity<String> ativaRegistroVendaBanco(@PathVariable(value = "idVenda") Long idVenda) {

		vendaSiteLojaServise.ativaRegistroVendaBanco(idVenda);

		return new ResponseEntity<String>("Venda ativada com sucesso!.", HttpStatus.OK);

	}

	// Exclusão total do Banco
	@ResponseBody
	@DeleteMapping(value = "**/deleteVendaTotalBanco/{idVenda}")
	public ResponseEntity<String> deleteVendaTotalBanco(@PathVariable(value = "idVenda") Long idVenda) {

		vendaSiteLojaServise.exclusaoTotalVendaBanco(idVenda);

		return new ResponseEntity<String>("Venda excluida com sucesso.", HttpStatus.OK);

	}

	// Exclusão lógica no Banco
	@ResponseBody
	@DeleteMapping(value = "**/deleteVendaTotalBanco2/{idVenda}") // Boolean
	public ResponseEntity<String> deleteVendaTotalBanco2(@PathVariable(value = "idVenda") Long idVenda) {

		vendaSiteLojaServise.exclusaoTotalVendaBanco2(idVenda);

		return new ResponseEntity<String>("Venda excluida logicamente com sucesso!", HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/consultaVendaDinamicaFaixaData/{data1}/{data2}")

	public ResponseEntity<List<VendaSiteLojaDTO>> consultaVendaDinamicaFaixaData(@PathVariable("data1") String data1,
			@PathVariable("data2") String data2) throws ParseException {

		List<VendaSiteLoja> vendaSiteLoja = null;

		vendaSiteLoja = vendaSiteLojaServise.consultaVendaFaixaData(data1, data2);

		if (vendaSiteLoja == null) {
			vendaSiteLoja = new ArrayList<VendaSiteLoja>();
		}

		List<VendaSiteLojaDTO> compraLojaVirtualDTOList = new ArrayList<VendaSiteLojaDTO>();

		for (VendaSiteLoja vcl : vendaSiteLoja) {

			VendaSiteLojaDTO vendaSiteLojaDTO = new VendaSiteLojaDTO();

			vendaSiteLojaDTO.setValorTotal(vcl.getValorTotal());
			vendaSiteLojaDTO.setPessoa(vcl.getPessoa());

			vendaSiteLojaDTO.setEntrega(vcl.getEnderecoEntrega());
			vendaSiteLojaDTO.setCobranca(vcl.getEnderecoCobranca());

			vendaSiteLojaDTO.setValorDesc(vcl.getValorDesconto());
			vendaSiteLojaDTO.setValorFrete(vcl.getValorFrete());
			vendaSiteLojaDTO.setId(vcl.getId());

			for (ItemVendaLoja item : vcl.getItemVendaLojas()) {

				ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
				itemVendaDTO.setQuantidade(item.getQuantidade());
				itemVendaDTO.setProduto(item.getProduto());

				vendaSiteLojaDTO.getItemVendaLoja().add(itemVendaDTO);
			}

			compraLojaVirtualDTOList.add(vendaSiteLojaDTO);

		}

		return new ResponseEntity<List<VendaSiteLojaDTO>>(compraLojaVirtualDTOList, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/vendaPorCliente/{idCliente}")
	public ResponseEntity<List<VendaSiteLojaDTO>> vendaPorCliente(@PathVariable("idCliente") Long idCliente) {

		List<VendaSiteLoja> compraLojaVirtual = vendaSitelojaRepository.vendaPorCliente(idCliente);

		if (compraLojaVirtual == null) {
			compraLojaVirtual = new ArrayList<VendaSiteLoja>();
		}

		List<VendaSiteLojaDTO> compraLojaVirtualDTOList = new ArrayList<VendaSiteLojaDTO>();

		for (VendaSiteLoja vcl : compraLojaVirtual) {

			VendaSiteLojaDTO compraLojaVirtualDTO = new VendaSiteLojaDTO();

			compraLojaVirtualDTO.setValorTotal(vcl.getValorTotal());
			compraLojaVirtualDTO.setPessoa(vcl.getPessoa());

			compraLojaVirtualDTO.setEntrega(vcl.getEnderecoEntrega());
			compraLojaVirtualDTO.setCobranca(vcl.getEnderecoCobranca());

			compraLojaVirtualDTO.setValorDesc(vcl.getValorDesconto());
			compraLojaVirtualDTO.setValorFrete(vcl.getValorFrete());
			compraLojaVirtualDTO.setId(vcl.getId());

			for (ItemVendaLoja item : vcl.getItemVendaLojas()) {

				ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
				itemVendaDTO.setQuantidade(item.getQuantidade());
				itemVendaDTO.setProduto(item.getProduto());

				compraLojaVirtualDTO.getItemVendaLoja().add(itemVendaDTO);
			}

			compraLojaVirtualDTOList.add(compraLojaVirtualDTO);

		}

		return new ResponseEntity<List<VendaSiteLojaDTO>>(compraLojaVirtualDTOList, HttpStatus.OK);
	}

}
