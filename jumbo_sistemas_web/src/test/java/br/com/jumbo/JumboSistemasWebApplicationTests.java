package br.com.jumbo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jumbo.controller.AcessoController;
import br.com.jumbo.model.Acesso;
import br.com.jumbo.repository.AcessoRepository;
import br.com.jumbo.service.AcessoService;
import junit.framework.TestCase;

@SpringBootTest(classes = JumboSistemasWebApplication.class)
public class JumboSistemasWebApplicationTests extends TestCase {

	@Autowired
	private AcessoService acessoService;

	@Autowired
	private AcessoController acessoController;
	
	@Autowired
	private AcessoRepository acessoRepository;

	@Test
	public void testCadastraAcesso() {

		Acesso acesso = new Acesso();

		acesso.setDescricao("ROLE_ADMIN");

		assertEquals(true, acesso.getId() == null);

		/*Grava no BD*/
		acesso = acessoController.salvarAcesso(acesso).getBody();

		assertEquals(true, acesso.getId() > 0);

		/*Valida dado salvos de forma correta*/
		assertEquals("ROLE_ADMIN", acesso.getDescricao());
		
		/*Teste de Carregamento*/
		Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();

		assertEquals(acesso.getId(), acesso2.getId());
		
		/*Teste de Delete*/
		
		acessoRepository.deleteById(acesso2.getId());
		
		acessoRepository.flush();
		
		Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null);
		
		assertEquals(true, acesso3 == null);
		
		
		/*Teste de query*/
		
		acesso = new Acesso();
		
		acesso.setDescricao("ROLE_ALUNO");
		
		acesso = acessoController.salvarAcesso(acesso).getBody();
		
	//	List<Acesso> acessos = acessoRepository.buscarAcessoDesc("ALUNO".trim().toUpperCase());
		
	//	assertEquals(1, acessos.size());
		
		acessoRepository.deleteById(acesso.getId());
		
		
	}

}
