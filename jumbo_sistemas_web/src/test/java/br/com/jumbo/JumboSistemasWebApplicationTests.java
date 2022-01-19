package br.com.jumbo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jumbo.controller.AcessoController;
import br.com.jumbo.model.Acesso;
import br.com.jumbo.repository.AcessoRepository;
import junit.framework.TestCase;

@SpringBootTest(classes = JumboSistemasWebApplication.class)
public class JumboSistemasWebApplicationTests extends TestCase {

	@Autowired
	private AcessoController acessoController;
	
	@Autowired
	private AcessoRepository acessoRepository;
	
	@Autowired
	private WebApplicationContext wac;
	
	/*Teste do end-point de salvar*/
	@Test
	public void testRestApiCadastroAcesso() throws JsonProcessingException, Exception {
		
		 DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac); 
		 MockMvc mockMvc = builder.build();
		 
		 Acesso acesso = new Acesso();
		 
		 acesso.setDescricao("ROLE_COMPRADOR");
		 
		 ObjectMapper objectMapper = new ObjectMapper();
		 
		 ResultActions retornoApi = mockMvc
				                 .perform(MockMvcRequestBuilders.post("/salvarAcesso")
				                 .content(objectMapper.writeValueAsString(acesso))
				                 .accept(MediaType.APPLICATION_JSON)
				                 .contentType(MediaType.APPLICATION_JSON));
		
              System.out.println("Retorno da API : " + retornoApi.andReturn().getResponse().getContentAsString());
              
              Acesso objetoRetorno = objectMapper
            		             .readValue(retornoApi.andReturn().getResponse().getContentAsString(), 
            				     Acesso.class);
              
              assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());
		
	}
	
	/*Teste do end-point de Delete*/
	@Test
	public void testRestApiDeleteAcesso() throws JsonProcessingException, Exception {
		
		 DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac); 
		 MockMvc mockMvc = builder.build();
		 
		 Acesso acesso = new Acesso();
		 
		 acesso.setDescricao("ROLE_COMPRADOR_DELETE");
		 
		 acesso = acessoRepository.save(acesso);
		 
		 ObjectMapper objectMapper = new ObjectMapper();
		 
		 ResultActions retornoApi = mockMvc
				                 .perform(MockMvcRequestBuilders.post("/deleteAcesso")
				                 .content(objectMapper.writeValueAsString(acesso))
				                 .accept(MediaType.APPLICATION_JSON)
				                 .contentType(MediaType.APPLICATION_JSON));
		
              System.out.println("Retorno da API : " + retornoApi.andReturn().getResponse().getContentAsString());
              System.out.println("Status Retorno : " + retornoApi.andReturn().getResponse().getStatus());
              
assertEquals("Acesso deletado com sucesso!", retornoApi.andReturn().getResponse().getContentAsString());
assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

		
	}

	
	//@Test
	//public void testCadastraAcesso() {

	//	Acesso acesso = new Acesso();

		//acesso.setDescricao("ROLE_ADMIN");

	//	assertEquals(true, acesso.getId() == null);

		/*Grava no BD*/
	
	//	acesso = acessoController.salvarAcesso(acesso).getBody();

	//	assertEquals(true, acesso.getId() > 0);

		/*Valida dado salvos de forma correta*/
	
	//	assertEquals("ROLE_ADMIN", acesso.getDescricao());
		
		/*Teste de Carregamento*/
	//	Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();

	//	assertEquals(acesso.getId(), acesso2.getId());
		
		/*Teste de Delete*/
		
	//	acessoRepository.deleteById(acesso2.getId());
		
	//	acessoRepository.flush();
		
	//	Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null);
		
	//	assertEquals(true, acesso3 == null);
		
		
		/*Teste de query*/
	//	acesso = new Acesso();
		
	//	acesso.setDescricao("ROLE_ALUNO");
		
	//	acesso = acessoController.salvarAcesso(acesso).getBody();
		
	//	List<Acesso> acessos = acessoRepository.buscarAcessoDesc("ALUNO".trim().toUpperCase());
		
	//	assertEquals(1, acessos.size());
		
	//	acessoRepository.deleteById(acesso.getId());
		
		
	//}
	

}
