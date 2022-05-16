/**
 * 
 */
package br.com.jumbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.CategoriaProduto;

/**
 * @author João Paulo
 *
 *         13 de jan. de 2022 21:04:24
 */
@Repository
@Transactional
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long> {

	@Query(nativeQuery = true, value = "select count(1) > 0 from categoria_produto where upper(trim(nome_desc)) = upper(trim(?1))")
	public boolean existeCategoria(String nomeCategoria);
	

	@Query("select a from CategoriaProduto a where upper(trim(a.nomeDesc)) like %?1%")
	public List<CategoriaProduto> buscarCategoriaDes(String nomeDesc);

}