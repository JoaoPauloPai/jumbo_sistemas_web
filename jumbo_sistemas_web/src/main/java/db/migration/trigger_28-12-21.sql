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

      /*NEW = carrega os dados de insert e update*/ 
      /*OLD = carrega os dados da linha antiga antes de atualizar*/ 

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