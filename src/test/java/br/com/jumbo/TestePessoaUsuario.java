/**
 * 
 */
package br.com.jumbo;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.jumbo.controller.PessoaController;
import br.com.jumbo.enums.TipoEndereco;
import br.com.jumbo.model.Endereco;
import br.com.jumbo.model.Pessoa;
import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.model.PessoaJuridica;
import br.com.jumbo.repository.PessoaFisicaRepository;
import br.com.jumbo.repository.PessoaRepository;
import br.com.jumbo.service.PessoaUserService;
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
	private PessoaController pessoaController;

	@Test
	public void testCadPessoaFisica() throws ExceptionJumboSistemas {

		PessoaFisica pessoaFisica = new PessoaFisica();

		/*
		 * PessoaJuridica pessoaJuridica = new PessoaJuridica();
		 * 
		 * pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis() + 2);
		 * pessoaJuridica.setNome("Empresa Teste Matriz3");
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

		pessoaFisica.setCpf("7669117445456456");
		pessoaFisica.setNome("João Paulo Gomes da Silva teste");
		pessoaFisica.setEmail("joaopaulopai99@gmail.com teste");
		pessoaFisica.setTelefone("6599920-7437");
		//pessoaFisica.setEmpresa();

		pessoaController.salvarPessoaFisica(pessoaFisica).getBody();

	}
}
