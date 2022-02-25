/**
 * 
 */
package br.com.jumbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumbo.model.Acesso;

/**
 * @author João Paulo
 *
 *         11 de jan. de 2022 18:22:33
 */
@Repository
@Transactional
public interface AcessoRepository extends JpaRepository<Acesso, Long> {

	@Query("select a from Acesso a where upper(trim(a.descricao)) like %?1%")

	List<Acesso>buscaAcessoDesc(String desc);
}


