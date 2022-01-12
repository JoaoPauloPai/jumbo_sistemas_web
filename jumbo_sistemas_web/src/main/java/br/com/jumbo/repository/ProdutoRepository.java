/**
 * 
 */
package br.com.jumbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

}
