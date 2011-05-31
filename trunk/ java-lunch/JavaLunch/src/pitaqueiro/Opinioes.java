package pitaqueiro;

import java.util.Map;
import exception.DataInvalidaException;
import exception.MapaOpinioesInvalidoException;
import exception.NomeInvalidoException;
/**
 * 
 * @author Julio, Lucas, Kevin
 *
 */
public class Opinioes {
	private final String Anonimo = "Anonimo";
	private String DataHora;
	private String Nome;
	private Map<String, String> mapaOpinioes;
		/**
	 * Construtor de Opinioes
	 * @param DataHora
	 * @param Nome
	 * @param mapaOpinioes
	 * @throws DataInvalidaException se a data for do tipo null ou String vazia
	 * @throws NomeInvalidoException se o nome for do tipo null ou String vazia
	 * @throws MapaOpinioesInvalidoException se o mapa for null
	 */
	public Opinioes(String DataHora, String Nome, Map<String, String> mapaOpinioes) throws DataInvalidaException, NomeInvalidoException, MapaOpinioesInvalidoException{
		if(validaOpinioes(DataHora, Nome, mapaOpinioes)){
			this.DataHora = DataHora;
			this.Nome = Nome;
			this.mapaOpinioes = mapaOpinioes;
		}else{
			if(DataHora == null || DataHora.equals("")){
				throw new DataInvalidaException();
			}if(Nome == null){
				throw new NomeInvalidoException();
			}if(mapaOpinioes == null){
				throw new MapaOpinioesInvalidoException();
			}
		}
	}
	/**
	 * Recupera o mapa de um usuario com todas as suas opinioes e notas
	 * @return mapaOpinioes
	 */
	public Map<String, String> getMapaOpinioes() {
		return mapaOpinioes;
	}

	public void setMapaOpinioes(Map<String, String> mapaOpinioes) {
		this.mapaOpinioes = mapaOpinioes;
	}
	/**
	 * Recupera Nome do usuario
	 * @return Nome
	 */
	public String getDataHora() {
		return DataHora;
	}

	public String getNome() {
		if(Nome.trim().equals("")){
			return Anonimo;
		}
		return Nome;
	}
	/**
	 * Valida os parametros passados no Construtor
	 * @param DataHora
	 * @param Nome
	 * @param mapaOpinioes
	 * @return true se nem DataHora e Nome forem do tipo null ou String vazia e se mapaOpinioes nao for do tipo null, se nao, retorna false
	 */
	public boolean validaOpinioes(String DataHora, String Nome, Map<String, String> mapaOpinioes){
		if(!(DataHora == null || DataHora.equals("") || Nome == null || mapaOpinioes == null)){
			return true;
		}
		return false;
	}


	
}
