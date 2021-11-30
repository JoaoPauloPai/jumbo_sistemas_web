package br.com.jumbo.enums;

public enum StatusContaPagar {
	
	
	COBRANCA("Pagar"),
	VENCIDA("Vencida"),
	ABERTA("Aberta"),
	QUITADA("Quitada"),
	NEGOCIADA("Re-negociada");
	
	private String descricao;
	
	 private StatusContaPagar(String descricao) {
		 
    this.descricao = descricao;
	}
		
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		
		return this.toString();
	}

}
