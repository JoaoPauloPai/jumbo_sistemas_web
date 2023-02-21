/**
 * 
 */
package br.com.jumbo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.NotaFiscalVenda;
import br.com.jumbo.model.dto.ObjetoRelatorioStatusCompra;
import br.com.jumbo.repository.NotaFiscalVendaSiteRepository;

/**
 * @author João Paulo
 *
 *         30 de jan. de 2022 14:04:59
 */
@Service
public class NotaFiscalVendaSiteService {

	@Autowired
	NotaFiscalVendaSiteRepository notaFiscalVendaRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public NotaFiscalVenda save(NotaFiscalVenda notaFiscalVenda) {

		return notaFiscalVendaRepository.save(notaFiscalVenda);
	}

	public List<ObjetoRelatorioStatusCompra> relatorioStatusVendaLojaSite(
			ObjetoRelatorioStatusCompra objetoRelatorioStatusCompra) {

		List<ObjetoRelatorioStatusCompra> retorno = new ArrayList<ObjetoRelatorioStatusCompra>();

		
		String sql = "select p.id as codigoProduto, " 
		        + " p.nome as nomeProduto, " 
				+ " p.id as codigoProduto, "
				+ " p.valor_venda as valorVendaProduto, " 
				+ " p.marca_produto_id as marcaProduto, "
				+ " pf.email as emailCliente, " 
				+ " pf.telefone as foneCliente, "
				+ " pf.id as codigoCliente, " 
				+ " pf.nome as nomeCliente," 
				+ " p.qtd_estoque as qtdEstoque, " 
				+ " vsl.id as codigoVenda, "
				+ " vsl.status_venda_loja_site as statusVenda " 
				+ " from  venda_site_loja as vsl "
				+ " inner join item_venda_site as ivs on  ivs.venda_site_loja_id = vsl.id "
				+ " inner join produto as p on p.id = ivs.produto_id "
				+ " inner join pessoa_fisica as pf on pf.id = vsl.pessoa_id ";
			

		sql += " where vsl.data_venda >= '" + objetoRelatorioStatusCompra.getDataInicial()
				+ "' and vsl.data_venda  <= '" + objetoRelatorioStatusCompra.getDataFinal() + "' ";

		if (!objetoRelatorioStatusCompra.getNomeProduto().isEmpty()) {
			sql += " and upper(p.nome) like upper('%" + objetoRelatorioStatusCompra.getNomeProduto() + "%') ";
		}
		
		if (!objetoRelatorioStatusCompra.getCodigoProduto().isEmpty()) {
			sql += " and p.id = " + objetoRelatorioStatusCompra.getCodigoProduto() + " ";
		}
		
		if (!objetoRelatorioStatusCompra.getValorVendaProduto().isEmpty()) {
			sql += " and p.valor_venda = " + objetoRelatorioStatusCompra.getValorVendaProduto() + " ";
		}

		if (!objetoRelatorioStatusCompra.getMarcaProduto().isEmpty()) {
			sql += " and p.marca_produto_id = " + objetoRelatorioStatusCompra.getMarcaProduto() + " ";
		}

		if (!objetoRelatorioStatusCompra.getStatusVenda().isEmpty()) {
			sql += " and upper (vsl.status_venda_loja_site) like upper ('%"
					+ objetoRelatorioStatusCompra.getStatusVenda() + "%') ";
		}

		if (!objetoRelatorioStatusCompra.getEmailCliente().isEmpty()) {
			sql += " and pf.email like ('%" + objetoRelatorioStatusCompra.getEmailCliente() + "%') ";
		}
		
		if (!objetoRelatorioStatusCompra.getNomeCliente().isEmpty()) {
			sql += " and pf.nome like ('%" + objetoRelatorioStatusCompra.getNomeCliente() + "%') ";
		}
		
		if (!objetoRelatorioStatusCompra.getCodigoCliente().isEmpty()) {
			sql += " and pf.id = " + objetoRelatorioStatusCompra.getCodigoCliente() + " ";
		}

		retorno = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ObjetoRelatorioStatusCompra.class));

		return retorno;

	}

}
