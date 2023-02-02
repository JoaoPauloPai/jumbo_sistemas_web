/**
 * 
 */
package br.com.jumbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.FormaPagamento;


/**
 * @author João Paulo
 *
 *         14 de jan. de 2022 19:31:48
 */
@Repository
@Transactional
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

  
	@Query("select a from FormaPagamento a where upper(trim(a.descricao))like %?1 and a.empresa.id = ?2")
	List<FormaPagamento> buscarFormaPagPorNome(String descricao, Long idEmpresa);
	
	@Query(value = "select f from FormaPagamento f where f.empresa.id = ?1")
	List<FormaPagamento> findAll(Long idEmpresa);

}
