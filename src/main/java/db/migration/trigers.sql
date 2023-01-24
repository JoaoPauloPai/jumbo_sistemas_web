      /*Funçao valida Chave Pessoa Fisica em todas as tabelas*/
	  create or replace function validaChavePessoa()
      returns trigger
      language plpgsql
      as $$
      declare existe integer;
      
      begin
      existe = (select count (1) from pessoa_fisica where id = NEW.pessoa_id);
      if(existe <= 0) then
      existe = (select count(1) from pessoa_juridica where id = NEW.pessoa_id);
      if(existe <= 0 ) then
      raise exception 'Não foi encontrado o ID ou PK da pessoa para realiar a associação';
       end if;
      end if;
      RETURN NEW;
       
      end;
      $$
	  
	   /*Funçao valida Chave Pessoa Fisica fornecedor  em todas as tabelas*/
	  create or replace function validaChavePessoa2()
      returns trigger
      language plpgsql
      as $$
      declare existe integer;
      
      begin
      existe = (select count (1) from pessoa_fisica where id = NEW.pessoa_forn_id);
      if(existe <= 0) then
      existe = (select count(1) from pessoa_juridica where id = NEW.pessoa_forn_id);
      if(existe <= 0 ) then
      raise exception 'Não foi encontrado o ID ou PK da pessoa para realiar a associação';
       end if;
      end if;
      RETURN NEW;
       
      end;
      $$
	  

      /*NEW = carrega os dados de insert e update*/ 
      /*OLD = carrega os dados da linha antiga antes de atualizar*/ 

      /*Tabela AvaliacaoProduto*/
      create trigger validaChavePessoaAvaliacaoProdutoInsert
      before insert
      on avaliacao_produto
      for each row
      execute procedure validaChavePessoa();

      
      create trigger validaChavePessoaAvaliacaoProdutoUpdate
      before update
      on avaliacao_produto
      for each row
      execute procedure validaChavePessoa();
	  
      /*Tabela ContaPagar*/
      create trigger validaChavePessoaContaPagarInsert
      before insert
      on conta_pagar
      for each row
      execute procedure validaChavePessoa();

      
      create trigger validaChavePessoaContaPagarUpdate
      before update
      on conta_pagar
      for each row
      execute procedure validaChavePessoa();
	  
      create trigger validaChavePessoaContaPagarInsertPessoaFornecedor
      before insert
      on conta_pagar
      for each row
      execute procedure validaChavePessoa2();

      create trigger validaChavePessoaContaPagarUpdatePessoaFonecedor
      before update
      on conta_pagar
      for each row
      execute procedure validaChavePessoa2();
	  
	  /*Tabela ContaReceber*/
      create trigger validaChavePessoaContaReceberInsert
      before insert
      on conta_receber
      for each row
      execute procedure validaChavePessoa();

      create trigger validaChavePessoaContaReceberUpdate
      before update
      on conta_receber
      for each row
      execute procedure validaChavePessoa();
	  
	  /*Tabela Endereço*/
      create trigger validaChavePessoaContaReceberInsert
      before insert
      on endereco
      for each row
      execute procedure validaChavePessoa();

      create trigger validaChavePessoaContaReceberUpdate
      before update
      on endereco
      for each row
      execute procedure validaChavePessoa();
	  
	   /*Tabela Nota Foscal Compra*/
      create trigger validaChavePessoaContaReceberInsert
      before insert
      on nota_fiscal_compra
      for each row
      execute procedure validaChavePessoa();

      create trigger validaChavePessoaContaReceberUpdate
      before update
      on nota_fiscal_compra
      for each row
      execute procedure validaChavePessoa();
	  
	  /*Tabela Usuario*/
      create trigger validaChavePessoaContaReceberInsert
      before insert
      on usuario
      for each row
      execute procedure validaChavePessoa();

      create trigger validaChavePessoaContaReceberUpdate
      before update
      on usuario
      for each row
      execute procedure validaChavePessoa();
	  
	     /*Tabela VendaBalcaoLoja*/
      create trigger validaChavePessoaContaReceberInsert
      before insert
      on venda_balcao_loja
      for each row
      execute procedure validaChavePessoa();

      create trigger validaChavePessoaContaReceberUpdate
      before update
      on venda_balcao_loja
      for each row
      execute procedure validaChavePessoa();
	  
	    /*Tabela VendaSiteLoja*/
      create trigger validaChavePessoaContaReceberInsert
      before insert
      on venda_site_loja
      for each row
      execute procedure validaChavePessoa();

      create trigger validaChavePessoaContaReceberUpdate
      before update
      on venda_site_loja
      for each row
      execute procedure validaChavePessoa();
	  
	     /*Tabela Vendedor*/
      create trigger validaChavePessoaContaReceberInsert
      before insert
      on vendedor
      for each row
      execute procedure validaChavePessoa();

      create trigger validaChavePessoaContaReceberUpdate
      before update
      on vendedor
      for each row
      execute procedure validaChavePessoa();