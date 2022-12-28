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
import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.model.Produto;
import br.com.jumbo.model.Vendedor;
import br.com.jumbo.repository.VendedorRepository;

/**
 * @author João Paulo
 *
 *         26 de jun. de 2022 18:57:49
 */
@Controller
@RestController
public class VendedorController {

	@Autowired
	private VendedorRepository vendedorRepository;
	


	@ResponseBody
	@PostMapping(value = "**/salvarVendedor")
	public ResponseEntity<Vendedor> salvarVendedor(@RequestBody @Valid Vendedor vendedor)
			throws ExceptionJumboSistemas {
		
		if (vendedor.getPessoa().getId() != null) {
			  List<Vendedor> vendedores  = vendedorRepository.buscarPorPessoa(vendedor.getPessoa().getId(), vendedor.getEmpresa().getId());
			  if (!vendedores.isEmpty()) {
				  throw new ExceptionJumboSistemas("Já existe Vendedor cadastrado com a pessoa  codigo : " + vendedor.getPessoa().getId());
			  }
			}
	

		Vendedor vendedorSalvo = vendedorRepository.save(vendedor);
		
		return new ResponseEntity<Vendedor>(vendedorSalvo, HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteVendedorPorId/{id}")
	public ResponseEntity<?> deleteVendedorPorId(@PathVariable("id") Long id) {

		vendedorRepository.deleteById(id);

		return new ResponseEntity("Vendedor deletado por Id com sucesso!", HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "**/listaVendedor")
	public ResponseEntity<List<Vendedor>> listaVendedor() {

		List<Vendedor> vendLista = vendedorRepository.findAll();

		return new ResponseEntity<List<Vendedor>>(vendLista, HttpStatus.OK);

	}
	
	@ResponseBody
	@GetMapping(value = "**/buscaVendedorPorId/{id}")
	public ResponseEntity<Vendedor> buscaVendedorPorId(@PathVariable("id") Long id) throws ExceptionJumboSistemas{ 
		
		Vendedor vendedor = vendedorRepository.findById(id).orElse(null);
		
		if (vendedor == null) {
			throw new ExceptionJumboSistemas("Não encontrou Vendedor com código: " + id);
		}
		
		return new ResponseEntity<Vendedor>(vendedor,HttpStatus.OK);
	}
	
}
