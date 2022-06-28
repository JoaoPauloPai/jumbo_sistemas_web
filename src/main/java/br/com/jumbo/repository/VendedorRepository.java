/**
 * 
 */
package br.com.jumbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.jumbo.model.Acesso;
import br.com.jumbo.model.Vendedor;

/**
 * @author João Paulo
 *
 * 27 de jun. de 2022
 * 17:18:22
 */
@ResponseBody
@Transactional
public interface VendedorRepository extends JpaRepository<Vendedor, Long>{

	@Query("select a from Vendedor a where (a.pessoa) like %?1")
	List<Vendedor> buscaVendedoPessoaId(Long id);
//	@Query("select a from Acesso a where upper(trim(a.descricao)) like %?1%")
	//List<Acesso> buscaAcessoDesc(String desc);

}