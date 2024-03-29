/**
 * 
 */
package br.com.jumbo.controller;

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
import br.com.jumbo.model.ContaPagar;
import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.repository.ContaPagarRepository;

/**
 * @author João Paulo
 *
 *         15 de jan. de 2022 10:25:34
 */
@Controller
@RestController
public class ContaPagarController {

	@Autowired
	ContaPagarRepository contaPagarRepository;
	
	@Autowired
	private PessoaFisicaController pessoaFisicaController;

	@PostMapping(value = "**/salvarContaPagar")
	public ResponseEntity<ContaPagar> salvarAcesso(@RequestBody @Valid ContaPagar contaPagar)
			throws ExceptionJumboSistemas {
		
		PessoaFisica pessoaFisica = pessoaFisicaController.salvarPessoaFisica(contaPagar.getPessoa())
				.getBody();
		contaPagar.setPessoa(pessoaFisica);
		

		if (contaPagar.getEmpresa() == null || contaPagar.getEmpresa().getId() <= 0) {
			throw new ExceptionJumboSistemas("Empresa responsável deve ser informada");
		}

		if (contaPagar.getPessoa() == null || contaPagar.getPessoa().getId() <= 0) {
			throw new ExceptionJumboSistemas("Pessoa responsável deve ser informada");
		}

		if (contaPagar.getPessoa_fornecedor() == null || contaPagar.getPessoa_fornecedor().getId() <= 0) {
			throw new ExceptionJumboSistemas("Fornecedor responsável deve ser informada");
		}

		if (contaPagar.getId() == null) {
			List<ContaPagar> contaPagars = contaPagarRepository
					.buscaContaDesc(contaPagar.getDescricao().toUpperCase().trim());
			if (!contaPagars.isEmpty()) {
				throw new ExceptionJumboSistemas("Já existe conta a pagar com a mesma descrição.");
			}
		}

		ContaPagar conPagarSalva = contaPagarRepository.save(contaPagar);

		return new ResponseEntity<ContaPagar>(conPagarSalva, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/listaContaPagar")
	public ResponseEntity<List<ContaPagar>> listaContaPagar() {

		List<ContaPagar> contaPagar = contaPagarRepository.findAll();

		return new ResponseEntity<List<ContaPagar>>(contaPagar, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/buscaContaPargarDesc/{desc}")
	public ResponseEntity<List<ContaPagar>> buscaContaPagarDesc(@PathVariable("desc") String desc) {

		List<ContaPagar> contaPagarDesc = contaPagarRepository.buscaContaPagarDesc(desc.toUpperCase());

		return new ResponseEntity<List<ContaPagar>>(contaPagarDesc, HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteContaPagarPorId/{id}")
	public ResponseEntity<?> deleteContaPagarPorId(@PathVariable("id") Long id) {

		contaPagarRepository.deleteById(id);

		return new ResponseEntity("Conta-Pagar deletado por Id com sucesso!", HttpStatus.OK);
	}

	@ResponseBody /* Poder dar um retorno da API */
	@PostMapping(value = "**/deleteContaPagar")
	public ResponseEntity<?> deleteContaPagar(@RequestBody ContaPagar contaPagar) throws ExceptionJumboSistemas {

		if (contaPagarRepository.findById(contaPagar.getId()).isPresent() == false) {
			throw new ExceptionJumboSistemas("O Código: " + contaPagar.getId()
					+ ", da categoria do produto não foi encotrado no banco de dados");

		}

		contaPagarRepository.deleteById(contaPagar.getId());

		return new ResponseEntity("Conta Pagar Removida", HttpStatus.OK);
	}

}
