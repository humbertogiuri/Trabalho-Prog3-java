package LeitorCsv;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * classe LeitorDeCsv que contem uma string um BufferedReader e um vetor de String.
 * Essa classe e responsavel por abrir o arquivo e ler as linhas
 * @author humberto
 *
 */

public class LeitorDeCsv {

	private String CaminhoProCsv; //string que representa o diretorio do arquivo
	private BufferedReader leitorDeCsv; 
	private String[] linhaAtualLida;
	
	/**
	 * construtor da classe
	 * @param caminho
	 * @throws FileNotFoundException
	 */
	public LeitorDeCsv(String caminho) throws FileNotFoundException {
		this.CaminhoProCsv = caminho;
		this.leitorDeCsv = new BufferedReader(new FileReader(caminho)); 
	}
	
	/**
	 * Funcao que faz a leitura de um novo diretorio que leva ate o proximo arquivo
	 * @param enderecoNovo
	 * @throws IOException
	 */
	public void lerNovoEnderecoDeEntrada(String enderecoNovo) throws IOException{
		this.leitorDeCsv = new BufferedReader(new FileReader(enderecoNovo));
	}
	
	/**
	 * Funcao que tira os espacos da linha
	 */
	private void formatarLinha() {
        for(int i = 0; i < linhaAtualLida.length; i++) {
            linhaAtualLida[i] = linhaAtualLida[i].trim();
        }
    }
	
	/**
	 * Funcao que faz a leitura do cabecalho
	 * @return um vetor de string que guarda o cabecalho
	 * @throws IOException
	 */
	public String[] lerCabecalhoCsv() throws IOException {
		String linhaDoArquivo = this.leitorDeCsv.readLine();
		this.linhaAtualLida = linhaDoArquivo.split
				(";(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
		
		this.formatarLinha();
		return linhaAtualLida;
		
	}
	
	/**
	 * Funcao que uma linha completa do arquivo csv
	 * @return a linha formatada caso exista ou null se nao houver a leitura
	 * @throws IOException
	 */
	public String[] lerLinhaDoCsv() throws IOException {
		String linhaDoArquivo = this.leitorDeCsv.readLine();
		
		if(linhaDoArquivo != null) {
			//caso nao seja nulo faz o split na linha do arquivo
			this.linhaAtualLida = linhaDoArquivo.split
					(";(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
			
			this.formatarLinha();
			return linhaAtualLida;
		}
		
		else {
			return null;
		}
	}
	
	/**
	 * Funcao que analisa a coluna do arquivo
	 * @param indice
	 * @return String que define o conteudo linha na posicao do vetor que defini linha
	 */
	public String getColuna(int indice) {
		return this.linhaAtualLida[indice];
	}
}
