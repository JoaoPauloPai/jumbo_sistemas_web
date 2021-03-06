/**
 * 
 */
package br.com.jumbo.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.jumbo.AplicationContextLoad;
import br.com.jumbo.model.Usuario;
import br.com.jumbo.repository.UsuarioRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * @author João Paulo
 *
 *         2 de fev. de 2022 18:27:55
 */
/* Criar a autenticação e retonar também a autenticação JWT */
@Service
@Component
public class JWTTokenAutenticacaoService {

	/* Token de validade de 11 dias = 959990000
	 *                      07 dias = 604800
	 *                      31557600 */
	private static final long EXPIRATION_TIME = 959990000;

	/* Chave de senha para juntar com o JWT */
	private static final String SECRET = "ss/-*-*sds565dsd-s/d-s*dsds";
                                        	

	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_STRING = "Authorization";

	/* Gera o token e da a responsta para o cliente o com JWT */
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {

		/* Montagem do Token */

		String JWT = Jwts.builder()./* Chama o gerador de token */
				setSubject(username) /* Adiciona o user */
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); /* Temp de expiração */

		/*
		 * Exe: Bearer
		 * *-/a*dad9s5d6as5d4s5d4s45dsd54s.sd4s4d45s45d4sd54d45s4d5s.ds5d5s5d5s65d6s6d
		 */
		String token = TOKEN_PREFIX + " " + JWT;


		/*
		 * Dá a resposta pra tela e para o cliente, outra API, navegador, aplicativo,
		 * javascript, outra chamadajava
		 */
		response.addHeader(HEADER_STRING, token);
		liberacaoCors(response);

		/* Usado para ver no Postman para teste */
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");

	}

	/* Retorna usuario validado com Token ou caso não seja retorna Null */
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String token = request.getHeader(HEADER_STRING);

		
		try {
		
		if (token != null) {

			String tokenlimpo = token.replace(TOKEN_PREFIX, "").trim();

			/* Faz a validação do Token do usuario na requesição e obtem o User */
			String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(tokenlimpo)
					.getBody().getSubject();

			if (user != null) {

				Usuario usuario = AplicationContextLoad
						.getApplicationContext()
						.getBean(UsuarioRepository.class).findUserByLogin(user);
				

				if (usuario != null) {
					return new UsernamePasswordAuthenticationToken(
							usuario.getLogin(),
							usuario.getSenha(),
							usuario.getAuthorities());
				}

			}

		}
	}catch (SignatureException e) {
		response.getWriter().write("Token está inválido.");
		
	}catch (ExpiredJwtException e) {
		response.getWriter().write("Token1 está expirado, efetue o login novamente.");
	}
    finally {
			
			liberacaoCors(response);
		}
		
		return null;
	}

	// Fazendo liberação contra erro de Cors
	private void liberacaoCors(HttpServletResponse response) {

		if (response.getHeader("Acess-Control-Allow-Oringin") == null) {
			response.addHeader("Acess-Control-Allow-Oringin", "*");

		}

		if (response.getHeader("Acess-Control-Allow-Headers") == null) {
			response.addHeader("Acess-Control-Allow-Headers", "*");
		}

		if (response.getHeader("Acess-Control-Request-Headrs") == null) {
			response.addHeader("Acess-Control-Request-Headrs", "*");
		}

		if (response.getHeader("Acess-Control-Allow-Methods") == null) {
			response.addHeader("Acess-Control-Allow-Methods", "*");
		}

	}
}
