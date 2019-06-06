import java.io.*;
import java.security.InvalidParameterException;
import java.util.*;

import ProcessaDados.Processador;

/**
 * classe main para parte 3
 * @author humberto
 *
 */
public class T1_2019_1_Etapa03 {

	public static void main(String[] args) throws IOException {

		Locale.setDefault(new Locale("pt", "BR"));
		Scanner entrada = new Scanner(System.in);
		String caminhoFixo = entrada.nextLine();
		//concatena as strigns para ter o diretorio completo do arquivo
		String caminhoDoArquivoCsv = caminhoFixo + entrada.nextLine();

		try {
			Processador processador = new Processador(caminhoDoArquivoCsv);
			for (int i = 0; i < 7; i++) {
				//faz as leituras dos arquivos
				processador.preencheListaDePPGs();
				if (i < 6) { //se i < 6 ainda tem arquivo pra ler
					//troca o diretorio que leva pro arquivo
					caminhoDoArquivoCsv = caminhoFixo + entrada.nextLine();
					processador.mudaEnderecoDeEntrada(caminhoDoArquivoCsv);
				}
			}
			
			String comando = entrada.next();
			//verifica qual comando foi pedido
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
