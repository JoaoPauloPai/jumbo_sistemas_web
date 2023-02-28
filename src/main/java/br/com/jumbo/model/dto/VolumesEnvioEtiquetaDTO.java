/**
 * 
 */
package br.com.jumbo.model.dto;

import java.io.Serializable;

/**
 * @author João Paulo
 *
 * 27 de fev. de 2023
 * 20:45:43
 */
public class VolumesEnvioEtiquetaDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private String height;
	private String width;
	private String length;
	private String weight;
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	

}
