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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
import br.com.jumbo.model.CategoriaProduto;
import br.com.jumbo.model.MarcaProduto;
import br.com.jumbo.model.dto.CategoriaProdutoDto;
import br.com.jumbo.repository.CategoriaProdutoRepository;
import br.com.jumbo.service.CategoriaProdutoService;

/**
 * @author João Paulo
 *
 *         13 de jan. de 2022 21:11:37
 */
@Controller
@RestController
public class CategoriaProdutoController {

	@Autowired
	private CategoriaProdutoRepository categoriaProdutoRepository;

	@Autowired
	private CategoriaProdutoService categoriaProdutoService;

	@ResponseBody
	@GetMapping(value = "**/listaCategoriaProduto")
	public ResponseEntity<List<CategoriaProduto>> listaCategoriaProduto() {

		List<CategoriaProduto> catprod = categoriaProdutoRepository.findAll();

		return new ResponseEntity<List<CategoriaProduto>>(catprod, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/buscaCatProdutoPorId/{id}")
	public ResponseEntity<CategoriaProduto> buscacatprodutoporid(@PathVariable("id") long id)
			throws ExceptionJumboSistemas {

		CategoriaProduto catprod = categoriaProdutoRepository.findById(id).orElse(null);

		if (catprod == null) {
			throw new ExceptionJumboSistemas("Não encontrou Categoria do Produto com código: " + id);
		}

		return new ResponseEntity<CategoriaProduto>(catprod, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping(value = "**/buscarCatgoriaPorDesc/{desc}")
	public ResponseEntity<List<CategoriaProduto>> buscarPorDesc(@PathVariable("desc") String desc) {

		List<CategoriaProduto> acesso = categoriaProdutoRepository.buscarCategoriaDes(desc.toUpperCase());

		return new ResponseEntity<List<CategoriaProduto>>(acesso, HttpStatus.OK);
	}

	@ResponseBody /* Poder dar um retorno da API */
	@PostMapping(value = "**/deleteCategoriaProduto")
	public ResponseEntity<?> deleteCategoriaProduto(@RequestBody CategoriaProduto categoriaProduto)
			throws ExceptionJumboSistemas {

		if (categoriaProdutoRepository.findById(categoriaProduto.getId()).isPresent() == false) {
			throw new ExceptionJumboSistemas("O Código: " + categoriaProduto.getId()
					+ ", da categoria do produto não foi encotrado no banco de dados");

		}

		categoriaProdutoRepository.deleteById(categoriaProduto.getId());

		return new ResponseEntity("Categoria Produto Removida", HttpStatus.OK);
	}

	/* Este End Point Esta Gerando erro com o produto DTO */
	@ResponseBody
	@PostMapping(value = "**/salvarCategoriaProduto2")
	public ResponseEntity<CategoriaProdutoDto> salvarCategoriaProduto2(@RequestBody CategoriaProduto categoriaProduto)
			throws ExceptionJumboSistemas {

		if (categoriaProduto.getEmpresa() == null || (categoriaProduto.getEmpresa().getId() == null)) {
			throw new ExceptionJumboSistemas("A empresa deve ser informada.");
		}

		if (categoriaProduto.getId() == null
				&& categoriaProdutoRepository.existeCategoria(categoriaProduto.getNomeDesc())) {
			throw new ExceptionJumboSistemas("Não pode cadastar categoria com mesmo nome.");
		}

		CategoriaProduto cateProduto = categoriaProdutoService.save(categoriaProduto);

		CategoriaProdutoDto categoriaProdutoDto = new CategoriaProdutoDto();
		categoriaProdutoDto.setId(cateProduto.getId());
		categoriaProdutoDto.setNomeDesc(cateProduto.getNomeDesc());
		categoriaProdutoDto.setEmpresa(cateProduto.getEmpresa().getId().toString());

		return new ResponseEntity<CategoriaProdutoDto>(categoriaProdutoDto, HttpStatus.OK);

	}

	@ResponseBody
	@PostMapping(value = "**/salvarCategoriaProduto")
	public ResponseEntity<CategoriaProduto> salvarCategoriaProduto(
			@RequestBody @Valid CategoriaProduto categoriaProduto)
			throws ExceptionJumboSistemas { /* Recebe o JSON e converte pra Objeto */

		if (categoriaProduto.getEmpresa() == null || (categoriaProduto.getEmpresa().getId() == null)) {
			throw new ExceptionJumboSistemas("A empresa deve ser informada.");
		}

		if (categoriaProduto.getId() == null
				&& categoriaProdutoRepository.existeCategoria(categoriaProduto.getNomeDesc())) {
			throw new ExceptionJumboSistemas("Não pode cadastar categoria com mesmo nome.");
		}

		CategoriaProduto categoriaProdutoSalvo = categoriaProdutoRepository.save(categoriaProduto);

		return new ResponseEntity<CategoriaProduto>(categoriaProdutoSalvo, HttpStatus.OK);
	}
}
