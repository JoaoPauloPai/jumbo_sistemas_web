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

		PessoaJuridica pessoaJuridica = new PessoaJuridica();

		pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis() + 2);
		pessoaJuridica.setNome("Empresa Teste Matriz");
		pessoaJuridica.setEmail("empresatestesalvarpj2@gmail.com");
		pessoaJuridica.setTelefone("6536832009");
		pessoaJuridica.setInscEstadual("65556565656665");
		pessoaJuridica.setInscMunicipal("55554565656565");
		pessoaJuridica.setNomeFantasia("NomeFantasia Emp Teste");
		pessoaJuridica.setRazaoSocial("4656656566");
		pessoaJuridica.setCategoria("CategoriaEmpTeste");

		pessoaController.salvarPessoaJuridica(pessoaJuridica);
		/*
		 * PessoaFisica pessoaFisica = new PessoaFisica();
		 * 
		 * pessoaFisica.setCpf("76691179153");
		 * pessoaFisica.setNome("João Paulo Gomes da Silva");
		 * pessoaFisica.setEmail("joaopaulopai99@gmail.com");
		 * pessoaFisica.setTelefone("6599920-7437");
		 * 
		 * pessoaFisica.setEmpresa(pessoaFisica);
		 * 
		 * pessoaFisicaRepository.save(pessoaFisica);
		 */
	}
}