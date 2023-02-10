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
 * 9 de fev. de 2023
 * 19:59:48
 */
@Service
public class AcessoContagemApiService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void atualizaAcessoEndPointSalvarAvalicaoProduto() {
		
		jdbcTemplate.execute("begin; update acesso_contagem_api set qtde_acesso_end_point = qtde_acesso_end_point + 1"
				+ " where nome_end_point = 'SALVA-AVALIACAO-PRODUTO'; commit;");
	   
	}
	
	public void atualizaAcessoEndPointSalvarCompraSite() {
		
		jdbcTemplate.execute("begin; update acesso_contagem_api set qtde_acesso_end_point = qtde_acesso_end_point + 1"
				+ " where nome_end_point = 'SALVA-COMPRA-SITE'; commit;");
	   
	}

}
