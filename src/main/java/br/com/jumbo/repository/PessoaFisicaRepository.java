/**
 * 
 */
package br.com.jumbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.PessoaFisica;

/**
 * @author João Paulo
 *
 *         12 de jan. de 2022 15:18:58
 */
@Repository
@Transactional
public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long> {
	
	@Query(value = "select pf from PessoaFisica pf where upper(trim(pf.nome)) like %?1%")
	public List<PessoaFisica> pesquisaPorNomePF(String nome);

	@Query(value = "select pf from PessoaFisica pf where upper(trim(pf.cpf)) like %?1%")
	public List<PessoaFisica> pesquisaPessoaFisicaPorCpf(String cpf);
	
	@Query(value = "select pf from PessoaFisica pf where pf.cpf = ?1")
	public PessoaFisica existeCpfCadastrado(String cpf);

	@Query(value = "select pf from PessoaFisica pf where pf.cpf = ?1")
	public List<PessoaFisica> existeCpfCadastradoList(String cpf);

	@Query(value = "select pf from PessoaFisica pf where pf.id = ?1")
	public PessoaFisica existeIdCadastrado(Long id);

}
