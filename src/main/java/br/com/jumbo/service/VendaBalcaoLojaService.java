/**
 * 
 */
package br.com.jumbo.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.dto.ObejtoRequisicaoRelatorioVendaBalcaoLoja;
import br.com.jumbo.model.dto.ObjetoRelatorioStatusCompra;

/**
 * @author João Paulo
 *
 *         22 de fev. de 2023 21:35:51
 */
@Service
public class VendaBalcaoLojaService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**Este relatório permite saber as vendas efetuadas pelo balcão da loja
	 * @param obejtoRequisicaoRelatorioVendaBalcaoLoja
	 * @return relatorioVendaBalcaoLoja
	 */
	public List<ObejtoRequisicaoRelatorioVendaBalcaoLoja> relatorioVendaBalcaoLoja(
			ObejtoRequisicaoRelatorioVendaBalcaoLoja obejtoRequisicaoRelatorioVendaBalcaoLoja) {

		List<ObejtoRequisicaoRelatorioVendaBalcaoLoja> retorno = new ArrayList<ObejtoRequisicaoRelatorioVendaBalcaoLoja>();

		String sql = "select vbl.id as codigoVenda, "
				         + " vbl.data_venda as dataVenda, "
				         + " vbl.valor_total as valorVenda, "
				         + " ivb.quantidade as quantProduto, "
				         + " p.descricao as nomeProduto, "
			             + " pf.nome as nomeVendedor, "
			           	+ " pf1.nome as nomeCliente, "
				         + " fp.descricao as tipoPagamento "
			//	+ " from item_venda_balcao as ivb "
				+ " from venda_balcao_loja as vbl "
			//	+ " inner join venda_balcao_loja as vbl on ivb.venda_balcao_loja_id = vbl.id "
				+ " inner join item_venda_balcao as ivb on vbl.id = ivb.venda_balcao_loja_id "
				+ " inner join vendedor as v on vbl.vendedor_id = v.id "
				+ " inner join pessoa_fisica as pf on v.pessoa_id = pf.id "
				+ " inner join pessoa_fisica as pf1 on vbl.pessoa_id = pf1.id "
				+ " inner join forma_pagamento as fp on vbl.forma_pagamento_id = fp.id "
				+ " inner join produto as p on p.id = ivb.produto_id ";
				
		sql += " where vbl.data_venda >= '" + obejtoRequisicaoRelatorioVendaBalcaoLoja.getDataInicial() 
		  + "' and vbl.data_venda <= '" + obejtoRequisicaoRelatorioVendaBalcaoLoja.getDataFinal() +"' ";
		
			if (!obejtoRequisicaoRelatorioVendaBalcaoLoja.getNomeVendedor().isEmpty()) {
				sql += " and upper(pf.nome) like upper('%" + obejtoRequisicaoRelatorioVendaBalcaoLoja.getNomeVendedor() + "%') ";
			}
		  
		retorno = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ObjetoRelatorioStatusCompra.class));
		
		return retorno;
	}

}
