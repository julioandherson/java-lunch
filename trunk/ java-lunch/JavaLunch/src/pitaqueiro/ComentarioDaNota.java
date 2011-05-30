package pitaqueiro;

public enum ComentarioDaNota {
	INCRIVEL("Incrivel, sensacional, impressionante"), //Nota 5
	MUITO_BOM("Muito bom"), //Nota 4
	BASTANTE_BOM("Bastante bom"), //Nota 3
	EH_BONZINHO("E Bonzinho"), //Nota 2
	NAO_EH_RUIM("Nao e ruim"), //Nota 1
	NAO_CONHECO("Nao conheco"), //Nota 0
	ACHO_UM_POUCO_RUIM("Acho um pouco ruim"), //Nota -1
	ACHO_RUIM("Acho ruim"), //Nota -2
	ACHO_BASTANTE_RUIM("Acho bastante ruim"), //Nota -3
	ACHO_MUITO_RUIM("Acho muito ruim"), //Nota -4
	DETESTO("Detesto"); //Nota -5
	
	private String Comentario;
	
	private ComentarioDaNota(String Comentario){
		this.Comentario = Comentario;
	}
	
	public String getComentario(){
		return Comentario;
	}
}
