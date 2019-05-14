import java.io.*;

public class TrabalhoJava {
	//br-colsucup-prod-detalhe-bibliografica-2017-2019-02-01-anais
	public static void main(String[] args) throws IOException {
		String caminhoDoArquivoCsv = "/home/humberto/Downloads/br-colsucup-prod-detalhe-bibliografica-2017-2019-02-01-anais.csv";
		try {
			Processador processador = new Processador(caminhoDoArquivoCsv);
			processador.preencheCabecalho();
			processador.preencheListaDePPGs();
			System.out.println("Instituicoes que publicaram em anais: " + processador.retornaQuantidadeDeAnais());
			System.out.println("PPGs que publicaram em anais: " + processador.retornaQuantidadeDePPGs());
			
		}
		
		catch(IOException e) {
			System.out.println("Erro de I/0");
		}
	}
	
}
