/**
 * 
 */
package br.com.jumbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.StatusRastreio;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 19:46:04
 */
@Repository
@Transactional
public interface StatusRatreioRepository extends JpaRepository<StatusRastreio, Long>{

}
