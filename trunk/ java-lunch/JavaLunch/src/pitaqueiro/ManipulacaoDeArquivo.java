package pitaqueiro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManipulacaoDeArquivo {
	private List<Estabelecimento> listaEstabelecimento;
	private Map<String, String> mapaOpinioes;
	private List<String> listaRestaurantes;
	private List<Opinioes> listaOpinioes;
	private List<String> listaElementosPerfil;
	private List<Integer> listaNotas;
	private Map<Integer, List<String>> mapaRanking;
	private List<String> listaRestaurantePopular;
	private List<PerfilUsuario> listaPerfilUsuarioSistema;
	public List<Integer> listaProdutoEscalar;
	
	public ManipulacaoDeArquivo(){
		listaEstabelecimento = new ArrayList<Estabelecimento>();
		mapaOpinioes = new HashMap<String, String>();
		listaRestaurantes = new ArrayList<String>();
		listaOpinioes = new ArrayList<Opinioes>();
		listaElementosPerfil = new ArrayList<String>();
		listaNotas = new ArrayList<Integer>(); //NOTAS EQUIVALENTE AO INDICE DA LISTA DE RESTAURANTE
		mapaRanking = new HashMap<Integer, List<String>>();
		listaPerfilUsuarioSistema = new ArrayList<PerfilUsuario>();
		listaRestaurantePopular = new ArrayList<String>();
		listaProdutoEscalar = new ArrayList<Integer>();
	}
	
	public void leEstabelecimentos() throws IOException{
		String diretorioRestaurantes = "C:/Users/Lucas/workspace/JavaLunch/src/Arquivos/lista_estabelecimentos_projeto_lp2.csv";
		BufferedReader leituraRestaurantes = null;
		try {
			leituraRestaurantes = new BufferedReader(new FileReader(diretorioRestaurantes));
			String[] Linha;		
			while (leituraRestaurantes.ready()) {
		    	Linha = leituraRestaurantes.readLine().split(";");
		    	listaRestaurantes.add(Linha[0]);//SERVE PARA MAPEAR OS RESTAURANTES COM OPINIOES
		    	Estabelecimento estabelecimento = new Estabelecimento(Linha[0], Linha[1], Linha[2]);
		    	listaEstabelecimento.add(estabelecimento);
		    }
		}catch (Exception erro) {
			System.err.println(erro.getMessage());
		}
		finally{
			leituraRestaurantes.close();
		}
	}
	
	public void leOpinioes() throws IOException{
		String diretorioOpinioes =  "C:/Users/Lucas/workspace/JavaLunch/src/Arquivos/opinioes-dos-usuarios-v1.csv";
		BufferedReader leituraOpinioes = null;
		int QUANTIDADE = 40; //ESSA QT VAI PODER MUDAR
		try {
			leituraOpinioes = new BufferedReader(new FileReader(diretorioOpinioes));
			String[] Linha;
			while (leituraOpinioes.ready()) {
		    	Linha = leituraOpinioes.readLine().split(";");
		    	int indiceArray; //Array de Restaurantes
		    	mapaOpinioes = new HashMap<String, String>(); //Sempre tem que ser reinicializado
		    	for(int indiceLinha = 2; indiceLinha <= QUANTIDADE; indiceLinha ++){
		    		indiceArray = indiceLinha - 2;	    		
		    		mapaOpinioes.put(listaRestaurantes.get(indiceArray), Linha[indiceLinha]); //MAPEIA RESTAURANTE COM SUA RESPECTIVA NOTA
		    	}
		    	Opinioes opiniao = new Opinioes(Linha[0], Linha[1], mapaOpinioes);
		    	listaOpinioes.add(opiniao);
		    	PerfilUsuario perfil = new PerfilUsuario(opiniao.getNome(), mapaOpinioes);
		    	listaPerfilUsuarioSistema.add(perfil);
		    }
			
		}catch (Exception erro) {
			System.err.println(erro.getMessage());
		    }
		finally{
			leituraOpinioes.close();
		}	
	}
	
	public void EscreveOpinioes(String Perfil) throws IOException{
		String diretorioOpinioes = "C:/Users/Lucas/workspace/JavaLunch/src/Arquivos/opinioes-dos-usuarios-v1.csv";
		BufferedWriter out = null;
		String novaLinha = System.getProperty("line.separator"); 
		try {
			out = new BufferedWriter(new FileWriter(diretorioOpinioes, true));
			out.write(getDataHora() + ";" + Perfil + novaLinha);
			listaElementosPerfil.add(Perfil);
		}catch(IOException erro){
			System.err.println(erro.getMessage());
		}
		finally{
			out.close();
		}
	}
	
	public void geraRankingRestaurantes(){
		int Somador = 0;
		String[] Nota = null;
		List<String> ArrayRestauranteAuxiliar = new ArrayList<String>();
		List<String> ArrayPopularidadeAuxiliar = new ArrayList<String>();
		List<String> ArrayComRepeticoes = new ArrayList<String>();
		
		for(String Restaurante : listaRestaurantes){
			Somador = 0;
			for(Opinioes opiniao : listaOpinioes){ //ESSE FOR SOMA TODAS AS OPINIOES DE UM DETERMINADO REST
				if(opiniao.getMapaOpinioes().containsKey(Restaurante)){
					Nota = opiniao.getMapaOpinioes().get(Restaurante).split(":");
					Somador += Integer.parseInt(Nota[0].trim());
				}
			}
				if(!(mapaRanking.containsKey(Somador)) && !(ArrayRestauranteAuxiliar.contains(Restaurante))){
					ArrayRestauranteAuxiliar.add(Restaurante);
					mapaRanking.put(Somador, ArrayRestauranteAuxiliar);	
				}else{
					mapaRanking.get(Somador).add(Restaurante);
				}
				ArrayRestauranteAuxiliar = new ArrayList<String>(); //Reinicializa o Array
				listaNotas.add(Somador);
		}
		Collections.sort(listaNotas);
		Collections.reverse(listaNotas);		
		
		for(int i = 0; i < listaNotas.size(); i++){
			ArrayPopularidadeAuxiliar.add((mapaRanking.get(listaNotas.get(i)) + ";" + listaNotas.get(i)));
		}
		
		for (int i = 0;  i < ArrayPopularidadeAuxiliar.size(); i++){
			String[] listaElemento = ArrayPopularidadeAuxiliar.get(i).replace("[", "").replace("]", "").split(";");
			listaElemento = listaElemento[0].split(",");
			if(listaElemento.length > 1){
				for(int j = 0; j < listaElemento.length; j++){ //FOR NOS ARRAY DE REST COM MESMA NOTA
					ArrayComRepeticoes.add(listaElemento[j].trim() + ";" + listaNotas.get(i));				
				}
			}else{
				ArrayComRepeticoes.add(listaElemento[0].trim() + ";" + listaNotas.get(i));
			}
		}
	
		for(String Restaurante : ArrayComRepeticoes){
			if(!(listaRestaurantePopular.contains(Restaurante))){
				listaRestaurantePopular.add(Restaurante);
			}
		}
	}
	
	public void calculaProdutoEscalar(PerfilUsuario perfil, ManipulacaoDeArquivo Manipulacao) throws IOException{
		int SomaProduto;
		int Produto;

		perfil.GeraListNotasUsuario(Manipulacao);
		for (int i = 0; i < getListaPerfilUsuarioSistema().size(); i++) {
			if (!perfil.getNome().toLowerCase().equals(getListaPerfilUsuarioSistema().get(i).getNome().toLowerCase())){ //Vai comparar com todos os usuarios exceto ele mesmo
				SomaProduto = 0;
				Produto = 0;
				for (int j = 0; j < getListaRestaurantes().size(); j++) {
					getListaPerfilUsuarioSistema().get(i).GeraListNotasUsuario(Manipulacao);
					Produto = perfil.getListaNotasUsuario().get(j) * getListaPerfilUsuarioSistema().get(i).getListaNotasUsuario().get(j);
					SomaProduto += Produto;
				}
				getListaPerfilUsuarioSistema().get(i).setSemelhanca(SomaProduto);
				listaProdutoEscalar.add(SomaProduto);
			}
		}
	}
	
	public List<String> getRecomendacoesRestaurantes(PerfilUsuario perfil, ManipulacaoDeArquivo Manipulacao) throws IOException{
		List<Integer> listaNotas = new ArrayList<Integer>();
		List<String> RestaurantesRecomendados = new ArrayList<String>();
		
		calculaProdutoEscalar(perfil, Manipulacao);
		
		Collections.sort(listaProdutoEscalar);
		Collections.reverse(listaProdutoEscalar);
		
		for (int i = 0; i < listaProdutoEscalar.size(); i++) {
			for(PerfilUsuario perfilSistema : getListaPerfilUsuarioSistema()){
				listaNotas.removeAll(listaNotas);
				if(listaProdutoEscalar.get(i) == perfilSistema.getSemelhanca()){ 			//verificar o perfil semelhante
					for (int j = 0; j < perfil.getListaNotasUsuario().size(); j++) {
						//TEM QUE BOTAR O GERAR NOTAS AQUI
//						Manipulacao.getListaRestaurantes().get(j).setConceito(perf.getListaNotasUsuario().get(j));
						listaNotas.add(perfilSistema.getListaNotasUsuario().get(j));	
					}
					
					Collections.sort(listaNotas);
					Collections.reverse(listaNotas);
					
					for (int j = 0; j < listaNotas.size(); j++) {
						if (listaNotas.get(j) > 0){
							for (int j2 = 0; j2 < getListaRestaurantes().size(); j2++) {
//								if (notas.get(j) == Manipulacao.getListaRestaurantes().get(j2).getConceito()){
									if (perfil.getListaNotasUsuario().get(j2) == 0){
										if (!RestaurantesRecomendados.contains(getListaRestaurantes().get(j2))){
											RestaurantesRecomendados.add(getListaRestaurantes().get(j2));
										}
									}
								}
							}
						}
					}
				}
			}
                        
			return RestaurantesRecomendados;
		}
	
	private String getDataHora(){
		String DataHora;
		Date date = new Date();
	    SimpleDateFormat sdf;
	    sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	    DataHora = sdf.format(date);
	    return DataHora;
	}

	public List<Estabelecimento> getListaEstabelecimento() {
		return listaEstabelecimento;
	}

	public Map<String, String> getMapaOpinioes() {
		return mapaOpinioes;
	}

	public List<String> getListaRestaurantes() {
		return listaRestaurantes;
	}

	public List<Opinioes> getListaOpinioes() {
		return listaOpinioes;
	}

	public List<String> getListaElementosPerfil(){
		return listaElementosPerfil;
	}
	
	public List<PerfilUsuario> getListaPerfilUsuarioSistema(){
		return listaPerfilUsuarioSistema;
	}

	public List<String> getListaRestaurantePopular() {
		return listaRestaurantePopular;
	}
	
}
