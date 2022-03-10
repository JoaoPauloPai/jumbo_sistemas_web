/**
 * 
 */
package br.com.jumbo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.jumbo.model.PessoaJuridica;

/**
 * @author João Paulo
 *
 * 1 de mar. de 2022
 * 20:56:54
 */

@Repository
public interface PessoaRepository extends CrudRepository<PessoaJuridica, Long> {
	
	@Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
	public PessoaJuridica existeCnpjCadastrado(String cnpj);
		
	

}
