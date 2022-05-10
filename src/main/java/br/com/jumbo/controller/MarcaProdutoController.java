/**
 * 
 */
package br.com.jumbo.controller;

import java.util.List;

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
import br.com.jumbo.model.Acesso;
import br.com.jumbo.model.CategoriaProduto;
import br.com.jumbo.model.MarcaProduto;
import br.com.jumbo.model.dto.CategoriaProdutoDto;
import br.com.jumbo.repository.MarcaProdutoRepository;
import br.com.jumbo.service.MarcaProdutoSevice;

/**
 * @author João Paulo
 *
 *         17 de jan. de 2022 20:17:21
 */
@Controller
@RestController
public class MarcaProdutoController {

	@Autowired
	private MarcaProdutoRepository marcaProdutoRepository;

	@Autowired
	private MarcaProdutoSevice marcaProdutoSevice;
	
	@ResponseBody
	@DeleteMapping(value = "**/deleteMarcaProdutoPorId/{id}")
	public ResponseEntity<?> deleteMarcaProdutoPorId(@PathVariable("id") Long id) {

		marcaProdutoRepository.deleteById(id);

		return new ResponseEntity("MarcaProduto deletado por Id com sucesso!", HttpStatus.OK);
	}


	@ResponseBody
	@GetMapping(value = "**/listaMarcaProduto")
	public ResponseEntity<List<MarcaProduto>> listaMarcaProduto() {

		List<MarcaProduto> marcprod = marcaProdutoRepository.findAll();

		return new ResponseEntity<List<MarcaProduto>>(marcprod, HttpStatus.OK);

	}

	@ResponseBody
	@PostMapping(value = "**/salvarMarcaProduto")
	public ResponseEntity<MarcaProduto> salvarMarcaProduto(@RequestBody MarcaProduto marcaProduto)
			throws ExceptionJumboSistemas {

		if (marcaProduto.getEmpresa() == null || (marcaProduto.getEmpresa().getId() == null)) {
			throw new ExceptionJumboSistemas("A empresa deve ser informada.");
		}

		if (marcaProduto.getId() <= 0 && marcaProdutoRepository.existeDescricao(marcaProduto.getNomeDesc())) {
			throw new ExceptionJumboSistemas("Não pode cadastar categoria com mesmo nome.");
		}

		MarcaProduto marcaSalva = marcaProdutoSevice.save(marcaProduto);

		return new ResponseEntity<MarcaProduto>(marcaSalva, HttpStatus.OK);
	}
}
