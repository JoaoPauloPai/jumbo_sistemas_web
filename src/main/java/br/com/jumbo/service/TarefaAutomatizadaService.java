/**
 * 
 */
package br.com.jumbo.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.Usuario;
import br.com.jumbo.repository.UsuarioRepository;




/**
 * @author João Paulo
 *
 * 7 de abr. de 2022
 * 10:31:53
 */
@Component
@Service
public class TarefaAutomatizadaService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ServiceSendEmail serviceSendEmail;
	
	
	//@Scheduled(initialDelay = 2000, fixedDelay = 86400000) /*Roda a cada 24 horas*/
	@Scheduled(cron = "0 0 11 * * *", zone = "America/Sao_Paulo") /*Vai rodar todo dia as 11 horas da manhã horario de Sao paulo*/
	public void notificarUserTrocaSenha() throws UnsupportedEncodingException, MessagingException, InterruptedException {
		
		List<Usuario> usuarios = usuarioRepository.usuarioSenhaVencida();
		
		for (Usuario usuario : usuarios) {
			
			
			StringBuilder msg = new StringBuilder();
			msg.append("Olá, ").append(usuario.getPessoa().getNome()).append("<br/>");
			msg.append("Está na hora de trocar sua senha, já passou 90 dias de validade.").append("<br/>");
			msg.append("Troque sua senha do Jumbo Sistemas Web - João Paulo - Desenvolvedor");
			
			serviceSendEmail.enviarEmailHtml("Troca de senha", msg.toString(), usuario.getLogin());
			
			Thread.sleep(3000);
			
		}
		
		
	}


}
