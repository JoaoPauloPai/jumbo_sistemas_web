/**
 * 
 */
package br.com.jumbo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.VendaSiteLoja;
import br.com.jumbo.repository.VendaSiteLojaRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:47:18
 */
@Service
public class VendaSitelojaServise {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private VendaSiteLojaRepository vendaSiteLojaRepository;
	
	
	public void exclusaoTotalVendaBanco2(Long idVenda) {
		String sql = "begin; update venda_site_loja set excluido = true where id = " + idVenda +"; commit;";
		jdbcTemplate.execute(sql);;
	}

	public void exclusaoTotalVendaBanco(Long idVenda) {
		
		String value = 
		                  " begin;"
		      			+ " UPDATE nota_fiscal_venda set venda_compra_loja_virt_id = null where venda_compra_loja_virt_id = "+idVenda+"; "
		      			+ " delete from nota_fiscal_venda where venda_compra_loja_virt_id = "+idVenda+"; "
		      			+ "  delete from item_venda_loja where venda_compra_loja_virtu_id ="+idVenda+"; "
		      			+ " delete from status_rastreio where venda_compra_loja_virt_id = "+idVenda+"; "
		      			+ " delete from venda_site_loja where id = "+idVenda+"; "
		      			+ " commit; ";
		
		jdbcTemplate.execute(value);
	}

	public void ativaRegistroVendaBanco(Long idVenda) {
		String sql = "begin; update venda_site_loja set excluido = false where id = " + idVenda +"; commit;";
		jdbcTemplate.execute(sql);;
		
	}

	public List<VendaSiteLoja> consultaVendaFaixaData(String data1, String data2) throws ParseException{
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date1 = dateFormat.parse(data1);
		Date date2 = dateFormat.parse(data2);
	
		return vendaSiteLojaRepository.consultaVendaFaixaData(date1, date2);
		
	}

}
