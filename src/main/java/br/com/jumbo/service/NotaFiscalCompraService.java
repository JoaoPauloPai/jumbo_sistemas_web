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

import br.com.jumbo.model.NotaFiscalCompra;
import br.com.jumbo.model.dto.ObejtoRequisicaoRelatorioProdutoAlertaEstoque;
import br.com.jumbo.repository.NotaFiscalCompraRepository;

/**
 * @author João Paulo
 *
 *         14 de jan. de 2022 20:33:57
 */
@Service
public class NotaFiscalCompraService {

	@Autowired
	NotaFiscalCompraRepository notaFiscalCompraRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public NotaFiscalCompra save(NotaFiscalCompra notaFiscalCompra) {

		return notaFiscalCompraRepository.save(notaFiscalCompra);
	}

	/**
	 * Este relatório retorna os produtos que estão com estoque menor ou igual a
	 * quantidade definida no campo de qtde_alerta_estoque.
	 * 
	 * @param alertaEstoque ObejtoRequisicaoRelatorioProdutoAlertaEstoque
	 * @return List<ObejtoRequisicaoRelatorioProdutoAlertaEstoque> Lista de Objetos
	 *         ObejtoRequisicaoRelatorioProdutoAlertaEstoque
	 */
	public List<ObejtoRequisicaoRelatorioProdutoAlertaEstoque> gerarRelatorioAlertaEstoque(
			ObejtoRequisicaoRelatorioProdutoAlertaEstoque alertaEstoque) {

		List<ObejtoRequisicaoRelatorioProdutoAlertaEstoque> retorno = new ArrayList<ObejtoRequisicaoRelatorioProdutoAlertaEstoque>();

		String sql = "select p.id as codigoProduto, " + " p.nome as nomeProduto, "
				+ " p.valor_venda as valorVendaProduto, " + " nip.quantidade as quantidadeComprada, "
				+ " pj.id as codigoFornecedor, " + " pj.nome as nomeFornecedor, " + " nfc.data_compra as dataCompra, "
				+ " p.qtd_estoque as qtdEstoque, " + " p.qtde_alerta_estoque as qtdAlertaEstoque "
				+ " from nota_fiscal_compra as nfc "
				+ " inner join nota_item_produto as nip on  nfc.id = nota_fiscal_compra_id "
				+ " inner join produto as p on p.id = nip.produto_id "
				+ " inner join pessoa_juridica as pj on pj.id = nfc.pessoa_id where ";

		sql += " nfc.data_compra >='" + alertaEstoque.getDataInicial() + "' and ";
		sql += " nfc.data_compra <= '" + alertaEstoque.getDataFinal() + "' ";
		sql += " and p.alerta_qtde_estoque = true and p.qtd_estoque <= p.qtde_alerta_estoque ";

		if (!alertaEstoque.getCodigoNota().isEmpty()) {
			sql += " and nfc.id = " + alertaEstoque.getCodigoNota() + " ";
		}

		if (!alertaEstoque.getCodigoProduto().isEmpty()) {
			sql += " and p.id = " + alertaEstoque.getCodigoProduto() + " ";
		}

		if (!alertaEstoque.getNomeProduto().isEmpty()) {
			sql += " upper(p.nome) like upper('%" + alertaEstoque.getNomeProduto() + "')";
		}

		if (!alertaEstoque.getNomeFornecedor().isEmpty()) {
			sql += " upper(pj.nome) like upper('%" + alertaEstoque.getNomeFornecedor() + "')";
		}

		retorno = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper(ObejtoRequisicaoRelatorioProdutoAlertaEstoque.class));

		return retorno;
	}

}
