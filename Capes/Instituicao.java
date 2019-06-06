package Capes;
import java.util.*;

/**
 * classe instituicao, que contem um nome, sigla e uma lista de ppgs
 * ao qual a instituicao esta vinculada
 * @author humberto
 *
 */
public class Instituicao implements Comparable<Instituicao>{
	
	private String nome;
	private String sigla;
	private List<PPG> PPGs = new ArrayList<PPG>();
	
	/**
	 * Construtor da classe
	 * @param nome
	 * @param sigla
	 */
	public Instituicao(String nome, String sigla) {
		this.nome = nome;
		this.sigla = sigla;
	}
	
	/**
	 * Fucao que recebe uma ppg como parametro e analisa se ela pertence 
	 * a lista de ppgs
	 * @param ppg
	 * @return true ou false dependendo se pgg esta na lista
	 */
	public boolean verificaSePpgExisteNaLIsta(PPG ppg) {
		String codigo = ppg.getCodigo();
		for(int i = 0; i < this.PPGs.size(); i++) {
			String codigoPpgNaLista = this.PPGs.get(i).getCodigo();
			if(codigoPpgNaLista.equals(codigo)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * retorna o nome da instituicao
	 * @return String representando nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * funcao que retorna a sigla da instituicao
	 * @return String representado sigla
	 */
	public String getSigla() {
		return sigla;
	}
	
	/**
	 * funcao que adiciona ppg na lista de ppgs
	 * @param ppg
	 */
	public void adicionaPPG(PPG ppg) {
		//analisa se a ppg ja existe na lista, e adiciona caso nao exista
		if(!this.verificaSePpgExisteNaLIsta(ppg)) {
			this.PPGs.add(ppg);
		}
		
	}
	
	/**
	 * Aqui, reescrevemos a funcao compareTo, para fazer a comparacao correta de uma
	 * instituicao, primeiro compara sigla e depois o nome
	 */
	@Override
	public int compareTo(Instituicao o) {
		int comp = this.sigla.compareTo(o.sigla);
		if(comp != 0) {
			return comp;
		}
		
		return this.nome.compareTo(o.nome);
	}
	
	/**
	 * funcao que imprimir o nome e sigla com uma certa formatacao
	 */
	public void imprimirNomeSiglaFormatados() {
		System.out.println("\t- " + this.sigla + " (" + this.nome + ")");
	}
	
	/**
	 * Funcao para ordenar a lista de ppgs
	 */
	public void ordenarPpgs() {
		Collections.sort(this.PPGs, new ComparadorPpgPorNome());
		
	}
	
	/**
	 * funcao para imprimir todas as ppgs da lista com uma certa formatacao
	 */
	public void imprimirPpgsFormatadas() {
		for(PPG ppg : this.PPGs) {
			System.out.println("\t- " + ppg.getNome() + ": " + ppg.retornaQuantidadeDeProducoesNaLista()
					+ " producoes");
		}
	}
}
