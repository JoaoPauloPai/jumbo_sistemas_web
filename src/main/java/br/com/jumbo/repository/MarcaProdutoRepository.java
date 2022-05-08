/**
 * 
 */
package br.com.jumbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.MarcaProduto;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 19:37:24
 */
@Repository
@Transactional
public interface MarcaProdutoRepository extends JpaRepository<MarcaProduto, Long> {


	@Query(nativeQuery = true, value = "select count(1) > 0 from marca_produto where upper(trim(nome_desc)) = upper(trim(?1))")
	public boolean existeDescricao(String nomeDesc);
	//boolean existeCategoria(String nomeDesc);

}
