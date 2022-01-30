package br.com.jumbo;
/**
 * @author João Paulo
 *
 * 21 de dez. de 2021
 * 21:34:48
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EntityScan(basePackages = "br.com.jumbo.model")
@ComponentScan(basePackages = {"br.*"})
@EnableJpaRepositories(basePackages = "br.com.jumbo.repository")
@EnableTransactionManagement
public class JumboSistemasWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(JumboSistemasWebApplication.class, args);
	}

}
