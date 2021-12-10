# jumbo_sistemas_web
Loja Virtual
 Planejamento

 

 Requisitos Funcionais


•	Gerenciamento 
•	Cadastro de categorias de produtos
Cadastrar a descrição da categoria
               Ter definido no banco categorias padrões.
               Não permitir cadastrar categoria com mesma descrição

•	Cadastro de fornecedor/marca
            Deve-se cadastrar o fornecedor com seus dados fiscais, CNPJ, Inscrição estadual, dados de endereço, dados de contado.
	Não cadastrar fornecedor com mesmo CNPJ e mesmo nome.
             Os dados de endereço e dados fiscais devem ser buscado em API externas.

•	Cadastro de produto
            Nome do produto de forma correta.
	Deve ser informado o tipo da unidade (UN, Peça, Quilo, CX, Litro)
	Não deixar cadastrar produto com nome igual.
               Ao cadastrar o produto é obrigatório associar a uma categoria.
               Não permitir cadastrar o produto com menos de 10 letras.
               Cadastrar uma lista de imagens para o produto
	Cadastrar descrição completa que pode ser mais de 2000 mil caracteres.
               Ao cadastrar as fotos para o produto, deve ser feito o redimensionamento da imagem para ficar com tamanho de 600x800
               Validar a imagem deve ter no máximo 1MB para fazer o upload.
                O produto deve ser associado ao fornecedor na hora do cadastro.
               Também deve ser associado a uma marca
               Cadastrar a imagem em tamanho real e também em tamanho miniatura.
               Limite mínimo de 3 imagens e no máximo 6.
               Informar a quantidade em estoque.
               Informar os gerentes/usuário do sistema que o produto está com estoque baixo.
              Poder cadastrar uma quantidade de estoque baixo e/ou esgotado para dar alerta.
              Validar o estoque antes da venda para ver se possível vender. (Na Venda)
             Poder cadastrar um vídeo do Youtube (Link)
	                    


•	 Cadastro de cliente (Quem compra pelo sistema – Loja)
 Dados de endereço completo, podemos usar o para facilitar a pesquisa de CEP.
 O cliente sempre deverá ter um login e senha.
 Nome, cpf, telefone, e-mail

•	Contas a receber
Clientes que geraram boleto
Entra todas as vendas e valores de produtor vendidos
             

•	Contas a pagar
	Ex: Pagar os fornecedores de produtos.
	Entra com os dados da nota fiscal dos produtos.
	Associar o fornecedor.
               Campo para informar um valor total.
	Campo para informar descontos.
	Campo para informar a data de vencimento.
	Campo para informar a forma de pagamento (Cheque, pix, cartão, boleto.)
               


•	Realizar Vendas
	O cliente informar os dados básicos, nome, cpf, e-mail, telefone.
	(Cliente de cadastrar com Facebook ou Google
                Essa atutenticão é feita com Auth2,)
	O cliente informa o CEP e o endereço completo de entrega.
	Pode ter a opção de informar o cupom de desconto.
	Obrigatório informar o número da casa, ou prédio
	Seleciona a forma de pagamento (Cartão, Boleto, PIX)
	Deve ser feito o cálculo do frete de acordo com o cep do cliente.
	O valor do frete deve ser incluído na venda.
	Mostrar para o cliente o número de dias que o produto demora para ser entregue.
	Finaliza a venda.
                Dar baixo no estoque de todos os produtos comprados., após a autorização de cobrança do cartão de credito.
	Envia e-mail para o cliente dizendo que a compra foi realizada com sucesso, enviar número do pedido.
	Se a venda for por boleto então o estoque deve ser baixado quando o sistema receber o pagamento do boleto.
	O responsável pela loja virtual recebe o e-mail de venda realizada.

•	Item de venda
	Ligado com o produto
	Será salvo no banco a quantidade que foi vendida.

•	Gerenciamento/Controle de estoque
Atualizar o estoque do produto de forma manual pela tela do cadastro de produto.
Atualizar a quantidade de produto quando cadastrar uma nota fiscal de compra de produtos para a loja.

•	Cadastro de formas de pagamento
Cadastrar a forma em texto (Cheque, boleto, cartão, PIX, transferência.)

•	Envio de ofertas da loja por e-mail
(GetResponse e-mail marketing – Até 2000 e-mail é grátis.)

•	Castrado de cupons de descontos
No cadastro de cupom deve ser informado a descrição do cupom e o valor de desconto.

•	Histórico de compras
Relatorio das compras de produtos feitas com fornecedores da loja virtual.
Intervalo de datas.

•	Relatórios de produtos vendidos
Relatorio de produto vendidos pela loja virtual.
Ter a opção de intervalo de datas.
Poder informar uma descrição de produto.

•	Nota fiscal do cliente
Enviar a nota fiscal do cliente para o e-mail dele, após ter recebido o pagamento da venda.

•	Recuperação de senha
O cliente deve informar o e-mail e a senha deve ser enviada para ele no e-mail


•	Chat do cliente com o suporte.
Para ter um chat profissional vamos usar o JivoChat

•	Cadastro de marca
Deve ser informada a descrição da marca do produto

•	Relatórios de estoque baixo (Reposição)
Poder ter o relatório por intervalo de datas
Poder selecionar o produto para imprimir relatório

•	Devolução / Troca de produtos

•	Avaliação de produtos
O cliente deve estar logado
O cliente tem que ter comprado o produto realmente para poder avaliar o produto.
O cliente pode escrever a avaliação do produto em nossa página.
Depois que o cliente receber o produto em casa será enviado um e-mail de pedido de avaliação do produto.

•	Relatório de compra cancelada.
Esse relatório serve para a equipe da loja virtual entrar em contato com os clientes para finalizar a compra porque se foi cancelada é porque o cliente teve problema para finalizar a compra.

•	Relatório de carrinho/checkout abandonado
Esse relatório é para a equipe entrar em contato com o cliente para finalizar a compra

•	Integração com logística
Vamos usar o correio ou outra API de logística

•	Relatórios de produto em evidencia (Mais clicados, mais comprados, favoritos)
Gravar a quantidade de clique que os clientes darem em cada do produto.

•	Parte de SAC
Temos que mostrar os dados de contato no site para oferecer o antendimento ao Cliente
   



•	Fluxo de caixa
•	Abertura e fechamento de caixa
•	Mapear vendas de produto por região
•	Relatórios por marcas
•	Bônus para fidelidade
•	Pesquisar sobre: Melhor Envio

•	
•	Cadastro de usuário (Gerência o sistema - Loja)
•	Gerenciamento de acesso
•	Cadastro de categorias de produtos
•	Cadastro de produto
•	 Cadastro de cliente (Quem compra pelo sistema - Loja)
•	Cadastro de fornecedor
•	Contas a receber
•	Contas a pagar
•	Realizar Vendas
•	Item de venda
•	Gerenciamento de estoque
•	Fluxo de caixa
•	Abertura e fechamento de caixa
•	Integração com logística()
•	Controle de estoque
•	Histórico de compras
•	Vender com boleto, pix, cartão
•	Cadastro de formas de pagamento
•	Cadastro de forma de pagamento
•	Cliente de cadastrar com Facebook ou Google
•	Envio de ofertas da loja por e-mail
•	Castrado de cupons de descontos
•	Relatórios de produtos vendidos
•	Relatórios de produto em evidencia (Mais clicados, mais comprados, favoritos)
•	Relatórios de estoque baixo (Reposição)
•	Mapear vendas de produto por região
•	Nota fiscal do cliente
•	Lembrete carrinho abandonado
•	Relatório de compra cancelada.
•	Relatório de carrinho/checkout abandonado
•	Chat do cliente com o suporte.
•	Recuperação de senha
•	Avaliação de produtos
•	Devolução / Troca de produtos
•	Calculo de frete.
•	Cadastro de marca
•	Relatórios por marcas
•	Bônus para fidelidade


•	Pesquisar sobre: Melhor Envio

•	Diagramas

Datas
Em 21/10/2021 Começa o Projeto mentoria.
Em 09/11/2021 Cria o Projeto JumboSistemasWeb


Histórico de Revisão do documento

Data	Versão	Descrição	Autor
21/10/2021	1.0	Documento desenvolvido para edição, reformulação e s.	João Paulo G Silva
09/11/2021	1.0	Projeto criado no Eclipse	João Paulo G Silva

Definições, Acrônimos e Abreviações:
Abreviação	Acrônimos	Definições
COD.	CÓDIGO	Identificador para os requisitos funcionais ou não funcionais.
RF	REQUISITO FUNCIONAL	Identificador de um requisito funcional.
RNF	REQUISITO NÃO FUNCIONAL	Identificador de um requisito não funcional.


       Backlog das Funções do Sistema Atual
Requisitos Funcionais                                                                  Cadastros
RF 01 – O Sistema ao Cadastrar uma nova empresa, gerar um usuário padrão para a empresa e enviar por e-mail.
RF 02 – O Sistema deve cadastrar usuários para empresa com acesso Full/Admin
RF 03 – O Sistema deve cadastrar um usuário master para o Desenvolvedor gerenciar o software. (DEV)
RF 04 – O Sistema deve permitir ao departamento
RF 05 – O Sistema deve permitir o usuário trocar de telas sem precisar logar novamente.

