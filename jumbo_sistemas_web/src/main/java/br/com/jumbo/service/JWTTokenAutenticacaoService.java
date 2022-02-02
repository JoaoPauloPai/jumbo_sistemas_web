/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author João Paulo
 *
 * 1 de fev. de 2022
 * 22:05:34
 */
@Service
@Component
public class JWTTokenAutenticacaoService {
	
	/*Token validade 11 dias*/
	private static final long EXPIRATION_TIME = 959990000;
	
	private static final String SECRET = "hdsiokwekoio-fdg";

}
