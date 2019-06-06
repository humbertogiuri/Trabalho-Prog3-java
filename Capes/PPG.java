package Capes;
import java.util.*;

import Producoes.Anais;
import Producoes.Artjr;
import Producoes.Artpe;
import Producoes.Livro;
import Producoes.Outro;
import Producoes.Partmu;
import Producoes.Producao;
import Producoes.Tradu;

/**
 * classe PPG que contem um codigo, um nome, uma lista de producoes e 
 * uma lista de instituicoes 
 * @author humberto
 *
 */
public class PPG implements Comparable<PPG> {
	
	private String codigo;
	private String nome;
	private List<Producao> producoes = new ArrayList<Producao>();
	private List<Instituicao> instituicoes = new ArrayList<Instituicao>();
	
	/**
	 * Construtor da classe
	 * @param codigo
	 * @param nome
	 */
	public PPG(String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	/**
	 * Funcao que adiciona uma instituicao na lista de instituicoes, verificando
	 * primeiro se ela ja esta na lista antes de adiconar
	 * @param instituicao
	 */
	public void adicionaInstituicaNaLista(Instituicao instituicao) {
		if(!this.verificaSeInstituicaoExisteNaLista(instituicao)) {
			this.instituicoes.add(instituicao); //adiciona caso nao esteja na lista
		}
	}
	
	/**
	 * funcao que adiciona uma producao na lista de producoes, sem verificao se ja existe
	 * @param producao
	 */
	public void adicionaProducaoNaLista(Producao producao) {
		this.producoes.add(producao);
	}
	
	/**
	 * Funcao que analisa se uma instituicao esta na lista de institucoes
	 * @param instiuicao
	 * @return false ou true dependendo se instituicao esta na lista
	 */
	private boolean verificaSeInstituicaoExisteNaLista(Instituicao instiuicao) {
		//concatena nome e sigla da instuicao de entrada
		String instituicaoNomeSigla = instiuicao.getNome() + instiuicao.getSigla();
		
		//roda toda a lista de instituicoes
		for(int i = 0; i < this.instituicoes.size(); i++) {
			
			//concatena nome e lista da instituicao que esta na lista na posicao i
			String instituicaoLista = this.instituicoes.get(i).getNome() + this.instituicoes.get(i).getSigla();
			if(instituicaoLista.equals(instituicaoNomeSigla)) {
				//verifica se eh igual e retorna true caso seja
				return true;
			}
		}
		//caso na seja retorna false
		return false;
	}
	
	/**
	 * Funcao que retorna o tamanho da lista de producoes
	 * @return um inteiro representado o tamanho da lista
	 */
	public int retornaQuantidadeDeProducoesNaLista() {
		return this.producoes.size();
	}
	
	/**
	 * retorna o codigo da ppg
	 * @return String que equivale ao codigo
	 */
	public String getCodigo() {
		return this.codigo;
	}
	
	/**
	 * funcao que roda todas as producoes da lista de producoes e retorna um vetor 
	 * contendo a quantidade de producoes que possuem paginas validas, de acordo
	 * com o contrato, e a soma dessa paginas
	 * @return vetor de inteiros
	 */
	public int[] retornaQuantidadeDePaginasProdValidas() {
		int soma = 0;
		int producoesValidas = 0;
		//roda toda a lista fazendo a verificacao e os incrementos
		for(int i = 0; i < this.producoes.size(); i++) {
			if(this.producoes.get(i).getQuantidadeDePaginas() != -1) {
				soma += this.producoes.get(i).getQuantidadeDePaginas();
				producoesValidas++;
			}
		}
		//cria o vetor e faz as atribuicoes
		//primeiro posicao a soma de paginas
		//e segunda posicao quantidade de producoes validas
		int[] quantidadeEValidas = new int[2];
		quantidadeEValidas[0] = soma; 
		quantidadeEValidas[1] = producoesValidas;
		return quantidadeEValidas;
	}
	
	/**
	 * retorna tamanho da lista de instituicoes
	 * @return int representado tamanho da lista de instituicoes
	 */
	public int retornaTamanhoDaListaDeInstituicoes() {
		return this.instituicoes.size();
	}
	
	/**
	 * aqui reescrevemos a funcao compareTo para comparar a pgg do nosso jeito
	 */
	@Override
	public int compareTo(PPG o) {
		int comp = this.codigo.compareTo(o.codigo);
		if(comp != 0) {
			return comp;
		}
		
		return this.nome.compareTo(o.nome);
	}
	
	/**
	 * Funcao que ordena a lista de instituicoes
	 */
	public void ordernarInstituicoes() {
		Collections.sort(this.instituicoes);
		
	}
	
	/**
	 * retorna o nome da ppg
	 * @return String representando o nome
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Funcao que roda toda a lista de instituicoes e imprimi em um certa formatacao
	 */
	public void imprimirInstituicoes() {
		for(int i = 0; i < this.instituicoes.size(); i++) {
			instituicoes.get(i).imprimirNomeSiglaFormatados();
		}
	}
	
	/**
	 * Funcao que verifica se a ppg esta ligada a mais de uma institucao
	 * @return true ou false dependendo se ppg esta em rede
	 */
	public boolean verificaPPGsEmRede() {
		if(this.instituicoes.size() > 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Funcao que retorna um vetor de 7 posicoes onde cada posicao faz referencia a 
	 * um tipo de producao, e retorna a quintidade de todos os tipos de producoes
	 * que estao na lista de producoes
	 * @return vetor de inteiros com a quantidade de producoes de cada tipo na lista
	 */
	private int[] retornaQuantidadeDasProducoes() {
		int[] tiposDeProducoes = new int[7];
		//roda toda lista de procoes e verifica os tipos das producoes
		//e vai somando um cada tipo de producoe que acha
		for(Producao producao : this.producoes) {
			if(producao instanceof Anais) {
				tiposDeProducoes[0]++;
			}
			
			else if(producao instanceof Artjr) {
				tiposDeProducoes[1]++;
			}
			
			else if(producao instanceof Artpe) {
				tiposDeProducoes[2]++;
			}
			
			else if(producao instanceof Livro) {
				tiposDeProducoes[3]++;
			}
			
			else if(producao instanceof Partmu) {
				tiposDeProducoes[4]++;
			}
			
			else if(producao instanceof Tradu) {
				tiposDeProducoes[5]++;
			}
			
			else if(producao instanceof Outro) {
				tiposDeProducoes[6]++;
			}
		}
		//retorna o vetor tiposDeProducao onde na sua primeira posicao faz referencia ao tipoa anais
		//a segunda a artjr, a terceira a artpe, a quarta a livro, a quinta partmu
		//a sexta tradu e a setima a outro
		return tiposDeProducoes;
	}
	
	/**
	 * funcao que usa funcao acima para saber quantas producoes de cada tipo existe e 
	 * imprimi em uma formatacao pedida pelo comando ppg
	 */
	public void imprimirDadosProducoesFormstadosParaComandoPpg() {
		System.out.printf("Programa: %s\n", this.getNome());
		System.out.println("Instituicoes:");
		this.ordernarInstituicoes();
		this.imprimirInstituicoes();
		System.out.printf("\n");
		
		int vetorDosTiposDeProducoes[] = this.retornaQuantidadeDasProducoes();
		System.out.println("Quantidade de producoes por tipo:");
		System.out.println("\t- Artigos em anais de eventos: " + vetorDosTiposDeProducoes[0]);
		System.out.println("\t- Artigos em jornais e revistas: " + vetorDosTiposDeProducoes[1]);
		System.out.println("\t- Artigos em periodicos cientificos: " + vetorDosTiposDeProducoes[2]);
		System.out.println("\t- Livros: " + vetorDosTiposDeProducoes[3]);
		System.out.println("\t- Partituras musicais: " + vetorDosTiposDeProducoes[4]);
		System.out.println("\t- Traducoes: " + vetorDosTiposDeProducoes[5]);
		System.out.println("\t- Outros: " + vetorDosTiposDeProducoes[6]);
		System.out.printf("\n");
		
		int paginas[] = this.retornaQuantidadeDePaginasProdValidas();
		
		System.out.print("Total de paginas produzidas pelo PPG: " + paginas[0]);
		
	}
	
	/**
	 * Funcao que cria cria uma lista de producoes do tipo anais e roda toda a
	 * lista de producoes da pgg analisando quando a producao for anais, caso
	 * seja ela adiciona nessa lista criada e depois retorna a lista
	 * @return lista de anais 
	 */
	public List<Anais> retornaListaDeAnais() {
		List<Anais> listaDeAnais = new ArrayList<Anais>();
		for(int i = 0; i < this.producoes.size(); i++) {

			if(this.producoes.get(i) instanceof Anais) {
				if(listaDeAnais.size() == 0) {
					listaDeAnais.add((Anais) this.producoes.get(i));
				}
				else {
					int contador = 0;
					for(int j = 0; j < listaDeAnais.size(); j++) {
						
						Anais anais = (Anais)this.producoes.get(i);
						
						if(anais.compareTo(listaDeAnais.get(j)) == 0) {
							contador++;
						}
					}
					if(contador == 0) {
						listaDeAnais.add((Anais) this.producoes.get(i));
					}
				}
			}
		}
		return listaDeAnais;
	}
	
	/**
	 * Funcao que cria cria uma lista de producoes do tipo artjr e roda toda a
	 * lista de producoes da pgg analisando quando a producao for artjr, caso
	 * seja ela adiciona nessa lista criada e depois retorna a lista
	 * @return lista de artjr
	 */
	public List<Artjr> retornaListaDeArtjr() {
		List<Artjr> listaDeArtjr = new ArrayList<Artjr>();
		for(int i = 0; i < this.producoes.size(); i++) {

			if(this.producoes.get(i) instanceof Artjr) {
				if(listaDeArtjr.size() == 0) {
					listaDeArtjr.add((Artjr) this.producoes.get(i));
				}
				else {
					int contador = 0;
					for(int j = 0; j < listaDeArtjr.size(); j++) {
						
						Artjr Artjr = (Artjr)this.producoes.get(i);
						
						if(Artjr.compareTo(listaDeArtjr.get(j)) == 0) {
							contador++;
						}
					}
					if(contador == 0) {
						listaDeArtjr.add((Artjr) this.producoes.get(i));
					}
				}
			}
		}
		return listaDeArtjr;
	}
	
	/**
	 * Funcao que cria cria uma lista de producoes do tipo artpe e roda toda a
	 * lista de producoes da pgg analisando quando a producao for artpe, caso
	 * seja ela adiciona nessa lista criada e depois retorna a lista
	 * @return lista de artpe
	 */
	public List<Artpe> retornaListaDeArtpe() {
		
		List<Artpe> listaDeArtpe = new ArrayList<Artpe>();
		for(int i = 0; i < this.producoes.size(); i++) {

			if(this.producoes.get(i) instanceof Artpe) {
				if(listaDeArtpe.size() == 0) {
					listaDeArtpe.add((Artpe) this.producoes.get(i));
				}
				else {
					int contador = 0;
					for(int j = 0; j < listaDeArtpe.size(); j++) {
						
						Artpe Artpe = (Artpe)this.producoes.get(i);
						
						if(Artpe.compareTo(listaDeArtpe.get(j)) == 0) {
							contador++;
						}
					}
					if(contador == 0) {
						listaDeArtpe.add((Artpe) this.producoes.get(i));
					}
				}
			}
		}
		return listaDeArtpe;	
	}
	
	/**
	 * Funcao que cria cria uma lista de producoes do tipo livro e roda toda a
	 * lista de producoes da pgg analisando quando a producao for livro, caso
	 * seja ela adiciona nessa lista criada e depois retorna a lista
	 * @return lista de livro
	 */
	public List<Livro> retornaListaDeLivro() {
		List<Livro> listaDeLivro = new ArrayList<Livro>();
		for(int i = 0; i < this.producoes.size(); i++) {

			if(this.producoes.get(i) instanceof Livro) {
				if(listaDeLivro.size() == 0) {
					listaDeLivro.add((Livro) this.producoes.get(i));
				}
				else {
					int contador = 0;
					for(int j = 0; j < listaDeLivro.size(); j++) {
						
						Livro livro = (Livro)this.producoes.get(i);
						
						if(livro.compareTo(listaDeLivro.get(j)) == 0) {
							contador++;
						}
					}
					if(contador == 0) {
						listaDeLivro.add((Livro) this.producoes.get(i));
					}
				}
			}
		}
		return listaDeLivro;
	}
	
	/**
	 * Funcao que cria cria uma lista de producoes do tipo partmu e roda toda a
	 * lista de producoes da pgg analisando quando a producao for partmu, caso
	 * seja ela adiciona nessa lista criada e depois retorna a lista
	 * @return lista de partmu
	 */
	public List<Partmu> retornaListaDePartmu() {
		List<Partmu> listaDePartmu = new ArrayList<Partmu>();
		for(int i = 0; i < this.producoes.size(); i++) {

			if(this.producoes.get(i) instanceof Partmu) {
				if(listaDePartmu.size() == 0) {
					listaDePartmu.add((Partmu) this.producoes.get(i));
				}
				else {
					int contador = 0;
					for(int j = 0; j < listaDePartmu.size(); j++) {
						
						Partmu partmu = (Partmu)this.producoes.get(i);
						
						if(partmu.compareTo(listaDePartmu.get(j)) == 0) {
							contador++;
						}
					}
					if(contador == 0) {
						listaDePartmu.add((Partmu) this.producoes.get(i));
					}
				}
			}
		}
		return listaDePartmu;
	}
	
	/**
	 * Funcao que cria cria uma lista de producoes do tipo tradu e roda toda a
	 * lista de producoes da pgg analisando quando a producao for tradu, caso
	 * seja ela adiciona nessa lista criada e depois retorna a lista
	 * @return lista de tradu
	 */
	public List<Tradu> retornaListaDeTradu() {
		List<Tradu> listaDeTradu = new ArrayList<Tradu>();
		for(int i = 0; i < this.producoes.size(); i++) {

			if(this.producoes.get(i) instanceof Tradu) {
				if(listaDeTradu.size() == 0) {
					listaDeTradu.add((Tradu) this.producoes.get(i));
				}
				else {
					int contador = 0;
					for(int j = 0; j < listaDeTradu.size(); j++) {
						
						Tradu tradu = (Tradu)this.producoes.get(i);
						
						if(tradu.compareTo(listaDeTradu.get(j)) == 0) {
							contador++;
						}
					}
					if(contador == 0) {
						listaDeTradu.add((Tradu) this.producoes.get(i));
					}
				}
			}
		}
		return listaDeTradu;
	}
	
	/**
	 * Funcao que cria cria uma lista de producoes do tipo outro e roda toda a
	 * lista de producoes da pgg analisando quando a producao for outro, caso
	 * seja ela adiciona nessa lista criada e depois retorna a lista
	 * @return lista de outro
	 */
	public List<Outro> retornaListaDeOutro() {
		List<Outro> listaDeOutro = new ArrayList<Outro>();
		for(int i = 0; i < this.producoes.size(); i++) {

			if(this.producoes.get(i) instanceof Outro) {
				if(listaDeOutro.size() == 0) {
					listaDeOutro.add((Outro) this.producoes.get(i));
				}
				else {
					int contador = 0;
					for(int j = 0; j < listaDeOutro.size(); j++) {
						
						Outro outro = (Outro)this.producoes.get(i);
						
						if(outro.compareTo(listaDeOutro.get(j)) == 0) {
							contador++;
						}
					}
					if(contador == 0) {
						listaDeOutro.add((Outro) this.producoes.get(i));
					}
				}
			}
		}
		return listaDeOutro;
	}
}
