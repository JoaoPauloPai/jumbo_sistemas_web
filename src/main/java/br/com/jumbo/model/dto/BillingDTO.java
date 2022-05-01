/**
 * 
 */
package br.com.jumbo.model.dto;

import java.io.Serializable;

/**
 * @author João Paulo
 *
 *         1 de mai. de 2022 14:38:41
 */
public class BillingDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean free;

	private boolean database;

	/**
	 * @return the free
	 */
	public boolean isFree() {
		return free;
	}

	/**
	 * @param free the free to set
	 */
	public void setFree(boolean free) {
		this.free = free;
	}

	/**
	 * @return the database
	 */
	public boolean isDatabase() {
		return database;
	}

	/**
	 * @param database the database to set
	 */
	public void setDatabase(boolean database) {
		this.database = database;
	}

}
