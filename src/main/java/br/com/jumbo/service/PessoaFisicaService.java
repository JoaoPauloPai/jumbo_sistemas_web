/**
 * 
 */
package br.com.jumbo.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.PessoaFisica;
import br.com.jumbo.model.Usuario;
import br.com.jumbo.repository.PessoaFisicaRepository;
import br.com.jumbo.repository.UsuarioRepository;

/**
 * @author João Paulo
 *
 *         12 de jan. de 2022 15:20:58
 */
@Service
public class PessoaFisicaService {

	@Autowired
	private ServiceSendEmail serviceSendEmail;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public PessoaFisica save(PessoaFisica pessoaFisica) {

		/* Qualquer tipo de validação */

		return pessoaFisicaRepository.save(pessoaFisica);

	}

	public PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) {

		for (int i = 0; i < pessoaFisica.getEnderecos().size(); i++) {
			pessoaFisica.getEnderecos().get(i).setPessoa(pessoaFisica);

		}

		pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);

		Usuario usuarioPf = usuarioRepository.findUserByPessoa(pessoaFisica.getId(), pessoaFisica.getEmail());

		if (usuarioPf == null) {

			String constraint = usuarioRepository.consultaConstraintAcesso();
			if (constraint != null) {
				jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint + "; commit;");
			}

			usuarioPf = new Usuario();
			usuarioPf.setDataAtualSenha(Calendar.getInstance().getTime());
			usuarioPf.setEmpresa(pessoaFisica.getEmpresa());
			usuarioPf.setPessoa(pessoaFisica);
			usuarioPf.setLogin(pessoaFisica.getEmail());

			String senha = "" + Calendar.getInstance().getTimeInMillis();
			String senhaCript = new BCryptPasswordEncoder().encode(senha);

			usuarioPf.setSenha(senhaCript);

			usuarioPf = usuarioRepository.save(usuarioPf);

			usuarioRepository.insereAcessoUserPj(usuarioPf.getId());
			usuarioRepository.insereAcessoUser(usuarioPf.getId(), "ROLE_ADMIN");

			StringBuilder menssagemHtml = new StringBuilder();

			menssagemHtml.append("<b>Segue abaixo seus dados de acesso para a loja virtual</b><br/>");
			menssagemHtml.append("<b>Login: </b>" + pessoaFisica.getEmail() + "<br/>");
			menssagemHtml.append("<b>Senha: </b>").append(senha).append("<br/><br/>");
			menssagemHtml.append("Obrigado!");

			try {
				serviceSendEmail.enviarEmailHtml("Acesso Gerado para Loja Virtual", menssagemHtml.toString(),
						pessoaFisica.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return pessoaFisica;
	}

}
