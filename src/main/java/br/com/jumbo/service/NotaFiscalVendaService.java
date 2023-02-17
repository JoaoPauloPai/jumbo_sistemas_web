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
import br.com.jumbo.repository.NotaFiscalVendaRepository;

/**
 * @author João Paulo
 *
 * 30 de jan. de 2022
 * 14:04:59
 */
@Service
public class NotaFiscalVendaService {
	
	@Autowired
	NotaFiscalVendaRepository notaFiscalVendaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public NotaFiscalVenda save(NotaFiscalVenda notaFiscalVenda) {
		
		return notaFiscalVendaRepository.save(notaFiscalVenda);
	}
	
	
	public List<ObjetoRelatorioStatusCompra> relatorioStatusVendaLojaVirtual(ObjetoRelatorioStatusCompra objetoRelatorioStatusCompra){
		
		List<ObjetoRelatorioStatusCompra> retorno = new ArrayList<ObjetoRelatorioStatusCompra>();
		
		String sql = "select p.id as codigoProduto, "
				+ " p.nome as nomeProduto, "
				+ " pf.email as emailCliente, "
				+ " pf.telefone as foneCliente, "
				+ " p.valor_venda as valorVendaProduto, "
				+ " pf.id as codigoCliente, "
				+ " pf.nome as nomeCliente,"
				+ " p.qtd_estoque as qtdEstoque, "
				+ " cfc.id as codigoVenda, "
				+ " cfc.status_venda_loja_virtual as statusVenda "
				+ " from  vd_cp_loja_virt as cfc "
				+ " inner join item_venda_loja as ntp on  ntp.venda_compra_loja_virtu_id = cfc.id "
				+ " inner join produto as p on p.id = ntp.produto_id "
				+ " inner join pessoa_fisica as pf on pf.id = cfc.pessoa_id ";
		
		
				sql+= " where cfc.data_venda >= '"+objetoRelatorioStatusCompra.getDataInicial()+"' and cfc.data_venda  <= '"+objetoRelatorioStatusCompra.getDataFinal()+"' ";
				
				if(!objetoRelatorioStatusCompra.getNomeProduto().isEmpty()) {		
				  sql += " and upper(p.nome) like upper('%"+objetoRelatorioStatusCompra.getNomeProduto()+"%') ";
				}
				
				if (!objetoRelatorioStatusCompra.getStatusVenda().isEmpty()) {
				 sql+= " and cfc.status_venda_loja_virtual in ('"+objetoRelatorioStatusCompra.getStatusVenda()+"') ";
				}
				
				if (!objetoRelatorioStatusCompra.getNomeCliente().isEmpty()) {
				 sql += " and pf.nome like '%"+objetoRelatorioStatusCompra.getNomeCliente()+"%' ";
				}
		
		
		retorno = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ObjetoRelatorioStatusCompra.class));
				
		return retorno;
		
	}
	

}
