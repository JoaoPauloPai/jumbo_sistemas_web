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
 * 30 de jan. de 2022
 * 14:04:59
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
	
	
	public List<ObjetoRelatorioStatusCompra> relatorioStatusVendaLojaSite(ObjetoRelatorioStatusCompra objetoRelatorioStatusCompra){
		
		List<ObjetoRelatorioStatusCompra> retorno = new ArrayList<ObjetoRelatorioStatusCompra>();
		
		//cfc = vsl
		String sql = "select p.id as codigoProduto, "
				+ " p.nome as nomeProduto, "
				+ " pf.email as emailCliente, "
				+ " pf.telefone as foneCliente, "
				+ " p.valor_venda as valorVendaProduto, "
				+ " pf.id as codigoCliente, "
				+ " pf.nome as nomeCliente,"
				+ " p.qtd_estoque as qtdEstoque, "
				+ " vsl.id as codigoVenda, "
				+ " vsl.status_venda_loja_site as statusVenda "
				+ " from  venda_site_loja as vsl "
				+ " inner join item_venda_site as ntp on  ntp.venda_site_loja_id = vsl.id "
				+ " inner join produto as p on p.id = ntp.produto_id "
				+ " inner join pessoa_fisica as pf on pf.id = vsl.pessoa_id ";
		
		
				sql+= " where vsl.data_venda >= '"+objetoRelatorioStatusCompra.getDataInicial()+"' and vsl.data_venda  <= '"+objetoRelatorioStatusCompra.getDataFinal()+"' ";
				
				if(!objetoRelatorioStatusCompra.getNomeProduto().isEmpty()) {		
				  sql += " and upper(p.nome) like upper('%"+objetoRelatorioStatusCompra.getNomeProduto()+"%') ";
				}
				
				if (!objetoRelatorioStatusCompra.getStatusVenda().isEmpty()) {
				 sql+= " and vsl.status_venda_loja_virtual in ('"+objetoRelatorioStatusCompra.getStatusVenda()+"') ";
				}
				
				if (!objetoRelatorioStatusCompra.getNomeCliente().isEmpty()) {
				 sql += " and pf.nome like '%"+objetoRelatorioStatusCompra.getNomeCliente()+"%' ";
				}
		
		
		retorno = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ObjetoRelatorioStatusCompra.class));
				
		return retorno;
		
	}
	

}
