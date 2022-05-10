/**
 * 
 */
package br.com.jumbo;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.jumbo.controller.PessoaFisicaController;
import br.com.jumbo.controller.PessoaJuridicaController;
import br.com.jumbo.enums.TipoEndereco;
import br.com.jumbo.model.Endereco;
import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.model.PessoaJuridica;
import br.com.jumbo.repository.PessoaJuridicaRepository;
import br.com.jumbo.repository.PessoaRepository;
import junit.framework.TestCase;

/**
 * @author João Paulo
 *
 *         1 de mar. de 2022 20:06:04
 */
@Profile("test")
@SpringBootTest(classes = JumboSistemasWebApplication.class)
public class TestePessoaUsuario extends TestCase {

	@Autowired
	private PessoaJuridicaController pessoaController;
	
	@Autowired
	private PessoaFisicaController pessoaFisicaController;
	
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@Test
	public void testCadPessoaJuridica() throws ExceptionJumboSistemas {



		
		 PessoaJuridica pessoaJuridica = new PessoaJuridica();
		 
		 pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis() + 2);
		 /* pessoaJuridica.setNome("Empresa Teste Matriz3");
		 * pessoaJuridica.setEmail("empresatesteunit01@gmail.com3");
		 * pessoaJuridica.setTelefone("6536832009");
		 * pessoaJuridica.setInscEstadual("65556565656665");
		 * pessoaJuridica.setInscMunicipal("55554565656565");
		 * pessoaJuridica.setNomeFantasia("NomeFantasia Emp Teste3");
		 * pessoaJuridica.setRazaoSocial("4656656566");
		 * pessoaJuridica.setCategoria("CategoriaEmpTeste3");
		 * 
		 * 
		 * Endereco endereco1 = new Endereco();
		 * 
		 * endereco1.setBairro("Centro"); endereco1.setCep("45355645");
		 * endereco1.setCidade("Cuiaba"); endereco1.setComplemento("Casa Rosa");
		 * endereco1.setEmpresa(pessoaJuridica); endereco1.setNumero("53657");
		 * endereco1.setTipoEndereco(TipoEndereco.ENTREGA);
		 * endereco1.setRuaLogra("Rua Das Acacias"); endereco1.setUf("MT");
		 * Endereco endereco2 = new Endereco(); endereco2.setBairro("Centro");
		 * endereco2.setCep("45355645"); endereco2.setCidade("Curitiba");
		 * endereco2.setComplemento("Casa Rosa"); endereco2.setEmpresa(pessoaJuridica);
		 * endereco2.setNumero("53657");
		 * endereco2.setTipoEndereco(TipoEndereco.COBRANCA);
		 * endereco2.setRuaLogra("Rua Soa Judas"); endereco2.setUf("PR");
		 * 
		 * pessoaJuridica.getEnderecos().add(endereco2);
		 * pessoaJuridica.getEnderecos().add(endereco1);
		 * 
		 * pessoaJuridica =
		 * pessoaController.salvarPessoaJuridica(pessoaJuridica).getBody();
		 * 
		 * assertEquals(true, pessoaJuridica.getId() > 0);
		 * 
		 * for(Endereco endereco : pessoaJuridica.getEnderecos()) { assertEquals(true,
		 * endereco.getId() > 0);
		 * 
		 * }
		 * 
		 * assertEquals(2, pessoaJuridica.getEnderecos().size());
		 */
	}
		@Test
		public void testCadPessoaFisica() throws ExceptionJumboSistemas {
			
			PessoaJuridica pessoaJuridica =  pessoaJuridicaRepository.existeCnpjCadastrado("08905412000182");
			

			PessoaFisica pessoaFisica = new PessoaFisica();
			pessoaFisica.setCpf("713.482.980-49");
			pessoaFisica.setNome("Joao Paulo Gomes");
			pessoaFisica.setEmail("joaopaulopai99@gmail.com");
			pessoaFisica.setTelefone("45999795800");
			pessoaFisica.setEmpresa(pessoaJuridica);
			
			Endereco endereco1 = new Endereco();
			endereco1.setBairro("Jd Dias");
			endereco1.setCep("556556565");
			endereco1.setComplemento("Casa cinza");
			endereco1.setNumero("389");
			endereco1.setPessoa(pessoaFisica);
			endereco1.setRuaLogra("Av. são joao sexto");
			endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
			endereco1.setUf("PR");
			endereco1.setCidade("Curitiba");
			endereco1.setEmpresa(pessoaJuridica);
			
			
			Endereco endereco2 = new Endereco();
			endereco2.setBairro("Jd Maracana");
			endereco2.setCep("7878778");
			endereco2.setComplemento("Andar 4");
			endereco2.setNumero("555");
			endereco2.setPessoa(pessoaFisica);
			endereco2.setRuaLogra("Av. maringá");
			endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
			endereco2.setUf("PR");
			endereco2.setCidade("Curitiba");
			endereco2.setEmpresa(pessoaJuridica);
			
			pessoaFisica.getEnderecos().add(endereco2);
			pessoaFisica.getEnderecos().add(endereco1);

			pessoaFisica = pessoaFisicaController.salvarPessoaFisica(pessoaFisica).getBody();
			
			assertEquals(true, pessoaFisica.getId() > 0 );
			
			for (Endereco endereco : pessoaFisica.getEnderecos()) {
				assertEquals(true, endereco.getId() > 0);
			}
			
			assertEquals(2, pessoaFisica.getEnderecos().size());

		

	}
}
