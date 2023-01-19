/**
 * 
 */
package br.com.jumbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jumbo.model.VendaBalcaoLoja;

/**
 * @author João Paulo
 *
 * 28 de jun. de 2022
 * 18:28:14
 */
public interface VendaBalcaoLojaRepository extends JpaRepository<VendaBalcaoLoja, Long>{


 //   @Query(value="select a from VendaCompraLojaVirtual a where a.id = ?1 and a.excluido = false")
	@Query(value = "select a from VendaBalcaoLoja a where a.id = ?1")
	VendaBalcaoLoja findByIdExclusao(Long idVenda);
	

}
