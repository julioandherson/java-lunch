package pitaqueiro;

import Exceptions.EnderecoInvalidoException;
import Exceptions.RestauranteInvalidoException;
import Exceptions.TipoComidaInvalidoException;


public class Estabelecimento {

	private String Restaurante;
	private String Endereco;
	private String TipoComida;
	
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

	public String getRestaurante() {
		return Restaurante;
	}

	public String getEndereco() {
		return Endereco;
	}

	public String getTipoComida() {
		return TipoComida;
	}
	
	public boolean validaEstabelecimento(String Restaurante, String Endereco, String TipoComida){
		if(!(Restaurante == null || Restaurante.equals("") || Endereco == null || Endereco.equals("") || TipoComida == null || TipoComida.equals(""))){
			return true;
		}
		return false;
	}
		
}
