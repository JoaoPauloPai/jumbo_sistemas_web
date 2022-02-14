/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.StatusRastreio;
import br.com.jumbo.repository.StatusRatreioRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:41:16
 */
@Service
public class StatusRastreioService {
	
	@Autowired
	StatusRatreioRepository statusRatreioRepository;
	
	public StatusRastreio save(StatusRastreio statusRastreio) {
		
		return statusRatreioRepository.save(statusRastreio);
	}

}
