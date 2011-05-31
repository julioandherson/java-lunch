package pitaqueiro;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Projeto LP2
 * Autores Lucas Albuquerque, Julio Andherson, Kevin Viera
 */

public class LeituraDeArquivo {
	private List<Estabelecimento> listaEstabelecimento;
	private Map<String, String> mapaOpinioes;
	private List<String> listaRestaurantes;
	private List<Opinioes> listaOpinioes;
	private List<String> listaElementosPerfil;
	
	private List<Integer> listaNotas;
        
        /*
         * Construtor de LeiraDeArquivo()
         * Inicializa as colecoes que vao ser usadas no programa
         */
	
	public LeituraDeArquivo(){
		listaEstabelecimento = new ArrayList<Estabelecimento>();
		mapaOpinioes = new HashMap<String, String>();
		listaRestaurantes = new ArrayList<String>();
		listaOpinioes = new ArrayList<Opinioes>();
		listaElementosPerfil = new ArrayList<String>();
		
		listaNotas = new ArrayList<Integer>();
	}
	
        /*
         * Le e guarda em uma List<String> os estabelecimentos que estao no arquivo
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
	
        /*
         * Le e guarda em uma List<String> as opinioes od usuarios que estao no arquivo
         */
	public void leOpinioes() throws IOException{
		String diretorioOpinioes = "C:/Users/Lucas/Documents/NetBeansProjects/Pitaqueiro/src/Arquivos/opinioes-dos-usuarios-v1.csv";
		BufferedReader leituraOpinioes = null;
		final int QUANTIDADE_RESTAURANTES = 39;
		int Somador = 0;
		try {
			leituraOpinioes = new BufferedReader(new FileReader(diretorioOpinioes));
			String[] Linha;
			while (leituraOpinioes.ready()) {
		    	Linha = leituraOpinioes.readLine().split(";");
		    	int indiceArray; //Array de Restaurantes
		    	for(int indiceLinha = 2; indiceLinha < QUANTIDADE_RESTAURANTES; indiceLinha ++){
		    		indiceArray = indiceLinha - 2;
		    		mapaOpinioes.put(listaRestaurantes.get(indiceArray), Linha[indiceLinha]);
		    	}
		    	Opinioes opiniao = new Opinioes(Linha[0], Linha[1], mapaOpinioes);
		    	
//		    	Object[] listaValores = opiniao.getMapaOpinioes().values().toArray();
//		    	String[] Nota = listaValores[0].toString().split(" ");
//		    	System.out.println(Nota[0].replace(":", ""));
//		    	Somador += Integer.parseInt(Nota[0].replace(":", ""));
//		    	System.out.println("NOME: " + opiniao.getNome() + Integer.parseInt(Nota[0].replace(":", "")) + "    " + Somador);
		    	
//		    	System.out.println("NOME: " + opiniao.getNome() + " <> " + "OPINIOES: " + opiniao.getMapaOpinioes());
		    	//USAR O HASHCODE
		    	listaOpinioes.add(opiniao); //Talvez tirar
		    }
		   
		}catch (Exception erro) {
			System.err.println(erro.getMessage());
		    }
		finally{
			leituraOpinioes.close();
		}	
	}
        
        /*
         * Le e guarda em uma List<String> os usuarios que estao no arquivo
         */
	public void lePerfis() throws IOException{
		String diretorioPerfis = "C:/Users/Lucas/Documents/NetBeansProjects/Pitaqueiro/src/Arquivos/Perfis.txt";
		BufferedReader leituraPerfil = null;
		try {
			leituraPerfil = new BufferedReader(new FileReader(diretorioPerfis));
//			String[] Linha;		
//			while (leituraPerfil.ready()) {
//		    	Linha = leituraPerfil.readLine().split(";");
//		    	for(int indice = 0; indice < Linha.length; indice ++){
//		    		listaElementosPerfil.add(Linha[indice]);
//		    	}
//		    }
			
			String Linha;		
			while (leituraPerfil.ready()) {
		    	Linha = leituraPerfil.readLine();
		    	listaElementosPerfil.add(Linha);
		    	
		    }
		}catch (Exception erro) {
			System.err.println(erro.getMessage());
		}
		finally{
			leituraPerfil.close();
		}
	}
	
        /*
         * 
         */
	public void Escreve(String Perfil) throws IOException{
		String Diretorio = "C:/Users/Lucas/Documents/NetBeansProjects/Pitaqueiro/src/Arquivos/Perfis.txt";
		BufferedWriter out = null;
		String novaLinha = System.getProperty("line.separator"); 
		try {
			out = new BufferedWriter(new FileWriter(Diretorio, true));
			out.write(Perfil + novaLinha);
			listaElementosPerfil.add(Perfil);
		}catch(IOException erro){
			System.err.println(erro.getMessage());
		}
		finally{
			System.out.println("Acabei de escrever no arquivo");
			out.close();
		}
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
        
        public static void main(String args[]) throws IOException{
            
            LeituraDeArquivo l = new LeituraDeArquivo();
            l.leEstabelecimentos();

        }
	
}
