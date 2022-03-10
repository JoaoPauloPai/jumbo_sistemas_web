/**
 * 
 */
package br.com.jumbo.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.PessoaJuridica;
import br.com.jumbo.model.Usuario;
import br.com.jumbo.repository.PessoaRepository;
import br.com.jumbo.repository.UsuarioRepository;

/**
 * @author João Paulo
 *
 * 1 de mar. de 2022
 * 20:53:19
 */
@Service
public class PessoaUserService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica juridica) {
		
		juridica = pessoaRepository.save(juridica);
		
		Usuario usuarioPj = usuarioRepository.findUserByPessoa(juridica.getId(), juridica.getEmail());
		
		if(usuarioPj == null) {
			
			String constraint = usuarioRepository.consultaConstraint();
			
			if(constraint != null) {
				jdbcTemplate.execute("begin; alter table usuarios_acesso drop constraint "+ constraint +"; commit;");
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
		}
		
		return juridica;
	}

}
