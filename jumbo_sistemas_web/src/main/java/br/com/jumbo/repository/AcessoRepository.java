/**
 * 
 */
package br.com.jumbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.Acesso;

/**
 * @author João Paulo
 *
 * 11 de jan. de 2022
 * 18:22:33
 */
@Repository
@Transactional
public interface AcessoRepository extends JpaRepository<Acesso, Long>{

}
