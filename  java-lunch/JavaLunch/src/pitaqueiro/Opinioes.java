package pitaqueiro;

import java.util.Map;

public class Opinioes {
	private final String Anonimo = "Anonimo";
	private String Data;
	private String Nome;
	private Map<String, String> mapaOpinioes;
	
	public Opinioes(String Data, String Nome, Map<String, String> mapaOpinioes){
		this.Data = Data;
		this.Nome = Nome;
		this.mapaOpinioes = mapaOpinioes;
	}

	public Map<String, String> getMapaOpinioes() {
		return mapaOpinioes;
	}

	public void setMapaOpinioes(Map<String, String> mapaOpinioes) {
		this.mapaOpinioes = mapaOpinioes;
	}

	public String getData() {
		return Data;
	}

	public String getNome() {
		if(Nome.trim().equals("")){
			return Anonimo;
		}
		return Nome;
	}



	
}
