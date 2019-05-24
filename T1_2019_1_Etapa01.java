import java.io.*;
import java.util.*;

public class T1_2019_1_Etapa01 {
	
	//br-colsucup-prod-detalhe-bibliografica-2017-2019-02-01-anais
	public static void main(String[] args) throws IOException {
		
		Locale.setDefault(new Locale("pt", "BR"));
		Scanner entrada = new Scanner(System.in);
		String caminhoFixo = entrada.nextLine();
		String caminhoDoArquivoCsv = caminhoFixo + entrada.nextLine();
		
		try {
			Processador processador = new Processador(caminhoDoArquivoCsv);
			for(int i = 0; i < 7; i++) {
				processador.preencheListaDePPGs();
				if(i < 6) {
					caminhoDoArquivoCsv = caminhoFixo + entrada.nextLine();
					processador.mudaEnderecoDeEntrada(caminhoDoArquivoCsv);
				}
			}
			
			if(entrada.nextLine().equals("rede")) {
				processador.executaComandoRede();
			}
		}
		
		catch(IOException e) {
			System.out.println("Erro de I/O");
		}
		
		entrada.close();
	}
	
}
