/**
 * 
 */
package br.com.jumbo.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author João Paulo
 *
 *         23 de mar. de 2022 18:28:27
 */
@Service
public class ServiceSendEmail {

	/**
	 * Servidor do email com Gmail private String userName =
	 * "empresatecnologia.sa@gmail.com"; private String senha = "marido@2021pai";
	 **/

	/*
	 * private String userName = "joaopaulopai99@gmail.com"; private String senha
	 * ="marido@2021pai";
	 */

	/* Servidor do email com Hotmail */
	private String userName = "motokleber2009@hotmail.com.com";
	private String senha = "marido@2021pai";

	/* Liberar o acesso do email */
	/* https://www.google.com/settings/security/lessecureapps */

	@Async
	public void enviarEmailHtml(String assunto, String menssagem, String emailDestino)
			throws UnsupportedEncodingException, MessagingException {

		Properties properties = new Properties();
		/** Parâmetros de conexão com servidor gmail */

		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls", "false");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		/** Parâmetros de conexão com servidor Hotmail */
		/*
		 * properties.put("mail.transport.protocol", "smtp");
		 * properties.put("mail.smtp.host", "smtp.live.com");
		 * properties.put("mail.smtp.socketFactory.port", "587");
		 * properties.put("mail.smtp.socketFactory.fallback", "false");
		 * properties.put("mail.smtp.starttls.enable", "true");
		 * properties.put("mail.smtp.auth", "true"); properties.put("mail.smtp.port",
		 * "587");
		 */

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(userName, senha);

			}
		});

		session.setDebug(true);

		Address[] toUser = InternetAddress.parse(emailDestino);

		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(userName, "João Paulo - Projeto Web", "UTF-8"));
		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(assunto);
		message.setContent(menssagem, "text/html; charset=utf-8");

		Transport.send(message);

	}

}
