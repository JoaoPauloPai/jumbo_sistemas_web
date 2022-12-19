/**
 * 
 */
package br.com.jumbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
import br.com.jumbo.enums.TipoPessoa;
import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.model.Usuario;
import br.com.jumbo.repository.PessoaFisicaRepository;
import br.com.jumbo.service.PessoaFisicaService;
import br.com.jumbo.service.ServiceContagemAcessoApi;
import br.com.jumbo.util.ValidaCPF;

/**
 * @author João Paulo
 *
 *         7 de mai. de 2022 16:23:00
 */
@RestController
public class PessoaFisicaController {

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	private ServiceContagemAcessoApi serviceContagemAcessoApi;

	@Autowired
	private PessoaFisicaService pessoaFisicaService;

	@ResponseBody
	@GetMapping(value = "**/consultaPfNome/{nome}")
	public ResponseEntity<List<PessoaFisica>> consultaPfNome(@PathVariable("nome") String nome) {

		List<PessoaFisica> fisicas = pessoaFisicaRepository.pesquisaPorNomePF(nome.trim().toUpperCase());

		serviceContagemAcessoApi.atualizaAcessoEndPointPf();

		return new ResponseEntity<List<PessoaFisica>>(fisicas, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/consultaPfCpf/{cpf}")
	public ResponseEntity<List<PessoaFisica>> consultaPfCpf(@PathVariable("cpf") String cpf) {

		List<PessoaFisica> fisicas = pessoaFisicaRepository.pesquisaPessoaFisicaPorCpf(cpf);

		return new ResponseEntity<List<PessoaFisica>>(fisicas, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "**/salvarPessoaFisica")
	public ResponseEntity<PessoaFisica> salvarPessoaFisica(@RequestBody PessoaFisica pessoaFisica)
			throws ExceptionJumboSistemas {
		
		if (pessoaFisica == null) {
			throw new ExceptionJumboSistemas("Pessoa fisica não pode ser NULL");
		}
		if (pessoaFisica.getTipoPessoa() == null) {
			pessoaFisica.setTipoPessoa(TipoPessoa.FISICA.name());
		}

		if (pessoaFisica.getId() == null && pessoaFisicaRepository.existeCpfCadastrado(pessoaFisica.getCpf()) != null) {
			throw new ExceptionJumboSistemas("Já existe CPF cadastrado com o número: " + pessoaFisica.getCpf());
		}

		if (!ValidaCPF.isCPF(pessoaFisica.getCpf())) {
			throw new ExceptionJumboSistemas("CPF : " + pessoaFisica.getCpf() + " está inválido.");
		}
		//pessoaFisica.getEmpresa().setEmpresa(usuario.);
	//	vendaCompraLojaVirtual.getPessoa().setEmpresa(vendaCompraLojaVirtual.getEmpresa());

		pessoaFisica = pessoaFisicaService.salvarPessoaFisica(pessoaFisica);
		


		return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);
	}
	

	@ResponseBody
	@GetMapping(value = "**/listaPessoaFisica")
	public ResponseEntity<List<PessoaFisica>> listaPessoaFisica() {

		List<PessoaFisica> pessFis = (List<PessoaFisica>) pessoaFisicaRepository.findAll();

		return new ResponseEntity<List<PessoaFisica>>(pessFis, HttpStatus.OK);

	}
	
	@ResponseBody
	@GetMapping(value = "**/consultaPessoaFisicaNome/{nome}")
	public ResponseEntity<List<PessoaFisica>> consultaPessoaFisicaNome(@PathVariable("nome") String nome) {
		
		List<PessoaFisica> fisicas = pessoaFisicaRepository.pesquisaPorNomePF(nome.trim().toUpperCase());
		
		return new ResponseEntity<List<PessoaFisica>>(fisicas, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping(value = "**/deletePessoaFisicaPorId/{id}")
	public ResponseEntity<?> deletePessoaFisicaPorId(@PathVariable("id") Long id) {

		pessoaFisicaRepository.deleteById(id);

		return new ResponseEntity("Pessoa Física deletado por Id com sucesso!", HttpStatus.OK);
	}

}
