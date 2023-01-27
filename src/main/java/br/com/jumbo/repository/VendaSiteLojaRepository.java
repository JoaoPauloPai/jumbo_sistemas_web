/**
 * 
 */
package br.com.jumbo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.VendaSiteLoja;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 19:53:24
 */
@Repository
@Transactional
public interface VendaSiteLojaRepository extends JpaRepository<VendaSiteLoja, Long>{


    @Query(value="select a from VendaSiteLoja a where a.id = ?1 and a.excluido = false")
	VendaSiteLoja findByIdExclusao(Long id);


	
	@Query(value="select distinct(i.vendaSiteLoja) from ItemVendaLoja i "
			+ " where i.vendaSiteLoja.excluido = false "
			+ " and i.vendaSiteLoja.dataVenda >= ?1 "
			+ " and i.vendaSiteLoja.dataVenda <= ?2 ")
	List<VendaSiteLoja> consultaVendaFaixaData(Date data1, Date data2);



	@Query(value="select distinct(i.vendaSiteLoja) from ItemVendaLoja i "
			+ " where i.vendaSiteLoja.excluido = false and i.vendaSiteLoja.pessoa.id = ?1")
	List<VendaSiteLoja> vendaPorCliente(Long idCliente);

	
	@Query(value="select distinct(i.vendaSiteLoja) from ItemVendaLoja i "
			+ " where i.vendaSiteLoja.excluido = false and i.vendaSiteLoja.produto.id = ?1")
	List<VendaSiteLoja> vendaPorProduto(long parseLong);




	//@Query(value="select i.vendaCompraLojaVirtual from ItemVendaLoja i where "
	//		+ " i.vendaCompraLojaVirtual.excluido = false and i.produto.id = ?1")

	
	



	






	


	





}
