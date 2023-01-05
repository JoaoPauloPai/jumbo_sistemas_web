/**
 * 
 */
package br.com.jumbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.ImagemProduto;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 19:33:57
 */
@Repository
@Transactional
public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long> {
	
	@Query("select a from ImagemProduto a where a.produto.id = ?1 ")
	List<ImagemProduto> buscaImagemProduto(Long idproduto);
	
	@Transactional
	@Modifying(flushAutomatically = true)
	@Query(nativeQuery = true, value = "delete from imagem_produto where produto_id = ?1")
	void deleteImagem(Long idProduto);


}
