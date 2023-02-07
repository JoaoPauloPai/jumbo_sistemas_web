/**
 * 
 */
package br.com.jumbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jumbo.model.ItemVendaSite;
import br.com.jumbo.repository.ItemVendaSiteRepository;

/**
 * @author João Paulo
 *
 * 14 de jan. de 2022
 * 20:21:18
 */
@Service
public class ItemVendaLojaService {
	
	@Autowired
	ItemVendaSiteRepository itemVendaLojaRepository;
	
	public ItemVendaSite save(ItemVendaSite itemVendaLoja) {
		
		return itemVendaLojaRepository.save(itemVendaLoja);
				
	}

}
