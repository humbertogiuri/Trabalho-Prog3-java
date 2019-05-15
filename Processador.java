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
	
	public void preencheListaDePPGs() throws IOException {
		String[] linha = this.leitorDoArqivo.lerLinhaDoCsv();
		while(linha != null) {			
			
			String codigoPPG = this.leitorDoArqivo.getColuna(0);
			String sigla = this.leitorDoArqivo.getColuna(2);
			String nomeFaculdade = this.leitorDoArqivo.getColuna(3);
			int idSubTipo = Integer.parseInt(this.leitorDoArqivo.getColuna(7));
			this.listaDePPGs.add(new PPG(codigoPPG, sigla, nomeFaculdade, idSubTipo));
			
			String titulo = this.leitorDoArqivo.getColuna(9);
			String natureza = this.leitorDoArqivo.getColuna(8);
			String idioma = this.leitorDoArqivo.getColuna(18);
			String cidade = this.leitorDoArqivo.getColuna(16);
			String paginaInicial = this.leitorDoArqivo.getColuna(14);
			String paginaFinal = this.leitorDoArqivo.getColuna(13);
			
			if(idSubTipo == 8) {
				String evento = this.leitorDoArqivo.getColuna(15);
				this.listaDePPGs.                      
			}
			
			linha = this.leitorDoArqivo.lerLinhaDoCsv();
		}
	}
	
	
	public int retornaQuantidadeDePPGs() {
		List<String> listaDePPGs = new ArrayList<String>();
		
		for(int i = 0; i < this.listaDePPGs.size(); i ++) {
			String PPGAtual = this.listaDePPGs.get(i).getCodigo();
			if(!listaDePPGs.contains(PPGAtual)) {
				listaDePPGs.add(PPGAtual);
			}
		}
		int numeroDePPGs =  listaDePPGs.size();
		listaDePPGs = null;
		return numeroDePPGs;
	}
	
	public int retornaQuantidadeDeAnais() {
		List<String> listaDeAnais = new ArrayList<String>();
		
		for(int i = 0; i < this.listaDePPGs.size(); i ++) {
			String analAtual = this.listaDePPGs.get(i).getSigladaInstituicao();
			if(!listaDeAnais.contains(analAtual)) {
				listaDeAnais.add(analAtual);
			}
		}
		int numeroDeAnais =  listaDeAnais.size();
		listaDeAnais = null;
		return numeroDeAnais;
	}
}
