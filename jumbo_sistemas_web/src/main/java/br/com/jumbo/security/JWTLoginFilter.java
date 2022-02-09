/**
 * 
 */
package br.com.jumbo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author João Paulo
 *
 * 8 de fev. de 2022
 * 18:36:46
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{

	/*Configurando o Gerenciador de Autenticacao*/
	public JWTLoginFilter(String url, AuthenticationManager) {
		
	}
	
	protected JWTLoginFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

}
