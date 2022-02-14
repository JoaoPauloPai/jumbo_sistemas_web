/**
 * 
 */
package br.com.jumbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import br.com.jumbo.repository.UsuarioRepository;

/**
 * @author João Paulo
 *
 * 24 de jan. de 2022
 * 14:42:58
 */
@Controller
@RestController
public class UsuarioController {
	
	private UsuarioRepository usuarioRepository;

}
