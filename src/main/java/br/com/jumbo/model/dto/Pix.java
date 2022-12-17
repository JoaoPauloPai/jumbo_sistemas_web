/**
 * 
 */
package br.com.jumbo.model.dto;

import java.io.Serializable;

/**
 * @author João Paulo
 *
 * 16 de dez. de 2022
 * 22:13:32
 */
public class Pix implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
	private String payloadInBase64;
	private String imageInBase64;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPayloadInBase64() {
		return payloadInBase64;
	}

	public void setPayloadInBase64(String payloadInBase64) {
		this.payloadInBase64 = payloadInBase64;
	}

	public String getImageInBase64() {
		return imageInBase64;
	}

	public void setImageInBase64(String imageInBase64) {
		this.imageInBase64 = imageInBase64;
	}

}
