/**
 * 
 */
package br.com.jumbo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.PessoaFisica;

/**
 * @author João Paulo
 *
 *         12 de jan. de 2022 15:18:58
 */
@Repository
@Transactional
public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long> {

}
