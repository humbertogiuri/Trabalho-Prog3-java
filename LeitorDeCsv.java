import java.io.*;
import java.util.ArrayList;
import java.util.List;


//Essa classe tem a funcao de abrir o arquivo csv e salvar os seu dados para serem usados depois

public class LeitorDeCsv {
	private String CaminhoProCsv;
	private BufferedReader leitorDeCsv;
	private String[] linhaAtualLida;
	
	public LeitorDeCsv(String caminho) throws FileNotFoundException {
		this.CaminhoProCsv = caminho;
		this.leitorDeCsv = new BufferedReader(new FileReader(caminho)); 
	}
	
	private void formatarLinha() {
        for(int i = 0; i < linhaAtualLida.length; i++) {
            linhaAtualLida[i] = linhaAtualLida[i].trim();
        }
    }
	
	public String[] lerCabecalhoCsv() throws IOException {
		String linhaDoArquivo = this.leitorDeCsv.readLine();
		this.linhaAtualLida = linhaDoArquivo.split
				(";(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
		
		this.formatarLinha();
		return linhaAtualLida;
		
	}
	
	public String[] lerLinhaDoCsv() throws IOException {
		String linhaDoArquivo = this.leitorDeCsv.readLine();
		
		if(linhaDoArquivo != null) {
			this.linhaAtualLida = linhaDoArquivo.split
					(";(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
			
			this.formatarLinha();
			return linhaAtualLida;
		}
		
		else {
			return null;
		}
	}

	public String getColuna(int indice) {
		return this.linhaAtualLida[indice];
	}
}
