import java.io.*;
import java.security.InvalidParameterException;
import java.util.*;

public class T1_2019_1_Etapa04 {

	public static void main(String[] args) throws IOException {

		Locale.setDefault(new Locale("pt", "BR"));
		Scanner entrada = new Scanner(System.in);
		String caminhoFixo = entrada.nextLine();
		String caminhoDoArquivoCsv = caminhoFixo + entrada.nextLine();

		try {
			Processador processador = new Processador(caminhoDoArquivoCsv);
			for (int i = 0; i < 7; i++) {
				processador.preencheListaDePPGs();
				if (i < 6) {
					caminhoDoArquivoCsv = caminhoFixo + entrada.nextLine();
					processador.mudaEnderecoDeEntrada(caminhoDoArquivoCsv);
				}
			}
			
			String comando = entrada.next();
			if (comando.equals("rede")) {
				processador.executaComandoRede();
			}
			
			else if(comando.contentEquals("ppg")) {
				processador.exexutaComandoPpg(entrada.next());
			}
			
			else if(comando.equals("ies")) {
				processador.executaComandoIes(entrada.next());
			}
			
			else if(comando.equals("csv")) {
				processador.executaComandoCsv(entrada.next(), entrada.next());
			}
			
			else {
				System.out.println("Comando desconhecido.");
			}
		}

		catch (IOException e) {
			System.out.println("Erro de I/O");
		}
		
		catch (InvalidParameterException e) {
			System.out.println(e.getMessage());
		}

		entrada.close();
	}

}
