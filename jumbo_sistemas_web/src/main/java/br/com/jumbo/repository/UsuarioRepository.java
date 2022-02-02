/**
 * 
 */
package br.com.jumbo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.Usuario;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 19:49:28
 */
@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	@Query(value = "select u from Usuario u where u.login =?1")
	Usuario findeUserByLogin(String login);

}
