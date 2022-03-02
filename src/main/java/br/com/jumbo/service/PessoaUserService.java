/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.jumbo.repository.UsuarioRepository;

/**
 * @author João Paulo
 *
 * 1 de mar. de 2022
 * 20:53:19
 */
public class PessoaUserService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

}
