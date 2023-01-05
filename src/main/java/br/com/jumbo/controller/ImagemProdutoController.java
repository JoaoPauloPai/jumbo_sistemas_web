/**
 * 
 */
package br.com.jumbo.controller;

import java.util.ArrayList;
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

import br.com.jumbo.model.ImagemProduto;
import br.com.jumbo.model.dto.ImagemProdutoDTO;
import br.com.jumbo.repository.ImagemProdutoRepository;
import br.com.jumbo.service.ImagemProdutoService;

/**
 * @author João Paulo
 *
 *         17 de jan. de 2022 19:25:00
 */
@Controller
@RestController
public class ImagemProdutoController {

	@Autowired
	private ImagemProdutoRepository imagemProdutoRepository;
	
	@ResponseBody
	@PostMapping(value = "**/salvarImagemProduto")
	public ResponseEntity<ImagemProdutoDTO> salvarImagemProduto(@RequestBody ImagemProduto imagemProduto) {

		imagemProduto = imagemProdutoRepository.saveAndFlush(imagemProduto);

		ImagemProdutoDTO imagemProdutoDTO = new ImagemProdutoDTO();
		
	
		imagemProdutoDTO.setId(imagemProduto.getId());
		imagemProdutoDTO.setEmpresa(imagemProduto.getEmpresa().getId());
		imagemProdutoDTO.setProduto(imagemProduto.getProduto().getId());
		imagemProdutoDTO.setImagemMiniatura(imagemProduto.getImagemMiniatura());
		imagemProdutoDTO.setImagemOriginal(imagemProduto.getImagemOriginal());

		return new ResponseEntity<ImagemProdutoDTO>(imagemProdutoDTO, HttpStatus.OK);

	}
	
	@ResponseBody
	@DeleteMapping(value = "**/deleteTodasImagemProduto/{idProduto}")
	public ResponseEntity<?> deleteTodasImagemProduto(@PathVariable("idProduto") Long idProduto) {
		
		imagemProdutoRepository.deleteImagem(idProduto);
		
		return new ResponseEntity<String>("Imagems do produto removida", HttpStatus.OK);
	}
	
	
	@ResponseBody
	@DeleteMapping(value = "**/deleteImagemObjeto")
	public ResponseEntity<?> deleteImagemProdutoPorId(@RequestBody ImagemProduto imagemProduto) {
		imagemProduto.setId(10);
		if(!imagemProdutoRepository.existsById(imagemProduto.getId())) {
			return new ResponseEntity<String>("Imagem já foi removida ou não existe com esse id: " + imagemProduto.getId(), HttpStatus.OK);
		}
		
		imagemProdutoRepository.deleteById(imagemProduto.getId());
		
		return new ResponseEntity<String>("Imagem removida", HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping(value = "**/deleteImagemProdutoPorId/{id}")
	public ResponseEntity<?> deleteImagemProdutoPorId(@PathVariable("id") Long id) {
		
		if(!imagemProdutoRepository.existsById(id)) {
			return new ResponseEntity<String>("Imagem já foi removida ou não existe com esse id: " + id, HttpStatus.OK);
		}
		
		imagemProdutoRepository.deleteById(id);
		
		return new ResponseEntity<String>("Imagem removida", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/obterImagemPorProduto/{idProduto}")
	public ResponseEntity<List<ImagemProdutoDTO>> obterImagemPorProduto(@PathVariable("idProduto") Long idProduto) {

		List<ImagemProdutoDTO> dtos = new ArrayList<ImagemProdutoDTO>();

		List<ImagemProduto> imagemProdutos = imagemProdutoRepository.buscaImagemProduto(idProduto);

		for (ImagemProduto imagemProduto : imagemProdutos) {

			ImagemProdutoDTO imagemProdutoDTO = new ImagemProdutoDTO();
			
			/**
			imagemProdutoDTO.setId(imagemProduto.getId());
			imagemProdutoDTO.setEmpresa(imagemProduto.getEmpresa().getId());
			imagemProdutoDTO.setProduto(imagemProduto.getProduto().getId());
			imagemProdutoDTO.setImagemMiniatura(imagemProduto.getImagemMiniatura());
			imagemProdutoDTO.setImagemOriginal(imagemProduto.getImagemOriginal());  **/

			dtos.add(imagemProdutoDTO);
		}

		return new ResponseEntity<List<ImagemProdutoDTO>>(dtos, HttpStatus.OK);

	}



	@ResponseBody
	@GetMapping(value = "**/listaImagemProduto")
	public ResponseEntity<List<ImagemProduto>> ListaImagemProduto() {

		List<ImagemProduto> imgprodut = imagemProdutoRepository.findAll();

		return new ResponseEntity<List<ImagemProduto>>(imgprodut, HttpStatus.OK);

	}

}
