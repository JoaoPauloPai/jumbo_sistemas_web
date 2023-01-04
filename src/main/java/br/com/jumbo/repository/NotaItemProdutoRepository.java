/**
 * 
 */
package br.com.jumbo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.NotaFiscalCompra;
import br.com.jumbo.model.NotaItemProduto;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 19:42:47
 */
@Repository
@Transactional
public interface NotaItemProdutoRepository extends JpaRepository<NotaItemProduto, Long>{

	@Query("select a from NotaItemProduto a where a.produto.id = ?1 and a.notaFiscalCompra.id = ?2")
	List<NotaItemProduto> buscaNotaItemPorProdutoNota(Long idPrduto, Long idNotaFiscal);
	
	@Query("select a from NotaItemProduto a where a.produto.id = ?1")
	List<NotaItemProduto> buscaNotaItemPorProduto(Long idProduto);
	
	
	@Query("select a from NotaItemProduto a where a.notaFiscalCompra.id = ?2")
	List<NotaItemProduto> buscaNotaItemPorNotaFiscal(Long idNotaFiscal);
	
	
	@Query("select a from NotaItemProduto a where a.empresa.id = ?2")
	List<NotaFiscalCompra> buscaNotaItemPorEmpresa(Long idEmpresa);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from nota_item_produto where id = ?1")
	void deleteByIdNotaItem(Long id);
	
	
}
