/**
 * 
 */
package br.com.jumbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.model.Vendedor;

/**
 * @author João Paulo
 *
 *         27 de jun. de 2022 17:18:22
 */
@ResponseBody
@Transactional
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

	@Query(nativeQuery = true, value = "Select count(1) > 0 from vendedor v where upper(v.pessoa_id) like %?1%")
	boolean existeVendedorComMesmaPessoa(Long pessoaId);

	@Query("select a from Vendedor a where a.pessoa.id = ?1 and a.empresa.id = ?2")
	List<Vendedor> buscarPorPessoa(Long id, Long id2);

	@Query("select a from Vendedor a where a.pessoa.id = ?1")
	List<Vendedor> buscarVendedorPorId(Long id);

}