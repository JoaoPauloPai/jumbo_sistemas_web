/**
 * 
 */
package br.com.jumbo.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.jumbo.model.AccessTokenJunoAPI;
import br.com.jumbo.repository.AccesTokenJunoRepository;
import br.com.jumbo.repository.BoletoJunoRepository;
import br.com.jumbo.repository.VendaSiteLojaRepository;

/**
 * @author João Paulo
 *
 * 5 de mar. de 2023
 * 15:07:05
 */
@Service
public class ServiceJunoBoleto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AccessTokenJunoService accessTokenJunoService;
	
	@Autowired
	private AccesTokenJunoRepository accesTokenJunoRepository;
	
	@Autowired
	private VendaSiteLojaRepository vendaSiteLojaRepository;
	
	@Autowired
	private BoletoJunoRepository boletoJunoRepository;
	

public AccessTokenJunoAPI obterTokenApiJuno() throws Exception {
		
		AccessTokenJunoAPI accessTokenJunoAPI = accessTokenJunoService.buscaTokenAtivo();
		
		if (accessTokenJunoAPI == null || (accessTokenJunoAPI != null && accessTokenJunoAPI.expirado()) ) {
			
			String clienteID = "vi7QZerW09C8JG1o";
			String secretID = "$A_+&ksH}&+2<3VM]1MZqc,F_xif_-Dc";
			
			Client client = new HostIgnoringCliente("https://api.juno.com.br/").hostIgnoringCliente();
			
			WebResource webResource = client.resource("https://api.juno.com.br/authorization-server/oauth/token?grant_type=client_credentials");
			
			String basicChave = clienteID + ":" + secretID;
			String token_autenticao = DatatypeConverter.printBase64Binary(basicChave.getBytes());
			
			ClientResponse clientResponse = webResource.
					accept(MediaType.APPLICATION_FORM_URLENCODED)
					.type(MediaType.APPLICATION_FORM_URLENCODED)
					.header("Content-Type", "application/x-www-form-urlencoded")
					.header("Authorization", "Basic " + token_autenticao)
					.post(ClientResponse.class);
			
			if (clientResponse.getStatus() == 200) { /*Sucesso*/
				accesTokenJunoRepository.deleteAll();
				accesTokenJunoRepository.flush();
				
				AccessTokenJunoAPI accessTokenJunoAPI2 = clientResponse.getEntity(AccessTokenJunoAPI.class);
				accessTokenJunoAPI2.setToken_acesso(token_autenticao);
				
				accessTokenJunoAPI2 = accesTokenJunoRepository.saveAndFlush(accessTokenJunoAPI2);
				
				return accessTokenJunoAPI2;
			}else {
				return null;
			}
			
			
		}else {
			return accessTokenJunoAPI;
		}
	}
	
	/*
	 * {"id":"wbh_AE815607C1F5A94934934A2EA3CA0180","url":"https://lojavirtualmentoria-env.eba-bijtuvkg.sa-east-1.elasticbeanstalk.com/loja_virtual_mentoria/requisicaojunoboleto/notificacaoapiv2","secret":"23b85f4998289533ed3ee310ae9d5bd3f803fadac7fb1ecff0296fbf1bb060f8","status":"ACTIVE","eventTypes":[{"id":"evt_DC2E7E8848B08C62","name":"DOCUMENT_STATUS_CHANGED","label":"O status de um documento foi alterado","status":"ENABLED"}],"_links":{"self":{"href":"https://api.juno.com.br/api-integration/notifications/webhooks/wbh_AE815607C1F5A94934934A2EA3CA0180"}}}
	 * 
	 * */


	
	

}
