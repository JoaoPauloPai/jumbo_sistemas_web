/**
 * 
 */
package br.com.jumbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.CupomDesc;
import br.com.jumbo.model.PessoaFisica;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 19:27:52
 */
@Repository
@Transactional
public interface CupomDescontoRepository extends JpaRepository<CupomDesc, Long>{




	//@Query(value = "select cd from CupomDesc cd where cd.id = ?1")
	//public  existeCupomCadastrado(Long id);
	



}
