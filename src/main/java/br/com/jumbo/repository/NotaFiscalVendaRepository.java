/**
 * 
 */
package br.com.jumbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.NotaFiscalVenda;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 19:41:06
 */
@Repository
@Transactional
public interface NotaFiscalVendaRepository extends JpaRepository<NotaFiscalVenda, Long> {
	
	@Query(value = "select n from NotaFiscalVenda n where n.vendaSiteLoja.id = ?1")
	List<NotaFiscalVenda> buscaNotaPorVenda(Long idVenda);
	

}
