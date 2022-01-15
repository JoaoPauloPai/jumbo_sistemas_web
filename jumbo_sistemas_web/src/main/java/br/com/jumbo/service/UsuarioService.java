/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.Usuario;
import br.com.jumbo.repository.UsuarioRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:44:47
 */
@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario save(Usuario usuario) {
		
		
		return usuarioRepository.save(usuario);
	}

}
