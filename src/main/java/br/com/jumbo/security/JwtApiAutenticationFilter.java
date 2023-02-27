/**
 * 
 */
package br.com.jumbo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author João Paulo
 *
 *         8 de fev. de 2022 18:47:42
 */

/* Filtro onde todas as requisicoes serão capturadas para autenticar */
public class JwtApiAutenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		/* Estabele a autenticao do user */

		try {

			//Authentication authentication = new JWTTokenAutenticacaoService()//.getAuthentication(HttpServletRequest)request, (HttpServletResponse)response);
			//		.getAuthentication((HttpServletRequest) request, (HttpServletResponse) response);

			/* Coloca o processo de autenticacao para o spring secutiry */
			//SecurityContextHolder.getContext().setAuthentication(authentication);

			chain.doFilter(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Erro no no sistema, avise o administrador: \n" + e.getMessage());
		}
	}
}
