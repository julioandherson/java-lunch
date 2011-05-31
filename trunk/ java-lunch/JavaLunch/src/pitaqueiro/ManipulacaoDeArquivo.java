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
/**
 * 
 * @author Julio, Lucas, Kevin
 *
 */
public class ManipulacaoDeArquivo {
	private List<Estabelecimento> listaEstabelecimento;
	private List<String> listaRestaurantes;
	private List<Opinioes> listaOpinioes;
	private List<Integer> listaNotas;
	private Map<Integer, List<String>> mapaRanking;
	private List<String> listaRestaurantePopular;
	private List<PerfilUsuario> listaPerfilUsuarioSistema;
	private List<Integer> listaProdutoEscalar;
	
	/**
	 * Construtor de ManipulacaoDeArquivo, que inicializa as colecoes
	 */
	public ManipulacaoDeArquivo(){
		listaEstabelecimento = new ArrayList<Estabelecimento>();
		listaRestaurantes = new ArrayList<String>();
		listaOpinioes = new ArrayList<Opinioes>();
		listaNotas = new ArrayList<Integer>(); //NOTAS EQUIVALENTE AO INDICE DA LISTA DE RESTAURANTE
		mapaRanking = new HashMap<Integer, List<String>>();
		listaPerfilUsuarioSistema = new ArrayList<PerfilUsuario>();
		listaRestaurantePopular = new ArrayList<String>();
		listaProdutoEscalar = new ArrayList<Integer>();
	}
	/**
	 * Metodo que le os Estabelecimentos, gera uma lista do tipo String com todos os restaurantes e gera uma lista do tipo Estabelecimento com todos os estabelecimento
	 * @throws IOException se nao conseguir le o arquivo corretamente
	 */
	public void leEstabelecimentos() throws IOException{
		String diretorioRestaurantes = "C:/Users/Lucas/Documents/NetBeansProjects/Pitaqueiro/src/Arquivos/lista_estabelecimentos_projeto_lp2.csv";
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
	
	/**
	 * Metodo que le as opinioes dos usuarios, gera uma lista do tipo Opinioes e adiciona todos os usuarios do arquivo em uma lista do tipo PerfilUsuario
	 * @throws IOException se nao conseguir le o arquivo corretamente
	 */
	public void leOpinioes() throws IOException{
		String diretorioOpinioes = "C:/Users/Lucas/Documents/NetBeansProjects/Pitaqueiro/src/Arquivos/opinioes-dos-usuarios-v1.csv";
		BufferedReader leituraOpinioes = null;
		int QUANTIDADE = 40; //ESSA QT VAI PODER MUDAR
		try {
			leituraOpinioes = new BufferedReader(new FileReader(diretorioOpinioes));
			String[] Linha;
			while (leituraOpinioes.ready()) {
		    	Linha = leituraOpinioes.readLine().split(";");
		    	int indiceArray; //Array de Restaurantes
		    	Map<String, String> mapaOpinioes = new HashMap<String, String>(); //Sempre tem que ser reinicializado
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
	/**
	 * Escreve no Arquivo
	 * @param Perfil uma String concatenada dessa forma NOMEUSUARIO;MAPAOPINIOES
	 * @throws IOException se nao conseguir le ou escrever no arquivo corretamente 
	 */
	public void EscreveOpinioes(String Perfil) throws IOException{
		String diretorioOpinioes = "C:/Users/Lucas/Documents/NetBeansProjects/Pitaqueiro/src/Arquivos/opinioes-dos-usuarios-v1.csv";
		BufferedWriter out = null;
		String novaLinha = System.getProperty("line.separator"); 
		try {
			out = new BufferedWriter(new FileWriter(diretorioOpinioes, true));
			out.write(getDataHora() + ";" + Perfil + novaLinha);
		}catch(IOException erro){
			System.err.println(erro.getMessage());
		}
		finally{
			out.close();
		}
	}
	/**
	 * Vai preencher a listaRestaurantePopular com uma String "RESTAURANTE NOTA", em ordem crescente de popularidade
	 */
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
	
	/**
	 * Calcula produtoEscalar de todos os usuarios que estao no arquivo, e adiciona esse produto aos seus Atributos
	 * @param perfil a ser comparado com os demais
	 * @param Manipulacao usado para acessar os metodos da classe
	 * @throws IOException se ocorrer algum erro na leitura
	 */
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
	
	/**
	 * Vai preencher a lista RestaurantesRecomendados com os restaurantes que sao recomendados por um usuario mais semelhante ao usuario a ser comparado
	 * @param perfil usuario a ser comparado com os demais
	 * @param Manipulacao usado para acessar os metodos da classe
	 * @return a lista com os Restaurantes mais Recomendados em ordem decrescente
	 * @throws IOException se ocorrer algum erro na leitura
	 */
	public List<String> getRecomendacoesRestaurantes(PerfilUsuario perfil, ManipulacaoDeArquivo Manipulacao) throws IOException{
		List<String> RestaurantesRecomendados = new ArrayList<String>();
		
		calculaProdutoEscalar(perfil, Manipulacao);
	
		Collections.sort(listaProdutoEscalar);
		Collections.reverse(listaProdutoEscalar);

		for (int i = 0; i < listaProdutoEscalar.size(); i++) {
			for(PerfilUsuario perfilSistema : getListaPerfilUsuarioSistema()){
				if(listaProdutoEscalar.get(i) == perfilSistema.getSemelhanca()){
					for (int j = 0; j < perfil.getListaNotasUsuario().size(); j++) {
                                            if(perfilSistema.getListaNotasUsuario().size() == 0){
                                                perfilSistema.GeraListNotasUsuario(Manipulacao);
                                            }
                                            if(perfil.getListaNotasUsuario().size() == 0){
                                                perfil.GeraListNotasUsuario(Manipulacao);
                                            }
                                            
                                            
                                            
                                            
                                            System.out.println(perfilSistema.getListaNotasUsuario().size()+" "+perfil.getListaNotasUsuario().size()+ " "+j);
						if(perfilSistema.getListaNotasUsuario().get(j) > perfil.getListaNotasUsuario().get(j) && perfil.getListaNotasUsuario().get(j) == 0){

							if(!(RestaurantesRecomendados.contains(listaRestaurantes.get(j)))){
								RestaurantesRecomendados.add(listaRestaurantes.get(j));
							}
                                                }
						
					}
				}
			}
		}
		return RestaurantesRecomendados;
	}
	
	/**
	 * Gera a data e a hora atual do sistema
	 * @return String com data e hora
	 */
	private String getDataHora(){
		String DataHora;
		Date date = new Date();
	    SimpleDateFormat sdf;
	    sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	    DataHora = sdf.format(date);
	    return DataHora;
	}
	
	/**
	 * Recupera a lista do tipo Estabelecimento (So pode ser chamado depois que chamar o metodo leEstabelecimento)
	 * @return listaEstabelecimento
	 */
	public List<Estabelecimento> getListaEstabelecimento() {
		return listaEstabelecimento;
	}
	
	/**
	 * Recupera lista de String com os restaurantes(so pode ser chamado depois que chamar o metodo leEstabelecimento)
	 * @return listaRestaurantes
	 */
	public List<String> getListaRestaurantes() {
		return listaRestaurantes;
	}
	
	/**
	 * Recupera a lista do tipo Opinioes (So pode ser chamado depois que chamar leEstabelecimento e depois leOpinioes)
	 * @return listaOpinioes
	 */
	public List<Opinioes> getListaOpinioes() {
		return listaOpinioes;
	}
	
	/**
	 * Lista do tipo PerfilUsuario, onde os usuarios lido do arquivo sao colocados(so pode ser chamado apoes chamar o metodo leEstabelecimentos e leOpinioes)
	 * @return listaPerfilUsuarioSistema
	 */
	public List<PerfilUsuario> getListaPerfilUsuarioSistema(){
		return listaPerfilUsuarioSistema;
	}
	
	/**
	 * Recupera o a lista de String dos Restaurantes mais populares
	 * @return listaRestaurantePopular
	 */
	public List<String> getListaRestaurantePopular() {
		return listaRestaurantePopular;
	}
	
}
