import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
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
			
			
			if(!this.instituicoes.containsKey(nomeFaculdade + sigla)) {
				instituicaoAtual = new Instituicao(nomeFaculdade, sigla);
				this.instituicoes.put(nomeFaculdade + sigla, instituicaoAtual);
			}
			
			else {
				instituicaoAtual = this.instituicoes.get(nomeFaculdade + sigla);
			}
			
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
				
			}
			
			else if(idSubTipo == 9) {
				
				String titulo = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_TITULO"));
				String idioma = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_IDIOMA"));
				String dataDePublicacao = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DT_PUBLICACAO"));
				String ISSN = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_ISSN"));
				producaoAtual = new Artjr(titulo, idioma, cidade, dataDePublicacao, ISSN);
				String paginaInicial = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINA_INICIAL"));
				String paginaFinal = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINA_FINAL"));
				int quantidadeDePaginas = producaoAtual.calculaQuantidadeDePaginas(paginaInicial, paginaFinal);
				producaoAtual.setQuantidadeDePaginas(quantidadeDePaginas);
				
			
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
				
			}
			
			else if(idSubTipo == 26) {
				
				String natureza = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_NATUREZA"));
				String idioma = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_IDIOMA"));
				String editora = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_EDITORA"));
				String ISBN = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_ISBN")); 
				String titulo = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_TITULO"));
				String cidadeLivro = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_CIDADE_PAIS"));
				
				producaoAtual = new Livro(cidadeLivro, natureza, idioma, editora, ISBN, titulo);
				String pagina = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINAS_CONTRIBUICAO"));
				int quantidadeDePaginas = producaoAtual.calculaQuantidadeDePaginas(pagina);
				producaoAtual.setQuantidadeDePaginas(quantidadeDePaginas);
				

			}
			
			else if(idSubTipo == 10) {
				
				String natureza = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_NATUREZA"));
				String idioma = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_IDIOMA"));
				String editora = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_EDITORA"));
				
				
				producaoAtual = new Outro(cidade, natureza, idioma, editora);
				String pagina = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINAS"));
				int quantidadeDePaginas = producaoAtual.calculaQuantidadeDePaginas(pagina);
				producaoAtual.setQuantidadeDePaginas(quantidadeDePaginas);
				
			}
			
			else if(idSubTipo == 28) {
				
				String natureza = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_NATUREZA"));
				String editora = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_EDITORA"));
				String formacaoInstrumental = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_FORMACAO_INSTRUMENTAL"));
				
				
				producaoAtual = new Partmu(cidade, natureza, editora, formacaoInstrumental);
				String pagina = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINAS"));
				int quantidadeDePaginas = producaoAtual.calculaQuantidadeDePaginas(pagina);
				producaoAtual.setQuantidadeDePaginas(quantidadeDePaginas);
				
				
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
				
			}
			
			ppgAtual = new PPG(codigoPPG, this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_PROGRAMA_IES")));
			
			if(!this.PPGs.containsKey(codigoPPG)) {
				this.PPGs.put(codigoPPG, ppgAtual);
			} 
			else {
				ppgAtual = this.PPGs.get(codigoPPG);
			}

			this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
			this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
		
			String nomeSigla = nomeFaculdade + sigla;
			this.instituicoes.get(nomeSigla).adicionaPPG(ppgAtual);
			
			linha = this.leitorDoArqivo.lerLinhaDoCsv();
		}
	}
	
	public int retornaQuantidadeDeProducoes() {
		int somaDeProducoes = 0;
		for(PPG ppg : this.PPGs.values()) {
			somaDeProducoes += ppg.retornaQuantidadeDeProducoesNaLista();
		}
		return somaDeProducoes;
	}
	
	private int[] retornaQuantidadeETotal() {
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
			ppgRequerida.imprimirDadosProducoesFormstadosParaComandoPpg();
		}
	}


	public void executaComandoIes(String ies) {
		List<Instituicao> instituicoesRequeridas = new ArrayList<Instituicao>();
		
		for(Instituicao instituicao : this.instituicoes.values()) {
			if(instituicao.getSigla().equals(ies)) {
				instituicoesRequeridas.add(instituicao);
			}
		}
		
		if(instituicoesRequeridas.size() == 0) {
			throw new InvalidParameterException("IES nao encontrada.");
		}
		
		Collections.sort(instituicoesRequeridas);
		
		for(Instituicao instituicao : instituicoesRequeridas) {
			instituicao.ordenarPpgs();
		}
		
		for(Instituicao instituicao : instituicoesRequeridas) {
			System.out.println(instituicao.getNome() + " (" + instituicao.getSigla() + "):");
			instituicao.imprimirPpgsFormatadas();
		}
	}


	public void executaComandoCsv(String next, String next2) {
		if(this.PPGs.get(next) == null) {
			System.out.println("PPG nao encontrado.");
		}
		else if(next2.equals("anais")) {
			this.executaComandoCsvParaAnais(next);
		}
		
		else if(next2.equals("artjr")) {
			this.executaComandoCsvParaArtjr(next);
		}
		
		else if(next2.equals("artpe")) {
			this.executaComandoCsvParaArtpe(next);
		}
		
		else if(next2.equals("livro")) {
			this.executaComandoCsvParaLivro(next);
		}
		
		else if(next2.equals("partmu")) {
			this.executaComandoCsvParaPartmu(next);
		}
		
		else if(next2.equals("tradu")) {
			this.executaComandoCsvParaTradu(next);
		}
		
		else if(next2.equals("outro")) {
			this.executaComandoCsvParaOutro(next);
		}
		else {
			throw new InvalidParameterException("Tipo invalido.");
		}
	}


	private void executaComandoCsvParaOutro(String next) {
		System.out.println("Natureza;Idioma;Editora;Cidade;Paginas");
		
		List<Outro> producoesDaPpgDeEntrada = new ArrayList<Outro>();
		
		for(PPG ppg : this.PPGs.values()) {
			if(ppg.getCodigo().equals(next)) {
				producoesDaPpgDeEntrada = ppg.retornaListaDeOutro();
			}
		}
		
		Collections.sort(producoesDaPpgDeEntrada);
		
		for(int i = 0; i < producoesDaPpgDeEntrada.size(); i++) {
			producoesDaPpgDeEntrada.get(i).imprimirOutroFormatadaParte5();
		}
		
	}


	private void executaComandoCsvParaTradu(String next) {
		System.out.println("Natureza;Titulo;Idioma;Editora;Cidade;Traducao;Paginas");
		
		List<Tradu> producoesDaPpgDeEntrada = new ArrayList<Tradu>();
		
		for(PPG ppg : this.PPGs.values()) {
			if(ppg.getCodigo().equals(next)) {
				producoesDaPpgDeEntrada = ppg.retornaListaDeTradu();
			}
		}
		
		Collections.sort(producoesDaPpgDeEntrada);
		
		for(int i = 0; i < producoesDaPpgDeEntrada.size(); i++) {
			producoesDaPpgDeEntrada.get(i).imprimirTraduFormatadaParte5();
		}
		
	}


	private void executaComandoCsvParaPartmu(String next) {
		System.out.println("Natureza;Editora;Cidade;Formacao;Paginas");
		
		List<Partmu> producoesDaPpgDeEntrada = new ArrayList<Partmu>();
		
		for(PPG ppg : this.PPGs.values()) {
			if(ppg.getCodigo().equals(next)) {
				producoesDaPpgDeEntrada = ppg.retornaListaDePartmu();
			}
		}
		
		Collections.sort(producoesDaPpgDeEntrada);
		
		for(int i = 0; i < producoesDaPpgDeEntrada.size(); i++) {
			producoesDaPpgDeEntrada.get(i).imprimirPartmuFormatadaParte5();
		}
		
	}


	private void executaComandoCsvParaLivro(String next) {
		System.out.println("Natureza;Titulo;Idioma;Editora;Cidade;ISBN;Paginas");
		
		List<Livro> producoesDaPpgDeEntrada = new ArrayList<Livro>();
		
		for(PPG ppg : this.PPGs.values()) {
			if(ppg.getCodigo().equals(next)) {
				producoesDaPpgDeEntrada = ppg.retornaListaDeLivro();
			}
		}
		
		Collections.sort(producoesDaPpgDeEntrada);
		
		for(int i = 0; i < producoesDaPpgDeEntrada.size(); i++) {
			producoesDaPpgDeEntrada.get(i).imprimirLivroFormatadaParte5();
		}		
	}


	private void executaComandoCsvParaArtpe(String next) {
		System.out.println("Natureza;Idioma;Editora;Cidade;Volume;Fasciulo;Serie;ISSN;Paginas");
		
		List<Artpe> producoesDaPpgDeEntrada = new ArrayList<Artpe>();
		
		for(PPG ppg : this.PPGs.values()) {
			if(ppg.getCodigo().equals(next)) {
				producoesDaPpgDeEntrada = ppg.retornaListaDeArtpe();
			}
		}
		
		Collections.sort(producoesDaPpgDeEntrada);
		
		for(int i = 0; i < producoesDaPpgDeEntrada.size(); i++) {
			producoesDaPpgDeEntrada.get(i).imprimirArtpeFormatadaParte5();
		}
		
	}


	private void executaComandoCsvParaArtjr(String next) {
		System.out.println("Titulo;Idioma;Cidade;Data;ISSN;Paginas");
		
		List<Artjr> producoesDaPpgDeEntrada = new ArrayList<Artjr>();
		
		for(PPG ppg : this.PPGs.values()) {
			if(ppg.getCodigo().equals(next)) {
				producoesDaPpgDeEntrada = ppg.retornaListaDeArtjr();
			}
		}
		
		Collections.sort(producoesDaPpgDeEntrada);
		
		for(int i = 0; i < producoesDaPpgDeEntrada.size(); i++) {
			producoesDaPpgDeEntrada.get(i).imprimirArtjrFormatadaParte5();
		}
	}


	private void executaComandoCsvParaAnais(String next) {
		System.out.println("Natureza;Titulo;Idioma;Evento;Cidade;Paginas");
		
		List<Anais> producoesDaPpgDeEntrada = new ArrayList<Anais>();
		
		for(PPG ppg : this.PPGs.values()) {
			if(ppg.getCodigo().equals(next)) {
				producoesDaPpgDeEntrada = ppg.retornaListaDeAnais();
			}
		}
		
		Collections.sort(producoesDaPpgDeEntrada);
		
		for(int i = 0; i < producoesDaPpgDeEntrada.size(); i++) {
			producoesDaPpgDeEntrada.get(i).imprimirAnaisFormatadaParte5();
		}
		
	}
	
}
	