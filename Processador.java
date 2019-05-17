import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Processador {
	private LeitorDeCsv leitorDoArqivo;
	private List<PPG> listaDePPGs = new ArrayList<PPG>();
	private String[] cabecalho;

	public Processador(String caminho) throws IOException {
		this.leitorDoArqivo = new LeitorDeCsv(caminho);
	}
	
	public void preencheCabecalho() throws IOException {
		this.cabecalho = this.leitorDoArqivo.lerCabecalhoCsv();
	}
	
	private String[] retornaDadosPadrao() {
		return null;
	}
	
	public void preencheListaDePPGs() throws IOException {
		String[] linha = this.leitorDoArqivo.lerLinhaDoCsv();
		while(linha != null) {			
			
			Producao producaoAtual = null;
			Instituicao instituicaoAtual = null;
			
			String codigoPPG = this.leitorDoArqivo.getColuna(0);
			String sigla = this.leitorDoArqivo.getColuna(2);
			String nomeFaculdade = this.leitorDoArqivo.getColuna(3);
			int idSubTipo = Integer.parseInt(this.leitorDoArqivo.getColuna(7));
			
			String titulo = this.leitorDoArqivo.getColuna(9);
			String natureza = this.leitorDoArqivo.getColuna(8);
			String idioma = this.leitorDoArqivo.getColuna(18);
			String cidade = this.leitorDoArqivo.getColuna(16);
			String paginaInicial = this.leitorDoArqivo.getColuna(14);
			String paginaFinal = this.leitorDoArqivo.getColuna(13);
			
			
			if(idSubTipo == 8) {
				String evento = this.leitorDoArqivo.getColuna(15);
				producaoAtual = new Anais(titulo, natureza, idioma, cidade, paginaInicial, paginaFinal, evento);
				instituicaoAtual = new Instituicao(nomeFaculdade, sigla);
				this.listaDePPGs.add(new PPG(codigoPPG, instituicaoAtual, producaoAtual));				
			}
			
			linha = this.leitorDoArqivo.lerLinhaDoCsv();
		}
	}
}
	