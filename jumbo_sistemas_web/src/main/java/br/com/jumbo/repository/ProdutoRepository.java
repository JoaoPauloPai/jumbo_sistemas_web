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
import br.com.jumbo.model.Produto;

/**
 * @author João Paulo
 *
 * 12 de jan. de 2022
 * 13:49:56
 */
@Repository
@Transactional
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	
	@Query("select a from Produto a where upper(trim(a.descricao)) like %?1%")

	List<Produto>buscaProdutoDesc(String desc);
}
