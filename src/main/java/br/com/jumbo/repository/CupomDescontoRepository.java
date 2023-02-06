/**
 * 
 */
package br.com.jumbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.CupomDesc;
import br.com.jumbo.model.FormaPagamento;
import br.com.jumbo.model.PessoaFisica;

/**
 * @author João Paulo
 *
 *         14 de jan. de 2022 19:27:52
 */
@Repository
@Transactional
public interface CupomDescontoRepository extends JpaRepository<CupomDesc, Long> {

	@Query(value = "select c from CupomDesc c where c.empresa.id = ?1")
	public List<CupomDesc> cupomDescontoPorEmpresa(Long idEmpresa);

	@Query(value = "select cd from CupomDesc cd where cd.id = ?1")
	public List<CupomDesc> existeCupomCadastrado(Long id);
	
	@Query("select cd from CupomDesc cd where upper(trim(cd.codDesc))like %?1 and cd.empresa.id = ?2")
	List<CupomDesc> buscarCupomPorDesc(String descricao, Long idEmpresa);

}
