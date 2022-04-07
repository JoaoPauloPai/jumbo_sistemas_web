/**
 * 
 */
package br.com.jumbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.model.PessoaJuridica;
import br.com.jumbo.repository.PessoaFisicaRepository;
import br.com.jumbo.repository.PessoaRepository;
import br.com.jumbo.service.PessoaUserService;
import br.com.jumbo.util.ValidaCNPJ;

/**
 * @author João Paulo
 *
 *         10 de mar. de 2022 14:36:00
 */
@RestController
public class PessoaController {

	@Autowired
	private PessoaUserService pessoaUserService;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@ResponseBody
	@PostMapping(value = "**/salvarPessoaJuridica")
	public ResponseEntity<PessoaJuridica> salvarPessoaJuridica(@RequestBody PessoaJuridica pessoaJuridica)
			throws ExceptionJumboSistemas {

		if (pessoaJuridica == null) {
			throw new ExceptionJumboSistemas("Pessoa Jurídica não pode ser null");
		}

		if (pessoaJuridica.getId() == null && pessoaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj()) != null) {
			throw new ExceptionJumboSistemas("Ja existe cnpj com o número: " + pessoaJuridica.getCnpj());
		}

		if (pessoaJuridica.getId() == null
				&& pessoaRepository.existeInsEstadualCadastrado(pessoaJuridica.getInscEstadual()) != null) {
			throw new ExceptionJumboSistemas(
					"Já existe Inscrição estadual cadastrado com o número: " + pessoaJuridica.getInscEstadual());
		}

		if (!ValidaCNPJ.isCNPJ(pessoaJuridica.getCnpj())) {
			throw new ExceptionJumboSistemas("Cnpj " + pessoaJuridica.getCnpj() + "Está inváliudo.");

		}

		pessoaJuridica = pessoaUserService.salvarPessoaJuridica(pessoaJuridica);

		return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "**/salvarPessoaFisica")
	public ResponseEntity<PessoaFisica> salvarPessoaFisica(@RequestBody PessoaFisica pessoaFisica)
			throws ExceptionJumboSistemas {

		if (pessoaFisica == null) {
			throw new ExceptionJumboSistemas("Pessoa Física não pode ser NULL. ");
		}

		PessoaFisica pess = pessoaUserService.save(pessoaFisica);

		return new ResponseEntity<PessoaFisica>(pess, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/listaPessoaFisica")
	public ResponseEntity<List<PessoaFisica>> listaPessoaFisica() {

		List<PessoaFisica> pessFis = (List<PessoaFisica>) pessoaFisicaRepository.findAll();

		return new ResponseEntity<List<PessoaFisica>>(pessFis, HttpStatus.OK);

	}

}
