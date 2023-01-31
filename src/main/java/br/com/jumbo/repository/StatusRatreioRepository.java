/**
 * 
 */
package br.com.jumbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jumbo.model.StatusRastreio;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 19:46:04
 */
@Repository
public interface StatusRatreioRepository extends JpaRepository<StatusRastreio, Long>{
	
	@Query(value = "select s from StatusRastreio s where s.vendaSiteLoja.id = ?1 order by s.id")
	public List<StatusRastreio> listaRastreioVenda(Long idVenda);
	
	//@Query(value = "select s from StatusRastreio s where s.vendaCompraLojaVirtual.id = ?1 order by s.id")
	//public List<StatusRastreio> listaRastreioVenda(Long idVenda);
}
