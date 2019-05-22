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
	
	private int retornaIndiceDaStringNoCabecalho(String entrada) {
		for(int i = 0; i < this.cabecalho.length; i++) {
			if(this.cabecalho[i].equals(entrada)) {
				return i;
			}
		}
		return -1;
	}
	
	public void preencheListaDePPGs() throws IOException {
		this.preencheCabecalho();
		String[] linha = this.leitorDoArqivo.lerLinhaDoCsv();
		while(linha != null) {			
			
			Producao producaoAtual = null;
			Instituicao instituicaoAtual = null;
			
			String codigoPPG = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("CD_PROGRAMA_IES"));
			String sigla = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("SG_ENTIDADE_ENSINO"));
			String nomeFaculdade = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_ENTIDADE_ENSINO"));
			int idSubTipo = Integer.parseInt(this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("ID_SUBTIPO_PRODUCAO")));
			
			String titulo = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_TITULO"));
			String natureza = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_NATUREZA"));
			String idioma = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_IDIOMA"));
			String cidade = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_CIDADE"));
			String paginaInicial = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINA_INICIAL"));
			String paginaFinal = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINA_FINAL"));
			
			
			if(idSubTipo == 8) {
				String evento = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_EVENTO"));
				producaoAtual = new Anais(titulo, natureza, idioma, cidade, paginaInicial, paginaFinal, evento);
				instituicaoAtual = new Instituicao(nomeFaculdade, sigla);
				this.listaDePPGs.add(new PPG(codigoPPG, instituicaoAtual, producaoAtual));				
			}
			
			linha = this.leitorDoArqivo.lerLinhaDoCsv();
		}
	}
	
	
	public int retornaQuantidadeDePPGs() {
		List<String> lista = new ArrayList<String>();
		for(int i = 0; i < this.listaDePPGs.size(); i++) {
			
			if(!lista.contains(listaDePPGs.get(i).getCodigo())) {
				
				lista.add(listaDePPGs.get(i).getCodigo());
			}
		}
		return lista.size();
	}
	
	
	public int retornaQuantidadeDeInstituicoesPublicaramEmAnais() {
		List<String> lista = new ArrayList<String>();
		
		for(int i = 0; i < this.listaDePPGs.size(); i++) {
			Instituicao instituicao = listaDePPGs.get(i).getInstituicao();
			String nomeSigla = instituicao.getNome() + "-" + instituicao.getSigla();
			
			if(!lista.contains(nomeSigla)) {
				lista.add(nomeSigla);
			}
		}
		
		return lista.size();
	}
	
	
	public int retornaQuantidadeDeProducoes() {
		return this.listaDePPGs.size();
	}

	
	public int[] retornaQuantidadeETotal() {
		int soma = 0;
		int contador = 0;
		
		for(int i = 0; i < this.listaDePPGs.size(); i++) {
			int valorDePaginas = this.listaDePPGs.get(i).getQuantidadeDePaginas();
			if(valorDePaginas != -1) {
				soma += valorDePaginas;
				contador++;
			}
			
		}
		int[] vetorDeDados = new int[2];
		vetorDeDados[0] = soma;
		vetorDeDados[1] = contador;
		
		return vetorDeDados;
	}
	
	public int retornaQuantidadeDePaginasTotal() {
		int[] vetor = this.retornaQuantidadeETotal();
		return vetor[0];
	}
	
	public double retornaMediaDePaginas() {
		int[] vetor = this.retornaQuantidadeETotal();
		return (double) vetor[0] / (double)vetor[1];
	}
	
}
	