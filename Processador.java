import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Processador {
	
	private LeitorDeCsv leitorDoArqivo;
	private Map<String, PPG> PPGs = new HashMap<>();
	private Map<String, Instituicao> instituicoes = new HashMap<>();
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
	
	public void mudaEnderecoDeEntrada(String novoEndereco) throws IOException {
		this.leitorDoArqivo.lerNovoEnderecoDeEntrada(novoEndereco);
	}
	
	public void preencheListaDePPGs() throws IOException {
		this.preencheCabecalho();
		String[] linha = this.leitorDoArqivo.lerLinhaDoCsv();
		while(linha != null) {			
			
			Producao producaoAtual = null;
			Instituicao instituicaoAtual = null;
			PPG ppgAtual = null;
			
			String codigoPPG = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("CD_PROGRAMA_IES"));
			String sigla = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("SG_ENTIDADE_ENSINO"));
			String nomeFaculdade = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_ENTIDADE_ENSINO"));
			int idSubTipo = Integer.parseInt(this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("ID_SUBTIPO_PRODUCAO")));
			String cidade = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_CIDADE"));
			
			instituicaoAtual = new Instituicao(nomeFaculdade, sigla);
			this.instituicoes.put(nomeFaculdade + sigla, instituicaoAtual);
			
			if(idSubTipo == 8) {
				
				String titulo = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_TITULO"));
				String natureza = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_NATUREZA"));
				String idioma = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_IDIOMA"));
				String evento = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_EVENTO"));
		
				producaoAtual = new Anais(cidade, natureza, titulo, idioma, evento);
				String paginaInicial = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINA_INICIAL"));
				String paginaFinal = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINA_FINAL"));
				int quantidadeDePaginas = producaoAtual.calculaQuantidadeDePaginas(paginaInicial, paginaFinal);
				producaoAtual.setQuantidadeDePaginas(quantidadeDePaginas);
				
				ppgAtual = new PPG(codigoPPG, this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_PROGRAMA_IES")));
				
				if(this.PPGs.containsKey(codigoPPG)) {
					this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
					this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
				}
				else {
					this.PPGs.put(codigoPPG, ppgAtual);
					this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
					this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
				}
			}
			
			else if(idSubTipo == 9) {
				
				String dataDePublicacao = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DT_PUBLICACAO"));
				String ISSN = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_ISSN"));
				producaoAtual = new Artjr(cidade, dataDePublicacao, ISSN);
				String paginaInicial = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINA_INICIAL"));
				String paginaFinal = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINA_FINAL"));
				int quantidadeDePaginas = producaoAtual.calculaQuantidadeDePaginas(paginaInicial, paginaFinal);
				producaoAtual.setQuantidadeDePaginas(quantidadeDePaginas);
				
				ppgAtual = new PPG(codigoPPG, this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_PROGRAMA_IES")));
				
				if(this.PPGs.containsKey(codigoPPG)) {
					this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
					this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
				}
				else {
					this.PPGs.put(codigoPPG, ppgAtual);
					this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
					this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
				}
			}
			
			else if(idSubTipo == 25) {
				
				String natureza = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_NATUREZA"));
				String idioma = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_IDIOMA"));
				String editora = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_EDITORA"));
				String volume = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_VOLUME"));
				String fasciculo = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_FASCICULO"));
				String serie = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_SERIE"));
				String ISSN = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_ISSN"));
				
				producaoAtual = new Artpe(cidade, natureza, idioma, editora, volume, fasciculo, serie, ISSN);
				String paginaInicial = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINA_INICIAL"));
				String paginaFinal = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINA_FINAL"));
				int quantidadeDePaginas = producaoAtual.calculaQuantidadeDePaginas(paginaInicial, paginaFinal);
				producaoAtual.setQuantidadeDePaginas(quantidadeDePaginas);
				
				ppgAtual = new PPG(codigoPPG, this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_PROGRAMA_IES")));
				
				if(this.PPGs.containsKey(codigoPPG)) {
					this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
					this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
				}
				else {
					this.PPGs.put(codigoPPG, ppgAtual);
					this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
					this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
				}
			}
			
			else if(idSubTipo == 26) {
				
				String natureza = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_NATUREZA"));
				String idioma = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_IDIOMA"));
				String editora = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_EDITORA"));
				String ISBN = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_ISBN"));
				
				
				producaoAtual = new Livro(cidade, natureza, idioma, editora, ISBN);
				String pagina = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINAS_CONTRIBUICAO"));
				int quantidadeDePaginas = producaoAtual.calculaQuantidadeDePaginas(pagina);
				producaoAtual.setQuantidadeDePaginas(quantidadeDePaginas);
				
				ppgAtual = new PPG(codigoPPG, this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_PROGRAMA_IES")));
				
				if(this.PPGs.containsKey(codigoPPG)) {
					this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
					this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
				}
				else {
					this.PPGs.put(codigoPPG, ppgAtual);
					this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
					this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
				}
			}
			
			else if(idSubTipo == 10) {
				
				String natureza = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_NATUREZA"));
				String idioma = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_IDIOMA"));
				String editora = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_EDITORA"));
				
				
				producaoAtual = new Outro(cidade, natureza, idioma, editora);
				String pagina = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINAS"));
				int quantidadeDePaginas = producaoAtual.calculaQuantidadeDePaginas(pagina);
				producaoAtual.setQuantidadeDePaginas(quantidadeDePaginas);
				
				ppgAtual = new PPG(codigoPPG, this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_PROGRAMA_IES")));
				
				if(this.PPGs.containsKey(codigoPPG)) {
					this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
					this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
				}
				else {
					this.PPGs.put(codigoPPG, ppgAtual);
					this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
					this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
				}
			}
			
			else if(idSubTipo == 28) {
				
				String natureza = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_NATUREZA"));
				String editora = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_EDITORA"));
				String formacaoInstrumental = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_EDITORA"));
				
				
				producaoAtual = new Partmu(cidade, natureza, editora, formacaoInstrumental);
				String pagina = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINAS"));
				int quantidadeDePaginas = producaoAtual.calculaQuantidadeDePaginas(pagina);
				producaoAtual.setQuantidadeDePaginas(quantidadeDePaginas);
				
				ppgAtual = new PPG(codigoPPG, this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_PROGRAMA_IES")));
				
				if(this.PPGs.containsKey(codigoPPG)) {
					this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
					this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
				}
				else {
					this.PPGs.put(codigoPPG, ppgAtual);
					this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
					this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
				}
			}
			
			else if(idSubTipo == 21) {
				
				String natureza = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_NATUREZA"));
				String titulo = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_TITULO"));
				String idioma = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_IDIOMA"));
				String editora = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_EDITORA_TRADUCAO"));
				String idiomaTraducao = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_IDIOMA_TRADUCAO"));
				
				
				producaoAtual = new Tradu(cidade, natureza, titulo, idioma, editora, idiomaTraducao);
				String pagina = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINAS"));
				int quantidadeDePaginas = producaoAtual.calculaQuantidadeDePaginas(pagina);
				producaoAtual.setQuantidadeDePaginas(quantidadeDePaginas);
				
				ppgAtual = new PPG(codigoPPG, this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_PROGRAMA_IES")));
				
				if(this.PPGs.containsKey(codigoPPG)) {
					this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
					this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
				}
				else {
					this.PPGs.put(codigoPPG, ppgAtual);
					this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
					this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
				}
			}
			
			String nomeSigla = nomeFaculdade + sigla;
			if(!this.instituicoes.get(nomeSigla).verificaSePpgExisteNaLIsta(ppgAtual)) {
				this.instituicoes.get(nomeSigla).adicionaPPG(ppgAtual);
			}
			
			linha = this.leitorDoArqivo.lerLinhaDoCsv();
		}
	}
	
	
	public int retornaQuantidadeDePPGs() {
		return this.PPGs.size();
	}
	
	
	public int retornaQuantidadeDeInstituicoesPublicaramEmAnais() {
		return this.instituicoes.size();
	}
	
	public int retornaQuantidadeDeProducoes() {
		int somaDeProducoes = 0;
		for(PPG ppg : this.PPGs.values()) {
			somaDeProducoes += ppg.retornaQuantidadeDeProducoesNaLista();
		}
		return somaDeProducoes;
	}
	
	public int[] retornaQuantidadeETotal() {
		int soma = 0;
		int contador = 0;
		
		for(PPG ppg : this.PPGs.values()) {
			soma += ppg.retornaQuantidadeDePaginasProdValidas()[0];
			contador += ppg.retornaQuantidadeDePaginasProdValidas()[1];
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
	
	
	public void executaComandoRede() {
		
		List<PPG> ppgsEmRede = new ArrayList<PPG>();
		System.out.println("Programas em rede:");
		
		for(PPG ppg : PPGs.values()) {
			if(ppg.verificaPPGsEmRede()) {
				ppg.ordernarInstituicoes();
				ppgsEmRede.add(ppg);
			}
		}
		Collections.sort(ppgsEmRede);
		
		for(int i = 0; i < ppgsEmRede.size(); i++) {
			System.out.println(ppgsEmRede.get(i).getCodigo() + ": " + ppgsEmRede.get(i).getNome());
			ppgsEmRede.get(i).imprimirInstituicoes();
		}
		
	}
	
	public void exexutaComandoPpg(String ppg) {
		
		PPG ppgRequerida = PPGs.get(ppg);
		if(ppgRequerida == null) {
			System.out.println("PPG nao encontrado.");
		}
		else {
			System.out.printf("Programa: %s\n", ppgRequerida.getNome());
			System.out.println("Instituicoes:");
			ppgRequerida.ordernarInstituicoes();
			ppgRequerida.imprimirInstituicoes();
			System.out.printf("\n");
			
			int vetorDosTiposDeProducoes[] = ppgRequerida.retornaQuantidadeDasProducoes();
			System.out.println("Quantidade de producoes por tipo:");
			System.out.println("\t- Artigos em anais de eventos: " + vetorDosTiposDeProducoes[0]);
			System.out.println("\t- Artigos em jornais e revistas: " + vetorDosTiposDeProducoes[1]);
			System.out.println("\t- Artigos em periodicos cientificos: " + vetorDosTiposDeProducoes[2]);
			System.out.println("\t- Livros: " + vetorDosTiposDeProducoes[3]);
			System.out.println("\t- Partituras musicais: " + vetorDosTiposDeProducoes[4]);
			System.out.println("\t- Traducoes: " + vetorDosTiposDeProducoes[5]);
			System.out.println("\t- Outros: " + vetorDosTiposDeProducoes[6]);
			System.out.printf("\n");
			
			int paginas[] = ppgRequerida.retornaQuantidadeDePaginasProdValidas();
			
			System.out.print("Total de paginas produzidas pelo PPG: " + paginas[0]);
		}
	}
	
}
	