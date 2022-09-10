/**
 * 
 */
package br.com.jumbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.Acesso;
import br.com.jumbo.model.ContaPagar;

/**
 * @author João Paulo
 *
 *         14 de jan. de 2022 19:23:05
 */
@Repository
@Transactional
public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long> {

	@Query("select a from ContaPagar a where upper(trim(a.descricao)) like %?1%")
	List<ContaPagar> buscaContaDesc(String des);

	@Query("select a from ContaPagar a where upper(trim(a.descricao)) like %?1")
	List<ContaPagar> buscaContaPagarDesc(String desc);

	

}
