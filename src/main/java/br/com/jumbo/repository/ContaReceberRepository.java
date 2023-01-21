/**
 * 
 */
package br.com.jumbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.ContaPagar;
import br.com.jumbo.model.ContaReceber;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 19:25:46
 */
@Repository
@Transactional
public interface ContaReceberRepository extends JpaRepository<ContaReceber, Long> {

	@Query("select a from ContaReceber a where upper(trim(a.descricao)) like %?1%")
	List<ContaReceber> buscaContaReceberTipo(String desc);
	
	//@Query("select a from ContaPagar a where upper(trim(a.descricao)) like %?1%")
	//List<ContaPagar> buscaContaDesc(String des);

}
