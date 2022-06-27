/**
 * 
 */
package br.com.jumbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
