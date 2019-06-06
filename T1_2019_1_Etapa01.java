import java.io.*;
import java.util.*;
import ProcessaDados.Processador;

/**
 * classe main para primeira parte
 * @author humberto
 *
 */
public class T1_2019_1_Etapa01 {
	
	public static void main(String[] args) throws IOException {
		Locale.setDefault(new Locale("pt", "BR"));
		Scanner entrada = new Scanner(System.in);
		//concatena as strigns para ter o diretorio completo do arquivo
		String caminhoDoArquivoCsv = entrada.nextLine() + entrada.nextLine();
		
		try {
			Processador processador = new Processador(caminhoDoArquivoCsv);
			//Faz as leituras e imprimi oq eh pedido
			processador.preencheListaDePPGs();
			System.out.printf("Instituicoes que publicaram em anais: %d\n", processador.retornaQuantidadeDeInstituicoes());
			System.out.printf("PPGs que publicaram em anais: %d\n", processador.retornaQuantidadeDePpgs());
			System.out.printf("Quantidade de producoes em anais: %d\n", processador.retornaQuantidadeDeProducoes());
			System.out.printf("Quantidade de paginas publicadas em anais: %d\n", processador.retornaQuantidadeDePaginasTotal());
			System.out.printf("Media de paginas por publicacao: %.1f\n", processador.retornaMediaDePaginas());
		}
		
		catch(IOException e) {
			System.out.println("Erro de I/O");
		}
		
		entrada.close();
	}
	
}