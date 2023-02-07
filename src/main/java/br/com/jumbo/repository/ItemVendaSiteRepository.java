/**
 * 
 */
package br.com.jumbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.ItemVendaSite;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 19:35:39
 */
@Repository
@Transactional
public interface ItemVendaSiteRepository extends JpaRepository<ItemVendaSite, Long>{

}
