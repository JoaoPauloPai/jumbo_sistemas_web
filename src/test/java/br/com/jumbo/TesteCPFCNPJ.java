/**
 * 
 */
package br.com.jumbo;

import br.com.jumbo.util.ValidaCNPJ;
import br.com.jumbo.util.ValidaCPF;

/**
 * @author João Paulo
 *
 * 11 de abr. de 2022
 * 20:48:09
 */
public class TesteCPFCNPJ {
	public static void main(String[] args) {
		boolean isCnpj = ValidaCNPJ.isCNPJ("66.347.536/0001-96");
		
		System.out.println("Cnpj Válido : " + isCnpj);
		
		
		boolean isCpf = ValidaCPF.isCPF("255.326.610-30");
		
		System.out.println("Cpf válido: " +  isCpf);
		
	}

}
