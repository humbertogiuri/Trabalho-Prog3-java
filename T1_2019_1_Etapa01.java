import java.io.*;
import java.util.*;

public class T1_2019_1_Etapa01 {
	//br-colsucup-prod-detalhe-bibliografica-2017-2019-02-01-anais
	public static void main(String[] args) throws IOException {
		Locale.setDefault(new Locale("pt", "BR"));
		Scanner entrada = new Scanner(System.in);
		String caminhoDoArquivoCsv = entrada.nextLine() + entrada.nextLine();
		
		//String caminhoDoArquivoCsv = "/home/humberto/Downloads/br-colsucup-prod-detalhe-bibliografica-2017-2019-02-01-anais.csv";
		try {
			Processador processador = new Processador(caminhoDoArquivoCsv);
			processador.preencheListaDePPGs();
			System.out.printf("Instituicoes que publicaram em anais: %d\n", processador.retornaQuantidadeDeInstituicoesPublicaramEmAnais());
			System.out.printf("PPGs que publicaram em anais: %d\n", processador.retornaQuantidadeDePPGs());
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
