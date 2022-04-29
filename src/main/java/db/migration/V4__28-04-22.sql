CREATE TABLE public.acesso_end_point(
  /*id bigint NOT NULL*/
  nome_end_point character varying,
  qntd_acesso integer);
  
  
  /*INSERT INTO public.acesso_end_point(
             nome_end_point, qntd_acesso, id)
    VALUES ('END-POINT-NOME-PESSOA-FISICA', 0, 1);*/


alter table   acesso_end_point add constraint nome_end_point_unique UNIQUE (nome_end_point);