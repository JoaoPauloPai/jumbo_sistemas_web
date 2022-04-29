/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author João Paulo
 *
 * 28 de abr. de 2022
 * 17:52:49
 */
@Service
public class ServiceContagemAcessoApi {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void atualizaAcessoEndPointPf() {
	
		jdbcTemplate.execute("begin; update acesso_end_point set qntd_acesso = qntd_acesso + 1 where nome_end_point = 'END-POINT-NOME-PESSOA-FISICA'; commit;");
	   
	}

}
