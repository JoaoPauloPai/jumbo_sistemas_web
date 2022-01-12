package br.com.jumbo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jumbo.controller.AcessoController;
import br.com.jumbo.model.Acesso;
import br.com.jumbo.service.AcessoService;

@SpringBootTest(classes = JumboSistemasWebApplication.class)
public class JumboSistemasWebApplicationTests {

	@Autowired
	private AcessoService acessoService;

	private AcessoController acessoController;

	@Test
	public void testCadastraAcesso() {

		Acesso acesso = new Acesso();

		acesso.setDescricao("ROLE_ADMIN");

		acesso = acessoController.salvarAcesso(acesso);

	}

}
