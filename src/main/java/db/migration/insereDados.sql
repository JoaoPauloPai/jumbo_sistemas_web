


/*Insere dados Pessoa Juridica*/
INSERT INTO public.pessoa_juridica(
            id, email, nome, telefone, tipo_pessoa, empresa_id, categoria, 
            cnpj, insc_estadual, insc_municipal, nome_fantasia, razao_social)
    VALUES (1, 'emailempresatetse@teste.com', 'NomeEmpresa Teste', '56 4333498', 'JURIDICA','1' ,'Categoria Teste', 
            '08905412000182', '1348058989283', '4682302', 'Nome Fantasia Teste', 'RazaoSocial Teste');
            
            /*Insere dados Pessoa Física*/
            INSERT INTO public.pessoa_fisica(
            id, email, nome, telefone, tipo_pessoa, empresa_id, cpf, data_nascimento)
    VALUES (1, 'motokleber2009@hotmail.com', 'Jose gomes Da Silva Teste', '65 999207427', 'FISICA', '1', '34722089027', '1980-12-12');
    
    /*Insere Acesso*/
    INSERT INTO public.acesso(
            id, descricao)
    VALUES (500, 'ROLE_USER'),
            (501,'ROLE_ADMIN');
            
            
             /*Insere Usuário = admin senha = admin*/
            INSERT INTO public.usuario(
            id, data_atual_senha, login, senha, empresa_id, pessoa_id)
    VALUES (1, '2021-12-20', 'admin','$2a$10$/3oD53zwfw1iGAgqFMmaJ.GHmkR/cL5TTOvf6Qi9sNdt5K7MJq4Ka' , '1', '1');
