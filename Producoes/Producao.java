package Producoes;

/**
 * classe Producao que eh uma classe abstrata que sera base para os tipos de producao
 * @author humberto
 *
 */
public abstract class Producao {
	
	private String cidade;
	private int quantidadeDePaginas;
	
	/**
	 * construtor da classe
	 * @param cidade
	 */
	public Producao(String cidade) {
		this.cidade = cidade;
	}
	
	/**
	 * Funcao que define a quantidade de paginas da producao
	 * @param quantidade
	 */
	public void setQuantidadeDePaginas(int quantidade) {
		this.quantidadeDePaginas = quantidade;
	}
	
	/**
	 * funcao que retorna a cidade da producao
	 * @return String cidade
	 */
	public String getCidade() {
		return this.cidade;
	}
	
	/**
	 * funcao que verifica se a pagina eh valida de acordo com o contrato
	 * @param paginaAvaliada
	 * @return true ou false dependendo se a paginas for valida
	 */
	private boolean validaPagina(String paginaAvaliada) {
		try {
			int pagina = Integer.parseInt(paginaAvaliada);
			if(pagina < 0) {
				return false;
			}
		}
		catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Funcao que calcula quantidade de paginas
	 * @param pagina
	 * @return um int que eh a transformacao para inteiro da string parametro caso
	 * ela seja valida ou -1 caso haja erro
	 */
	public int calculaQuantidadeDePaginas(String pagina) {
		if(this.validaPagina(pagina) == true ) {
			int quantidade = Integer.parseInt(pagina);
			return quantidade;
		}
		
		else {
			return -1; //valor de erro
		}
	}
	
	/**
	 * funcao que calcula quantidade de paginas
	 * @param paginaInicial
	 * @param paginaFinal
	 * @return um int que a quantidade de paginas da producao ou -1 caso haja erro
	 */
	public int calculaQuantidadeDePaginas(String paginaInicial, String paginaFinal) {
		if(this.validaPagina(paginaInicial) == true && this.validaPagina(paginaFinal) == true) {
			//converte as strings para inteiro
			int inicial = Integer.parseInt(paginaInicial);
			int fim = Integer.parseInt(paginaFinal);
			
			int soma = fim - inicial + 1;
			
			//verifica as condicoes para que seja valido
			if(soma > 2000) {
				return -1; //valor de erro
			}
			
			else if(fim < inicial) {
				return -1; //valor de erro
			}
			else {
				return soma;
			}
		}
		
		else {
			return -1; //valor de erro
		}
	}
	
	/**
	 * Funcao que retorna a quantidade de paginas
	 * @return int 
	 */
	public int getQuantidadeDePaginas() {
		return this.quantidadeDePaginas;
	}
	
	/**
	 * Funcao que analisa se um inteiro eh menor que 0
	 * @param numero
	 * @return A transformacao do inteiro pra string ou vazio caso seja menor que 0
	 */
	public String retornaStringVaziaCasoIntMenorQue1(int numero) {
		if(numero < 1) {
			return "";
		}
		return Integer.toString(numero);
	}
	
}
