/**
 * 
 */
package br.com.jumbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.PessoaJuridica;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 19:44:35
 */
@Repository
@Transactional
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long>{

	@Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
	public PessoaJuridica existeCnpjCadastrado(String cnpj);

	@Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
	public List<PessoaJuridica> existeCnpjCadastradoList(String cnpj);

	@Query(value = "select pj from PessoaJuridica pj where pj.inscEstadual = ?1")
	public PessoaJuridica existeInsEstadualCadastrado(String inscEstadual);

	@Query(value = "select pj from PessoaJuridica pj where pj.inscEstadual = ?1")
	public List<PessoaJuridica> existeInsEstadualCadastradoList(String inscEstadual);

	@Query(value = "select pj from PessoaJuridica pj where upper(trim(pj.nome)) like %?1%")
	public List<PessoaJuridica> pesquisaPjPorNome(String upperCase);
	
}
