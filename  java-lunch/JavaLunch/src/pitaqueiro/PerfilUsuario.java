package pitaqueiro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Exceptions.MapaOpinioesInvalidoException;
import Exceptions.NomeInvalidoException;
import Exceptions.NotaRestauranteInvalidoException;
import Exceptions.RestauranteInvalidoException;

public class PerfilUsuario {
	private String Nome;
	private Map<String, String> mapaOpinioes;
	private final String NOTA_DEFAULT = "0";
	private int Semelhanca = 0;
	private List<Integer> listaNotasUsuario = new ArrayList<Integer>(); //COLOCAR NO CONSTRUTOR
	
	public PerfilUsuario(String Nome, Map<String, String> mapaOpinioes)throws Exception{
		if(validaUsuario(Nome, mapaOpinioes)){
			this.Nome = Nome;
			if(mapaOpinioes.size() >= 0){
				this.mapaOpinioes = mapaOpinioes;
				GeraRecomendacoesDefault();
			}
		}else{
			if(Nome == null || Nome.equals(""))
				throw new NomeInvalidoException();
			if(mapaOpinioes == null)
				throw new MapaOpinioesInvalidoException();
		}
	}
	
	public String getNome() {
		return Nome;
	}
	
	public Map<String, String> getMapaOpinioes() {
		return mapaOpinioes;
	}
	
	public void setMapaOpinioes(String Restaurante, String novaNota, Map<String, String> MapaOpinioes) throws RestauranteInvalidoException, NotaRestauranteInvalidoException, MapaOpinioesInvalidoException {
        if(Restaurante == null || Restaurante.equals("")){
        	throw new RestauranteInvalidoException();
        }else if(novaNota == null || novaNota.equals("")){
        	throw new NotaRestauranteInvalidoException();
        }else if(MapaOpinioes == null){
        	throw new MapaOpinioesInvalidoException();
        }
		Set<String> chaves = MapaOpinioes.keySet();  
        for (Iterator<String> iterator = chaves.iterator(); iterator.hasNext();){  
            String chave = iterator.next();  
            if(chave.equals(Restaurante)){  
            	mapaOpinioes.put(Restaurante, novaNota);
            }
        }  
	}
	
	public boolean validaUsuario(String Nome, Map<String, String> mapaOpinioes) throws Exception{
		if (!(Nome == null || Nome.equals("") || mapaOpinioes == null)){
			return true;
			}
		return false;
	}
	
	public void GeraRecomendacoesDefault() throws Exception{
		ManipulacaoDeArquivo Manipulacao = new ManipulacaoDeArquivo();
		//Manipulacao.leEstabelecimentos(); //Metodo que gera Lista de Restaurantes
		
		for(String Restaurante : Manipulacao.getListaRestaurantes()){
			if(!(mapaOpinioes.containsKey(Restaurante))){
				mapaOpinioes.put(Restaurante, NOTA_DEFAULT);
			}
		}
	}
		
	public String MapaOpinioestoString(ManipulacaoDeArquivo Manipulacao) throws IOException {
		Manipulacao.leEstabelecimentos(); //Metodo que gera Lista de Restaurantes
		String Chave;
		String Valor;
		String Concatena = "";
		String Comentario = null;
	
		for(int i = 0; i < Manipulacao.getListaRestaurantes().size(); i++){
			Chave = Manipulacao.getListaRestaurantes().get(i);
			Valor = mapaOpinioes.get(Chave);
			listaNotasUsuario.add(Integer.parseInt(Valor)); //USADO NO ALGORITMO PERSONALIZADO (LISTA NOTAS ORDENADA DOS RESTAURANTES)
			Concatena += mapaOpinioes.get(Manipulacao.getListaRestaurantes().get(i));

			if(Valor.equals("5")){
				Comentario = ComentarioDaNota.INCRIVEL.getComentario();
			}else if(Valor.equals("4")){
				Comentario = ComentarioDaNota.MUITO_BOM.getComentario();
			}else if(Valor.equals("3")){
				Comentario = ComentarioDaNota.BASTANTE_BOM.getComentario();
			}else if(Valor.equals("2")){
				Comentario = ComentarioDaNota.EH_BONZINHO.getComentario();
			}else if(Valor.equals("1")){
				Comentario = ComentarioDaNota.NAO_EH_RUIM.getComentario();
			}else if(Valor.equals("0")){
				Comentario = ComentarioDaNota.NAO_CONHECO.getComentario();
			}else if(Valor.equals("-1")){
				Comentario = ComentarioDaNota.ACHO_UM_POUCO_RUIM.getComentario();
			}else if(Valor.equals("-2")){
				Comentario = ComentarioDaNota.ACHO_RUIM.getComentario();
			}else if(Valor.equals("-3")){
				Comentario = ComentarioDaNota.ACHO_BASTANTE_RUIM.getComentario();
			}else if(Valor.equals("-4")){
				Comentario = ComentarioDaNota.ACHO_MUITO_RUIM.getComentario();
			}else if(Valor.equals("-5")){
				Comentario = ComentarioDaNota.DETESTO.getComentario();
			}
			Concatena += " : " + Comentario + ";";
		}
		return Concatena;
	}
	
	//FALTA TESTA
	public int getSemelhanca() {
		return Semelhanca;
	}

	//FALTA TESTA
	public void setSemelhanca(int semelhanca) {
		Semelhanca = semelhanca;
	}
	
	//FALTA TESTE
	public void GeraListNotasUsuario(ManipulacaoDeArquivo Manipulacao) throws IOException{
//		Manipulacao.leEstabelecimentos(); //Metodo que gera Lista de Restaurantes ---- >TIRAR DEPOIS
		String[] Opiniao;
		String Chave;
		String Valor;
		List<String> listaRestaurante = Manipulacao.getListaRestaurantes();
		for(int i = 0; i < listaRestaurante.size(); i++){
			Chave = listaRestaurante.get(i);
			Opiniao = mapaOpinioes.get(Chave).split(":");
			Valor = Opiniao[0].trim();
			listaNotasUsuario.add(Integer.parseInt(Valor)); //USADO NO ALGORITMO PERSONALIZADO (LISTA NOTAS ORDENADA DOS RESTAURANTES)
		}
	}
	
	//FALTA TESTE
	public List<Integer> getListaNotasUsuario() {
		return listaNotasUsuario;
	}

	public static void main(String args[]) throws Exception{
		//TESTES ALTERANDO NOTA
//		Map<String, String> mapaOpinioes;
//		mapaOpinioes = new HashMap<String, String>();
//		mapaOpinioes.put("Baixinho Bar e Restaurante", "3");
//		mapaOpinioes.put("Bonaparte", "1");
//		mapaOpinioes.put("Qdoca Bar e Restaurante", "4");
//		PerfilUsuario p = new PerfilUsuario("Julio", mapaOpinioes);
//		System.out.println("ANTES DE ALTERAR " +p.getMapaOpinioes());
//		p.setMapaOpinioes("Baixinho Bar e Restaurante", "5", mapaOpinioes);
//		p.setMapaOpinioes("Bonaparte", "5", mapaOpinioes);
//		p.setMapaOpinioes("Qdoca Bar e Restaurante", "5", mapaOpinioes);
//		System.out.println("DEPOIS DE ALTERAR " +p.getMapaOpinioes());
//		TESTE ESCRITA PERFIL
//		ManipulacaoDeArquivo Manipulacao = new ManipulacaoDeArquivo();
//		String Concatena = p.getNome() + ";" + p.MapaOpinioestoString(Manipulacao);
//		Manipulacao.EscreveOpinioes(Concatena);
		
//		p.setMapaOpinioes("Bonaparte", "5", mapaOpinioes);
//		System.out.println(p.getMapaOpinioes());
//		System.out.println("ANTES DE ALTERAR: " + p.getMapaOpinioes());
//		p.setMapaOpinioes("Qdoca Bar e Restaurante", "10", mapaOpinioes);
//		p.setMapaOpinioes("Bar de Kevin", "3", mapaOpinioes);
//		System.out.println("DEPOIS DE ALTERAR: " + p.getMapaOpinioes());
//		System.out.println(p.getMapaOpinioes());
//		System.out.println("TAMANHO: " + p.getMapaOpinioes().size());
		
//		TESTE GERANDO LIST DE RECOMENDACOES DEFAULT
//		Map<String, String> mapa2;
//		mapa2 = new HashMap<String, String>(); //MAPA VAZIO
//		System.out.println("TAMANHO MAPA2: " + mapa2.size());
//		mapa2.put("RESTAURANTE", "4");
//		PerfilUsuario p2 = new PerfilUsuario("Dinho", mapa2);
//		System.out.println(p2.getMapaOpinioes());
		
//		//NOVO USUARIO
//		Map<String, String> mapa3;
//		mapa3 = new HashMap<String, String>(); //MAPA VAZIO
//		System.out.println("TAMANHO MAPA3: " + mapa3.size());
//		PerfilUsuario p3 = new PerfilUsuario("Andherson", mapa3);
		

//		
//		Concatena = p2.getNome() + ";" + p2.MapaOpinioestoString() + p2.getQtRecomendacoes();
//		leitura.Escreve(Concatena);
//		
//		Concatena = p3.getNome() + ";" + p3.MapaOpinioestoString();
//		Manipulacao.EscreveOpinioes(Concatena);
			

	}
         
}