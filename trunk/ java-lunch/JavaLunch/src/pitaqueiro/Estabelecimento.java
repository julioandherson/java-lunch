package pitaqueiro;

import exception.EnderecoInvalidoException;
import exception.RestauranteInvalidoException;
import exception.TipoComidaInvalidoException;

/**
 * Classe Estabelecimento, composta por Restaurante, Endereco e Tipo de Comida
 * @author Julio, Lucas, Kevin
 *
 */
public class Estabelecimento {

	private String Restaurante;
	private String Endereco;
	private String TipoComida;
	/**
	 * Construtor Estabelecimento
	 * @param Restaurante
	 * @param Endereco
	 * @param TipoComida
	 * @throws RestauranteInvalidoException se Restaurante for String Vazia ou null
	 * @throws EnderecoInvalidoException se Endereco for String Vazia ou null
	 * @throws TipoComidaInvalidoException se TipoDeComida for String Vazia ou null
	 */
	public Estabelecimento(String Restaurante, String Endereco, String TipoComida) throws RestauranteInvalidoException, EnderecoInvalidoException, TipoComidaInvalidoException{
		if(validaEstabelecimento(Restaurante, Endereco, TipoComida)){
			this.Restaurante = Restaurante;
			this.Endereco = Endereco;
			this.TipoComida = TipoComida;
		}else{
			if(Restaurante == null || Restaurante.equals(""))
				throw new RestauranteInvalidoException();
			else if(Endereco == null || Endereco.equals(""))
				throw new EnderecoInvalidoException();
			else
				throw new TipoComidaInvalidoException();
		}
	}
        /**
	 * Recupera o Restaurante
	 * @return Restaurante
	 */
	public String getRestaurante() {
		return Restaurante;
	}
	/**
	 * Recupera o Endereco
	 * @return Endereco
	 */
	public String getEndereco() {
		return Endereco;
	}
	/**
	 * Recupera TipoComida
	 * @return Tipo de Comida
	 */
	public String getTipoComida() {
		return TipoComida;
	}
	/**
	 * Valida Restaurante, Endereco e TipoDeComida.
	 * @param Restaurante
	 * @param Endereco
	 * @param TipoComida
	 * @return true se nenhum dos parametros forem String Vazia ou null, se nao, retorna false
	 */
	public boolean validaEstabelecimento(String Restaurante, String Endereco, String TipoComida){
		if(!(Restaurante == null || Restaurante.equals("") || Endereco == null || Endereco.equals("") || TipoComida == null || TipoComida.equals(""))){
			return true;
		}
		return false;
	}
		
}
