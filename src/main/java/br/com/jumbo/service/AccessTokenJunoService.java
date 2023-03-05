/**
 * 
 */
package br.com.jumbo.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import br.com.jumbo.model.AccessTokenJunoAPI;

/**
 * @author João Paulo
 *
 * 5 de mar. de 2023
 * 15:00:47
 */
@Service
public class AccessTokenJunoService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public AccessTokenJunoAPI buscaTokenAtivo() {
		
		try {
		
		   AccessTokenJunoAPI accessTokenJunoAPI = 
				  (AccessTokenJunoAPI) entityManager
				  .createQuery("select a from AccessTokenJunoAPI a ")
		          .setMaxResults(1).getSingleResult();
		   
		   return accessTokenJunoAPI;
		}catch (NoResultException e) {
			return null;
		}
		
		
	}

}
