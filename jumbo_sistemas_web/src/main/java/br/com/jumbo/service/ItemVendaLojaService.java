/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.ItemVendaLoja;
import br.com.jumbo.repository.ItemVendaLojaRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:21:18
 */
@Service
public class ItemVendaLojaService {
	
	@Autowired
	ItemVendaLojaRepository itemVendaLojaRepository;
	
	public ItemVendaLoja save(ItemVendaLoja itemVendaLoja) {
		
		return itemVendaLojaRepository.save(itemVendaLoja);
				
	}

}
