/**
 * 
 */
package br.com.jumbo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author João Paulo
 *
 * 15 de fev. de 2023
 * 17:12:51
 */
public class ServletInitializer extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		return builder.sources(JumboSistemasWebApplication.class);
	}

}
