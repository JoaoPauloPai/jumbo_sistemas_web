/**
 * 
 */
package br.com.jumbo.controller;

import java.util.List;

import javax.validation.Valid;

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
import br.com.jumbo.model.Endereco;
import br.com.jumbo.model.PessoaJuridica;
import br.com.jumbo.model.dto.CepDTO;
import br.com.jumbo.model.dto.ConsultaCnpjDto;
import br.com.jumbo.repository.EnderecoRepository;
import br.com.jumbo.repository.PessoaJuridicaRepository;
import br.com.jumbo.repository.PessoaRepository;
import br.com.jumbo.service.PessoaUserService;
import br.com.jumbo.util.ValidaCNPJ;

/**
 * @author João Paulo
 *
 *         10 de mar. de 2022 14:36:00
 */
@RestController
public class PessoaJuridicaController {

	@Autowired
	private PessoaUserService pessoaUserService;

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@ResponseBody
	@GetMapping(value = "**/consultaCep/{cep}")
	public ResponseEntity<CepDTO> consultaCep(@PathVariable("cep") String cep) {

		return new ResponseEntity<CepDTO>(pessoaUserService.consultaCep(cep), HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/consultaCnpjReceitaWs/{cnpj}")
	public ResponseEntity<ConsultaCnpjDto> consultaCnpjReceitaWs(@PathVariable("cnpj") String cnpj) {

		return new ResponseEntity<ConsultaCnpjDto>(pessoaUserService.consultaCnpjReceitaWS(cnpj), HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/consultaPjNome/{nome}")
	public ResponseEntity<List<PessoaJuridica>> consultaPjNome(@PathVariable("nome") String nome) {

		List<PessoaJuridica> fisicas = pessoaJuridicaRepository.pesquisaPjPorNome(nome.trim().toUpperCase());

		return new ResponseEntity<List<PessoaJuridica>>(fisicas, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/consultaCnpjPj/{cnp}")
	public ResponseEntity<List<PessoaJuridica>> consultaCnpjPj(@PathVariable("cnpj") String cnpj) {

		List<PessoaJuridica> fisicas = pessoaJuridicaRepository.existeCnpjCadastradoList(cnpj.trim().toUpperCase());

		return new ResponseEntity<List<PessoaJuridica>>(fisicas, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping(value = "**/salvarPessoaJuridica")
	public ResponseEntity<PessoaJuridica> salvarPessoaJuridica(@RequestBody @Valid PessoaJuridica pessoaJuridica)
			throws ExceptionJumboSistemas {


		if (pessoaJuridica == null) {
			throw new ExceptionJumboSistemas("Pessoa Jurídica não pode ser null");
		}
		if (pessoaJuridica.getTipoPessoa() == null) {
			throw new ExceptionJumboSistemas("Informe o Tipo Pessoa. ");
		}

		if (pessoaJuridica.getId() == null && pessoaJuridicaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj()) != null) {
			throw new ExceptionJumboSistemas("Ja existe cnpj com o número: " + pessoaJuridica.getCnpj());
		}

		if (pessoaJuridica.getId() == null
				&& pessoaJuridicaRepository.existeInsEstadualCadastrado(pessoaJuridica.getInscEstadual()) != null) {
			throw new ExceptionJumboSistemas(
					"Já existe Inscrição estadual cadastrado com o número: " + pessoaJuridica.getInscEstadual());
		}

		if (!ValidaCNPJ.isCNPJ(pessoaJuridica.getCnpj())) {
			throw new ExceptionJumboSistemas("Cnpj " + pessoaJuridica.getCnpj() + "Está inváliudo.");

		}

		if (pessoaJuridica.getId() == null || pessoaJuridica.getId() <= 0) {

			for (int p = 0; p < pessoaJuridica.getEnderecos().size(); p++) {

				CepDTO cepDTO = pessoaUserService.consultaCep(pessoaJuridica.getEnderecos().get(p).getCep());

				pessoaJuridica.getEnderecos().get(p).setBairro(cepDTO.getBairro());
				pessoaJuridica.getEnderecos().get(p).setCidade(cepDTO.getLocalidade());
				pessoaJuridica.getEnderecos().get(p).setComplemento(cepDTO.getComplemento());
				pessoaJuridica.getEnderecos().get(p).setRuaLogra(cepDTO.getLogradouro());
				pessoaJuridica.getEnderecos().get(p).setUf(cepDTO.getUf());

			}
		} else {

			for (int p = 0; p < pessoaJuridica.getEnderecos().size(); p++) {

				Endereco enderecoTemp = enderecoRepository.findById(pessoaJuridica.getEnderecos().get(p).getId()).get();

				if (!enderecoTemp.getCep().equals(pessoaJuridica.getEnderecos().get(p).getCep())) {

					CepDTO cepDTO = pessoaUserService.consultaCep(pessoaJuridica.getEnderecos().get(p).getCep());

					pessoaJuridica.getEnderecos().get(p).setBairro(cepDTO.getBairro());
					pessoaJuridica.getEnderecos().get(p).setCidade(cepDTO.getLocalidade());
					pessoaJuridica.getEnderecos().get(p).setComplemento(cepDTO.getComplemento());
					pessoaJuridica.getEnderecos().get(p).setRuaLogra(cepDTO.getLogradouro());
					pessoaJuridica.getEnderecos().get(p).setUf(cepDTO.getUf());
				}
			}
		}

		pessoaJuridica = pessoaUserService.salvarPessoaJuridica(pessoaJuridica);

		return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/listaPessoaJuridica")
	public ResponseEntity<List<PessoaJuridica>> listaPessoaJuridica() {

		List<PessoaJuridica> pessJur = (List<PessoaJuridica>) pessoaJuridicaRepository.findAll();

		return new ResponseEntity<List<PessoaJuridica>>(pessJur, HttpStatus.OK);

	}
	
	@ResponseBody
	@DeleteMapping(value = "**/deletePessoaJuridicaPorId/{id}")
	public ResponseEntity<?> deletePjPorId(@PathVariable("id") Long id) {

		pessoaJuridicaRepository.deleteById(id);

		return new ResponseEntity("Pj deletado por Id com sucesso!", HttpStatus.OK);
	}

}
