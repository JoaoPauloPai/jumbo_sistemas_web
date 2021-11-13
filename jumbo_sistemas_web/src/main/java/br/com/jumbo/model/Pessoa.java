package br.com.jumbo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1 , initialValue = 1)
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
	private Long Id;
	private String nomePessoao;
	private String emailPessoal;
	private  String fonePessoa;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNomePessoao() {
		return nomePessoao;
	}
	public void setNomePessoao(String nomePessoao) {
		this.nomePessoao = nomePessoao;
	}
	public String getEmailPessoal() {
		return emailPessoal;
	}
	public void setEmailPessoal(String emailPessoal) {
		this.emailPessoal = emailPessoal;
	}
	public String getFonePessoa() {
		return fonePessoa;
	}
	public void setFonePessoa(String fonePessoa) {
		this.fonePessoa = fonePessoa;
	}
	
	

}
