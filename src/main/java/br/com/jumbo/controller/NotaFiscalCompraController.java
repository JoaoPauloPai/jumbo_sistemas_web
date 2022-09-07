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
import br.com.jumbo.model.Acesso;
import br.com.jumbo.model.NotaFiscalCompra;
import br.com.jumbo.repository.NotaFiscalCompraRepository;

/**
 * @author João Paulo
 *
 *         30 de jan. de 2022 13:58:27
 */
@Controller
@RestController
public class NotaFiscalCompraController {

	@Autowired
	private NotaFiscalCompraRepository notaFiscalCompraRepository;

	@ResponseBody
	@PostMapping(value = "**/salvarNotaFiscalCompra")
	public ResponseEntity<NotaFiscalCompra> salvarMarca(@RequestBody @Valid NotaFiscalCompra notaFiscalCompra)
			throws ExceptionJumboSistemas { /* Recebe o JSON e converte pra Objeto */

		if (notaFiscalCompra.getId() == null) {

			if (notaFiscalCompra.getNumeroNota() != null) {
				boolean existe = notaFiscalCompraRepository
						.existeNotaComMesmoNumero(notaFiscalCompra.getNumeroNota().toUpperCase().trim());
				if (existe) {
					throw new ExceptionJumboSistemas(
							"Já existe nota de compra com esse número : " + notaFiscalCompra.getNumeroNota());
				}
			}

			if (notaFiscalCompra.getDescricaoObs() != null) {
				boolean existe = notaFiscalCompraRepository
						.existeNotaComDescricao(notaFiscalCompra.getDescricaoObs().toUpperCase().trim());

				if (existe) {
					throw new ExceptionJumboSistemas("Já existe Nota de compra com essa mesma descrição : "
							+ notaFiscalCompra.getDescricaoObs());
				}
			}

		}

		if (notaFiscalCompra.getPessoa() == null || notaFiscalCompra.getPessoa().getId() <= 0) {
			throw new ExceptionJumboSistemas("A Pessoa Juridica da nota fiscal deve ser informada.");
		}

		if (notaFiscalCompra.getEmpresa() == null || notaFiscalCompra.getEmpresa().getId() <= 0) {
			throw new ExceptionJumboSistemas("A empresa responsável deve ser infromada.");
		}

		if (notaFiscalCompra.getContaPagar() == null || notaFiscalCompra.getContaPagar().getId() <= 0) {
			throw new ExceptionJumboSistemas("A conta a pagar da nota deve ser informada.");
		}

		NotaFiscalCompra notaFiscalCompraSalva = notaFiscalCompraRepository.save(notaFiscalCompra);

		return new ResponseEntity<NotaFiscalCompra>(notaFiscalCompraSalva, HttpStatus.OK);
	}

	@ResponseBody
	@DeleteMapping(value = "**/deleteNotaFiscalCompraPorId/{id}")
	public ResponseEntity<?> deleteNotaFiscalCompraPorId(@PathVariable("id") Long id) {

		notaFiscalCompraRepository.deleteItemNotaFiscalCompra(id);/* Delete os filhos */
		notaFiscalCompraRepository.deleteById(id); /* Deleta o pai */

		return new ResponseEntity("Nota Fiscal Compra Removida", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/obterNotaFiscalCompra/{id}")
	public ResponseEntity<NotaFiscalCompra> obterNotaFiscalCompra(@PathVariable("id") Long id)
			throws ExceptionJumboSistemas {

		NotaFiscalCompra notaFiscalCompra = notaFiscalCompraRepository.findById(id).orElse(null);

		if (notaFiscalCompra == null) {
			throw new ExceptionJumboSistemas("Não encontrou Nota Fiscal com código: " + id);
		}

		return new ResponseEntity<NotaFiscalCompra>(notaFiscalCompra, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/buscarNotaFiscalPorDesc/{desc}")
	public ResponseEntity<List<NotaFiscalCompra>> buscarNotaFiscalPorDesc(@PathVariable("desc") String desc) {

		List<NotaFiscalCompra> notaFiscalCompras = notaFiscalCompraRepository.buscaNotaDesc(desc.toUpperCase().trim());

		return new ResponseEntity<List<NotaFiscalCompra>>(notaFiscalCompras, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(value = "**/listaNotaFiscalCompra")
	public ResponseEntity<List<NotaFiscalCompra>> listaNotaFiscalCompra() {

		List<NotaFiscalCompra> notaFiscaCompra = notaFiscalCompraRepository.findAll();

		return new ResponseEntity<List<NotaFiscalCompra>>(notaFiscaCompra, HttpStatus.OK);

	}

}
