/**
 * 
 */
package br.com.jumbo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.ExceptionJumboSistemas;
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
	public ResponseEntity<Vendedor> salvarVendedor(@RequestBody @Valid Vendedor vendedor) throws ExceptionJumboSistemas {
     System.out.print("Terste");
		// if (acesso.getId() == null) {
		// List<Acesso> acessos =
		// acessoRepository.buscaAcessoDesc(acesso.getDescricao().toUpperCase());

		// if (!acessos.isEmpty()) {
		// throw new ExceptionJumboSistemas("Já existe Acesso com a descrição: " +
		// acesso.getDescricao());
		// }

		Vendedor vendedorSalvo = vendedorRepository.save(vendedor);

		return new ResponseEntity<Vendedor>(vendedorSalvo, HttpStatus.OK);
	}
}
