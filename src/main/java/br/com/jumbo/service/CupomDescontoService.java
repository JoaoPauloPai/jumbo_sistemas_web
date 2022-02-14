/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.CupomDesc;
import br.com.jumbo.repository.CupomDescontoRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:06:36
 */
@Service
public class CupomDescontoService {
	
	@Autowired
	private CupomDescontoRepository cupomDescontoRepository;
	
	public CupomDesc save(CupomDesc cupomDesc) {
		
		return cupomDescontoRepository.save(cupomDesc);
	}

}
