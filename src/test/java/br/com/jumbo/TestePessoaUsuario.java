/**
 * 
 */
package br.com.jumbo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.jumbo.model.PessoaJuridica;
import br.com.jumbo.repository.PessoaRepository;
import br.com.jumbo.service.PessoaUserService;

/**
 * @author João Paulo
 *
 * 1 de mar. de 2022
 * 20:06:04
 */
@Profile("test")
@SpringBootTest(classes = JumboSistemasWebApplication.class)
public class TestePessoaUsuario {

	
	@Autowired
	private PessoaUserService pessoaUserService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	@Test
	public void testCadPessoaFisica() {
	
	PessoaJuridica pessoaJuridica = new PessoaJuridica();
	
	pessoaJuridica.setCnpj("865545598956556");
	pessoaJuridica.setNome("Alex fernando");
	pessoaJuridica.setEmail("alex.fernando.egidio@gmail.com");
	pessoaJuridica.setTelefone("45999795800");
	pessoaJuridica.setInscEstadual("65556565656665");
	pessoaJuridica.setInscMunicipal("55554565656565");
	pessoaJuridica.setNomeFantasia("54556565665");
	pessoaJuridica.setRazaoSocial("4656656566");
	
	pessoaRepository.save(pessoaJuridica);
	
	}
}
