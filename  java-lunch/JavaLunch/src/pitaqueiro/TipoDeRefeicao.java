package pitaqueiro;


public enum TipoDeRefeicao {
	A_LA_CARTE("A La Carte"),
	PRATO_FEITO("Prato Feito"),
	SELF_SERVICE("Self Service");
	
	private String Tipo;
	
	private TipoDeRefeicao(String Tipo){
		this.Tipo = Tipo;
	}
	
	public String getTipoComida(){
		return Tipo;
	}
}

