package ProcessaDados;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.*;

import Capes.Instituicao;
import Capes.PPG;
import LeitorCsv.LeitorDeCsv;
import Producoes.Anais;
import Producoes.Artjr;
import Producoes.Artpe;
import Producoes.Livro;
import Producoes.Outro;
import Producoes.Partmu;
import Producoes.Producao;
import Producoes.Tradu;

/**
 * classe processador que contem um leitor, duas maps, uma de ppgs e outra de instituicoes,
 * e um vetor de string que corresponde ao cabecalho
 * @author humberto
 *
 */
public class Processador {
	
	private LeitorDeCsv leitorDoArqivo;
	private Map<String, PPG> PPGs = new HashMap<>();
	private Map<String, Instituicao> instituicoes = new HashMap<>();
	private String[] cabecalho;	
	
	/**
	 * Construtor da classe processador
	 * @param caminho
	 * @throws IOException
	 */
	public Processador(String caminho) throws IOException {
		this.leitorDoArqivo = new LeitorDeCsv(caminho);
	}
	
	/**
	 * Funcao que faz a leitura do cabecalho e armazena no vetor
	 * @throws IOException
	 */
	public void preencheCabecalho() throws IOException {
		this.cabecalho = this.leitorDoArqivo.lerCabecalhoCsv();
	}
	
	/**
	 * Analisa em qual posicao do cabecalho esta a string entrada e retorna esse indice
	 * @param entrada
	 * @return inteiro representado indice que a string esta no cabecalho
	 */
	private int retornaIndiceDaStringNoCabecalho(String entrada) {
		for(int i = 0; i < this.cabecalho.length; i++) {
			if(this.cabecalho[i].equals(entrada)) {
				return i;
			}
		}
		return -1; //retorna -1 caso nao esteja no vetor
	}
	
	/**
	 * Muda a string que define o caminho ate o arquivo csv
	 * @param novoEndereco
	 * @throws IOException
	 */
	public void mudaEnderecoDeEntrada(String novoEndereco) throws IOException {
		this.leitorDoArqivo.lerNovoEnderecoDeEntrada(novoEndereco);
	}
	
	/**
	 * Principal funcao do trabalho, faz a leitura de todo o arquivo csv e cria
	 * as instituicoes, ppgs e producoes, preenchendo cada lista internamente
	 * e externamente.
	 * @throws IOException
	 */
	public void preencheListaDePPGs() throws IOException {
		this.preencheCabecalho(); //salva o cabecalho daquele arquivo
		String[] linha = this.leitorDoArqivo.lerLinhaDoCsv(); //le uma linha inteira do csv
		while(linha != null) {			
			
			//faz as instancias basicas
			Producao producaoAtual = null;
			Instituicao instituicaoAtual = null;
			PPG ppgAtual = null;
			
			//executa as leituras basicas que serao usadas em qualquer tipo de producao
			//e as caracteristicas basicas da ppg e instituicao
			String codigoPPG = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("CD_PROGRAMA_IES"));
			String sigla = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("SG_ENTIDADE_ENSINO"));
			String nomeFaculdade = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_ENTIDADE_ENSINO"));
			int idSubTipo = Integer.parseInt(this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("ID_SUBTIPO_PRODUCAO")));
			String cidade = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_CIDADE"));
			
			//analisa se instituicao ja esta na map e inseri caso contrario
			//caso ja esteja, retorna essa instituicao para ser manipulada
			if(!this.instituicoes.containsKey(nomeFaculdade + sigla)) {
				instituicaoAtual = new Instituicao(nomeFaculdade, sigla);
				this.instituicoes.put(nomeFaculdade + sigla, instituicaoAtual);
			}
			
			else {
				instituicaoAtual = this.instituicoes.get(nomeFaculdade + sigla);
			}
			
			//nessa parte, o funcao analisara qual a producao que contem a linha 
			//do arquivo e criara esse tipo de producao, fazendo as leituras
			//necessarias para cada tipo e insere nas devidas listas para serem
			//manipuladas depois
			
			if(idSubTipo == 8) { //caso seja anais
				
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
			
			else if(idSubTipo == 9) { //caso seja artjr
				
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
			
			else if(idSubTipo == 25) { //caso seja artpe
				
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
			
			else if(idSubTipo == 26) { //caso seja livro
				
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
			
			else if(idSubTipo == 10) { //caso seja outro
				
				String natureza = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_NATUREZA"));
				String idioma = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_IDIOMA"));
				String editora = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_EDITORA"));
				
				
				producaoAtual = new Outro(cidade, natureza, idioma, editora);
				String pagina = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINAS"));
				int quantidadeDePaginas = producaoAtual.calculaQuantidadeDePaginas(pagina);
				producaoAtual.setQuantidadeDePaginas(quantidadeDePaginas);
				
			}
			
			else if(idSubTipo == 28) { //caso seja partmu
				
				String natureza = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_NATUREZA"));
				String editora = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_EDITORA"));
				String formacaoInstrumental = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("DS_FORMACAO_INSTRUMENTAL"));
				
				
				producaoAtual = new Partmu(cidade, natureza, editora, formacaoInstrumental);
				String pagina = this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NR_PAGINAS"));
				int quantidadeDePaginas = producaoAtual.calculaQuantidadeDePaginas(pagina);
				producaoAtual.setQuantidadeDePaginas(quantidadeDePaginas);
				
				
			}
				
			else if(idSubTipo == 21) { //caso seja tradu
				
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
			
			//chama o construtor para uma nova ppg
			ppgAtual = new PPG(codigoPPG, this.leitorDoArqivo.getColuna(this.retornaIndiceDaStringNoCabecalho("NM_PROGRAMA_IES")));
			
			//analisa se ja esta na lista e insere caso contrario
			//se ja estiver, retorna para ser manipulada
			
			if(!this.PPGs.containsKey(codigoPPG)) {
				this.PPGs.put(codigoPPG, ppgAtual);
			} 
			else {
				ppgAtual = this.PPGs.get(codigoPPG);
			}
			
			//adiciona instituicao e producao nas listas da ppg que foi criada nessa
			//linha do arquivo csv
			this.PPGs.get(codigoPPG).adicionaInstituicaNaLista(instituicaoAtual);
			this.PPGs.get(codigoPPG).adicionaProducaoNaLista(producaoAtual);
		
			String nomeSigla = nomeFaculdade + sigla;
			this.instituicoes.get(nomeSigla).adicionaPPG(ppgAtual);
			
			linha = this.leitorDoArqivo.lerLinhaDoCsv();
		}
	}
	
	/**
	 * Funcao responsavel por percorrer toda a lista de ppgs e contar quantas producoes
	 * tem e fazendo a soma, para saber o numero de producoes total
	 * @return inteiro que representa o somatorio de producoes de todas as ppgs
	 */
	public int retornaQuantidadeDeProducoes() {
		int somaDeProducoes = 0;
		for(PPG ppg : this.PPGs.values()) {
			somaDeProducoes += ppg.retornaQuantidadeDeProducoesNaLista();
		}
		return somaDeProducoes;
	}
	
	/**
	 * Funcao que analisa o tamanho da lista de ppgs
	 * @return inteiro representado tamanho da lista
	 */
	public int retornaQuantidadeDePpgs() {
		return this.PPGs.size();
	}
	
	/**
	 * Funcao que analisa o tamanho da lista de instituicoes
	 * @return inteiro representado tamanho da lista
	 */
	public int retornaQuantidadeDeInstituicoes() {
		return this.instituicoes.size();
	}
	/**
	 * Funcao que tambem roda todas as producoes de todas as ppgs, mas retorna um vetor
	 * que na primeira posicao contem a soma das paginas de producoes validas
	 * e na segunda posicao quantas producoes sao validas
	 * @return um vetor de inteiros que guarda somatorio de paginas e quantidade de
	 * producoes
	 */
	private int[] retornaQuantidadeETotal() {
		int soma = 0;
		int contador = 0;
		
		for(PPG ppg : this.PPGs.values()) {
			//roda todas as ppg usando um forit
			soma += ppg.retornaQuantidadeDePaginasProdValidas()[0];
			contador += ppg.retornaQuantidadeDePaginasProdValidas()[1];
		}
		int[] vetorDeDados = new int[2];
		vetorDeDados[0] = soma; //atribui os valores no vetor
		vetorDeDados[1] = contador;
		
		return vetorDeDados;
	}
	
	/**
	 * funcao que filtra a funcao acima e retorna apenas a quantidade de paginas
	 * @return inteiro representado o somatorio de paginas das producoes
	 */
	public int retornaQuantidadeDePaginasTotal() {
		int[] vetor = this.retornaQuantidadeETotal();
		return vetor[0];
	}
	
	/**
	 * funcao que retorna media de paginas das producoes
	 * @return double repreentado a media de paginas
	 */
	public double retornaMediaDePaginas() {
		int[] vetor = this.retornaQuantidadeETotal();
		return (double) vetor[0] / (double)vetor[1];
	}
	
	/**
	 * funcao responsavel por executar o comando rede
	 */
	public void executaComandoRede() {
		
		List<PPG> ppgsEmRede = new ArrayList<PPG>();
		System.out.println("Programas em rede:");
		
		for(PPG ppg : PPGs.values()) {
			//para cada ppg (usando um forit) verifica se esta em rede
			//caso esteja, ordena a lista de instuicoes dela e adiciona na lista
			//de ppgs em rede
			if(ppg.verificaPPGsEmRede()) {
				ppg.ordernarInstituicoes();
				ppgsEmRede.add(ppg);
			}
		}
		Collections.sort(ppgsEmRede); //ordena a lista de ppgsEmRede
		
		//printa formatado
		for(int i = 0; i < ppgsEmRede.size(); i++) {
			System.out.println(ppgsEmRede.get(i).getCodigo() + ": " + ppgsEmRede.get(i).getNome());
			ppgsEmRede.get(i).imprimirInstituicoes();
		}
		
	}
	
	/**
	 * Funcao responsavel por fazer o que o comando ppg pede
	 * @param ppg
	 */
	public void exexutaComandoPpg(String ppg) {
		
		PPG ppgRequerida = PPGs.get(ppg); //retorna a ppg de entrada
		if(ppgRequerida == null) {
			//lanca um erro caso ppg nao seja encontrada na map
			throw new InvalidParameterException("PPG nao encontrado.");
		}
		else {
			//faz a impressao das producoes no modelo que o comando pede
			ppgRequerida.imprimirDadosProducoesFormstadosParaComandoPpg();
		}
	}

	/**
	 * Funcao responsavel por fazer o que o comando ies pede
	 * @param ies
	 */
	public void executaComandoIes(String ies) {
		List<Instituicao> instituicoesRequeridas = new ArrayList<Instituicao>();
		
		//percorre toda a lista de instituicao com um forit
		for(Instituicao instituicao : this.instituicoes.values()) {
			if(instituicao.getSigla().equals(ies)) {
				//busca a instituicao com a sigla e adiciona na lista
				instituicoesRequeridas.add(instituicao);
			}
		}
		
		if(instituicoesRequeridas.size() == 0) {
			//caso nao apareca nenhuma instituicao com aquela sigla, lanca um erro
			throw new InvalidParameterException("IES nao encontrada.");
		}
		
		//Ordena as intituicoes com aquela silga
		Collections.sort(instituicoesRequeridas);
		
		//para cada instituicao na lista, ordena a lista de ppgs interna de cada uma
		for(Instituicao instituicao : instituicoesRequeridas) {
			instituicao.ordenarPpgs();
		}
		
		//para cada instituicao na lista faz a impressao das ppgs no modelo pedido
		for(Instituicao instituicao : instituicoesRequeridas) {
			System.out.println(instituicao.getNome() + " (" + instituicao.getSigla() + "):");
			instituicao.imprimirPpgsFormatadas();
		}
	}

	/**
	 * Funcao responsavel por faz o que o comando csv pede
	 * @param next
	 * @param next2
	 */
	public void executaComandoCsv(String next, String next2) {
		
		if(this.PPGs.get(next) == null) {
			//lanca uma mensagem de erro caso na exista a ppg requerida
			throw new InvalidParameterException("PPG nao encontrado.");
		}
		else if(next2.equals("anais")) {
			//analisa se a string de entrada pede anais
			//chama a funcao que executa o comando especifico para anais
			this.executaComandoCsvParaAnais(next);
		}
		
		else if(next2.equals("artjr")) {
			//analisa se a string de entrada pede artjr
			//chama a funcao que executa o comando especifico para artjr
			this.executaComandoCsvParaArtjr(next);
		}
		
		else if(next2.equals("artpe")) {
			//analisa se a string de entrada pede artpe
			//chama a funcao que executa o comando especifico para artpe
			this.executaComandoCsvParaArtpe(next);
		}
		
		else if(next2.equals("livro")) {
			//analisa se a string de entrada pede livro
			//chama a funcao que executa o comando especifico para livro
			this.executaComandoCsvParaLivro(next);
		}
		
		else if(next2.equals("partmu")) {
			//analisa se a string de entrada pede partmu
			//chama a funcao que executa o comando especifico para partmu
			this.executaComandoCsvParaPartmu(next);
		}
		
		else if(next2.equals("tradu")) {
			//analisa se a string de entrada pede tradu
			//chama a funcao que executa o comando especifico para tradu
			this.executaComandoCsvParaTradu(next);
		}
		
		else if(next2.equals("outro")) {
			//analisa se a string de entrada pede outro
			//chama a funcao que executa o comando especifico para outro
			this.executaComandoCsvParaOutro(next);
		}
		else {
			//lanca uma mensagem de erro caso nao seja nenhum tipo das producoes
			throw new InvalidParameterException("Tipo invalido.");
		}
	}

	/**
	 * Funcao que executa o comando csv especifico para outro, imprimindo as caracteristicas
	 * especificas do tipo de producao outro
	 * @param next
	 */
	private void executaComandoCsvParaOutro(String next) {
		System.out.println("Natureza;Idioma;Editora;Cidade;Paginas");
		
		List<Outro> producoesDaPpgDeEntrada = new ArrayList<Outro>();
		
		//roda toda lista de ppgs com um forit
		for(PPG ppg : this.PPGs.values()) {
			if(ppg.getCodigo().equals(next)) { //analisa o codigo da ppg
				
				//retorna a todas as producoes do tipo outro da ppg requerida
				producoesDaPpgDeEntrada = ppg.retornaListaDeOutro();
			}
		}
		
		//ordena
		Collections.sort(producoesDaPpgDeEntrada);
		
		//faz a impressao como pedido para outro no comando csv
		for(int i = 0; i < producoesDaPpgDeEntrada.size(); i++) {
			producoesDaPpgDeEntrada.get(i).imprimirOutroFormatadaParte5();
		}
		
	}

	/**
	 * Funcao que executa o comando csv especifico para outro, imprimindo as caracteristicas
	 * especificas do tipo de producao tradu
	 * @param next
	 */
	private void executaComandoCsvParaTradu(String next) {
		System.out.println("Natureza;Titulo;Idioma;Editora;Cidade;Traducao;Paginas");
		
		List<Tradu> producoesDaPpgDeEntrada = new ArrayList<Tradu>();
		
		//roda toda a lista de pggs com um forit
		for(PPG ppg : this.PPGs.values()) {
			if(ppg.getCodigo().equals(next)) { //analisa o codigo da ppg
				//retorna a todas as producoes do tipo tradu da ppg requerida
				producoesDaPpgDeEntrada = ppg.retornaListaDeTradu();
			}
		}
		
		//ordena
		Collections.sort(producoesDaPpgDeEntrada);
		
		//faz a impressao como pedido para tradu no comando csv
		for(int i = 0; i < producoesDaPpgDeEntrada.size(); i++) {
			producoesDaPpgDeEntrada.get(i).imprimirTraduFormatadaParte5();
		}
		
	}

	/**
	 * Funcao que executa o comando csv especifico para outro, imprimindo as caracteristicas
	 * especificas do tipo de producao partmu
	 * @param next
	 */
	private void executaComandoCsvParaPartmu(String next) {
		System.out.println("Natureza;Editora;Cidade;Formacao;Paginas");
		
		List<Partmu> producoesDaPpgDeEntrada = new ArrayList<Partmu>();
		
		//roda toda a lista de pggs com um forit
		for(PPG ppg : this.PPGs.values()) {
			if(ppg.getCodigo().equals(next)) {//analisa o codigo da ppg
				
				//retorna a todas as producoes do tipo partmu da ppg requerida
				producoesDaPpgDeEntrada = ppg.retornaListaDePartmu();
			}
		}
		
		//ordena
		Collections.sort(producoesDaPpgDeEntrada);
		
		//faz a impressao como pedido para partmu no comando csv
		for(int i = 0; i < producoesDaPpgDeEntrada.size(); i++) {
			producoesDaPpgDeEntrada.get(i).imprimirPartmuFormatadaParte5();
		}
		
	}

	/**
	 * Funcao que executa o comando csv especifico para outro, imprimindo as caracteristicas
	 * especificas do tipo de producao livro
	 * @param next
	 */
	private void executaComandoCsvParaLivro(String next) {
		System.out.println("Natureza;Titulo;Idioma;Editora;Cidade;ISBN;Paginas");
		
		List<Livro> producoesDaPpgDeEntrada = new ArrayList<Livro>();
		
		//roda toda a lista de pggs com um forit
		for(PPG ppg : this.PPGs.values()) {
			if(ppg.getCodigo().equals(next)) {//analisa o codigo da ppg
				
				//retorna a todas as producoes do tipo livro da ppg requerida
				producoesDaPpgDeEntrada = ppg.retornaListaDeLivro();
			}
		}
		
		//ordena
		Collections.sort(producoesDaPpgDeEntrada);
		
		//faz a impressao como pedido para livro no comando csv
		for(int i = 0; i < producoesDaPpgDeEntrada.size(); i++) {
			producoesDaPpgDeEntrada.get(i).imprimirLivroFormatadaParte5();
		}		
	}

	/**
	 * Funcao que executa o comando csv especifico para outro, imprimindo as caracteristicas
	 * especificas do tipo de producao artpe
	 * @param next
	 */
	private void executaComandoCsvParaArtpe(String next) {
		System.out.println("Natureza;Idioma;Editora;Cidade;Volume;Fasciulo;Serie;ISSN;Paginas");
		
		List<Artpe> producoesDaPpgDeEntrada = new ArrayList<Artpe>();
		
		//roda toda a lista de pggs com um forit
		for(PPG ppg : this.PPGs.values()) {
			if(ppg.getCodigo().equals(next)) {//analisa o codigo da ppg
				
				//retorna a todas as producoes do tipo artpe da ppg requerida
				producoesDaPpgDeEntrada = ppg.retornaListaDeArtpe();
			}
		}
		
		//ordena
		Collections.sort(producoesDaPpgDeEntrada);
		
		//faz a impressao como pedido para artpe no comando csv
		for(int i = 0; i < producoesDaPpgDeEntrada.size(); i++) {
			producoesDaPpgDeEntrada.get(i).imprimirArtpeFormatadaParte5();
		}
		
	}

	/**
	 * Funcao que executa o comando csv especifico para outro, imprimindo as caracteristicas
	 * especificas do tipo de producao artjr
	 * @param next
	 */
	private void executaComandoCsvParaArtjr(String next) {
		System.out.println("Titulo;Idioma;Cidade;Data;ISSN;Paginas");
		
		List<Artjr> producoesDaPpgDeEntrada = new ArrayList<Artjr>();
		
		//roda toda a lista de pggs com um forit
		for(PPG ppg : this.PPGs.values()) {
			if(ppg.getCodigo().equals(next)) {//analisa o codigo da ppg
				
				//retorna a todas as producoes do tipo artjr da ppg requerida
				producoesDaPpgDeEntrada = ppg.retornaListaDeArtjr();
			}
		}
		
		//ordena
		Collections.sort(producoesDaPpgDeEntrada);
		
		//faz a impressao como pedido para artjr no comando csv
		for(int i = 0; i < producoesDaPpgDeEntrada.size(); i++) {
			producoesDaPpgDeEntrada.get(i).imprimirArtjrFormatadaParte5();
		}
	}

	/**
	 * Funcao que executa o comando csv especifico para outro, imprimindo as caracteristicas
	 * especificas do tipo de producao anais
	 * @param next
	 */
	private void executaComandoCsvParaAnais(String next) {
		System.out.println("Natureza;Titulo;Idioma;Evento;Cidade;Paginas");
		
		List<Anais> producoesDaPpgDeEntrada = new ArrayList<Anais>();
		
		for(PPG ppg : this.PPGs.values()) {
			//roda toda a lista de pggs com um forit
			if(ppg.getCodigo().equals(next)) { //analisa o codigo da ppg
				
				//retorna a todas as producoes do tipo anais da ppg requerida
				producoesDaPpgDeEntrada = ppg.retornaListaDeAnais();
			}
		}
		
		//ordena
		Collections.sort(producoesDaPpgDeEntrada);
		
		//faz a impressao como pedido para anais no comando csv
		for(int i = 0; i < producoesDaPpgDeEntrada.size(); i++) {
			producoesDaPpgDeEntrada.get(i).imprimirAnaisFormatadaParte5();
		}
		
	}
	
}
	