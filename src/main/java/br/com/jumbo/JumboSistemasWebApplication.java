package br.com.jumbo;

import java.util.concurrent.Executor;

/**
 * @author João Paulo
 *
 * 21 de dez. de 2021
 * 21:34:48
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EntityScan(basePackages = "br.com.jumbo.model")
@ComponentScan(basePackages = { "br.*" })
@EnableJpaRepositories(basePackages = "br.com.jumbo.repository")
@EnableTransactionManagement
public class JumboSistemasWebApplication implements AsyncConfigurer{

	public static void main(String[] args) {

		 //System.out.println("senha : "+ new BCryptPasswordEncoder().encode("1234"));

		SpringApplication.run(JumboSistemasWebApplication.class, args);
		
		

	}
	
	@Override
	@Bean
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
				executor.setCorePoolSize(10);
				executor.setMaxPoolSize(20);
				executor.setQueueCapacity(500);
				executor.setThreadNamePrefix("Assyncrono Thread");
				executor.initialize();
		
		return executor;
	}

}
