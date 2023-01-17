/**
 * 
 */
package br.com.jumbo.model.dto;

import java.io.Serializable;

/**
 * @author João Paulo
 *
 * 16 de jan. de 2023
 * 21:20:37
 */
public class ToDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String postal_code;

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	
	

}
