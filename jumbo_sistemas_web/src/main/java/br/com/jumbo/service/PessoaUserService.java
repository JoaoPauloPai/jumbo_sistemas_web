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
import br.com.jumbo.model.PessoaJuridica;
import br.com.jumbo.model.Usuario;
import br.com.jumbo.repository.PessoaFisicaRepository;
import br.com.jumbo.repository.PessoaRepository;
import br.com.jumbo.repository.UsuarioRepository;

/**
 * @author João Paulo
 *
 *         1 de mar. de 2022 20:53:19
 */
@Service
public class PessoaUserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ServiceSendEmail serviceSendEmail;

	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica juridica) {

	

		for (int i = 0; i < juridica.getEnderecos().size(); i++) {
			juridica.getEnderecos().get(i).setPessoa(juridica);
			juridica.getEnderecos().get(i).setEmpresa(juridica);

		}

		juridica = pessoaRepository.save(juridica);

		Usuario usuarioPj = usuarioRepository.findUserByPessoa(juridica.getId(), juridica.getEmail());

		if (usuarioPj == null) {

			String constraint = usuarioRepository.consultaConstraintAcesso();

			if (constraint != null) {
				jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint " + constraint + "; commit;");
			}

			usuarioPj = new Usuario();
			usuarioPj.setDataAtualSenha(Calendar.getInstance().getTime());
			usuarioPj.setEmpresa(juridica);
			usuarioPj.setPessoa(juridica);
			usuarioPj.setLogin(juridica.getEmail());

			String senha = "" + Calendar.getInstance().getTimeInMillis();

			String senhaCript = new BCryptPasswordEncoder().encode(senha);

			usuarioPj.setSenha(senhaCript);

			usuarioPj = usuarioRepository.save(usuarioPj);

			usuarioRepository.insereAcessoPj(usuarioPj.getId());

			juridica = pessoaRepository.save(juridica);
             
			
			/*
			StringBuilder mensagemHtml = new StringBuilder();
			
			mensagemHtml.append("<b>Segue abaixo os dados de acesso</b>");
			mensagemHtml.append("<b>Login : </b>"+juridica.getEmail()+"</b><br/>");
			mensagemHtml.append("<b>Senha : </b>").append(senha).append("<br/><br/>");
			mensagemHtml.append("Obrigado!");
	

			try {

				serviceSendEmail.enviarEmailHtml("Acesso Gerado Java Web",mensagemHtml.toString() ,juridica.getEmail());
                
			} catch (Exception e) {
				
			}*/

		}

		return juridica;
	}

	public PessoaFisica save(PessoaFisica pFisica) {
		
		pFisica = pessoaFisicaRepository.save(pFisica);

		return pFisica;
	}

}