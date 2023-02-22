--select * from nota_fiscal_compra where id = 21;

--select * from nota_item_produto where nota_fiscal_compra_id = 21;


select p.id as codigoProduto, p.nome as nomeProduto,
p.valor_venda as valorVendaProduto, ntp.quantidade as quantidadeComprada,
pj.id as codigoFornecedor, pj.nome as nomeFornecedor,cfc.data_compra as dataCompra  
from nota_fiscal_compra as cfc
inner join nota_item_produto as ntp on  cfc.id = nota_fiscal_compra_id
inner join produto as p on p.id = ntp.produto_id 
inner join pessoa_juridica as pj on pj.id = cfc.pessoa_id
where upper(p.nome) like upper('%iphon%') 
and cfc.data_compra >= '2022-01-01' and cfc.data_compra <= '2022-02-01'

--where upper(pj.nome) like upper('%alex FERNANDO%');
--where p.id = 15;
--where cfc.data_compra >= '2022-01-01' and cfc.data_compra <= '2022-02-01'
--where cfc.id = 21;

-- por produto
-- por intervalor de data
-- por fornecedor

Left join é uma operação em consultas SQL para retornar todos os dados da coluna esquerda, 
independente de haver ou não dados na coluna da direita.