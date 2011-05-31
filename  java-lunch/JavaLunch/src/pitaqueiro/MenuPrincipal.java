package pitaqueiro;

public class MenuPrincipal{
		
	public static void main(String args[]) throws Exception{
		ManipulacaoDeArquivo Manipulacao = new ManipulacaoDeArquivo();
		Manipulacao.leEstabelecimentos();
		Manipulacao.leOpinioes();
	
		for(PerfilUsuario perfil : Manipulacao.getListaPerfilUsuarioSistema()){
			if(perfil.getNome().equals("Jeymisson Oliveira")){
				System.out.println(perfil.getNome() + " " + Manipulacao.getRecomendacoesRestaurantes(perfil, Manipulacao));
			}
		}
		
//		Manipulacao.geraRankingRestaurantes();
//		for(String e : Manipulacao.getListaRestaurantePopular()){
//			System.out.println(e);
//		}
		
	}
}