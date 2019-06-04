public abstract class Producao {
	
	private String cidade;
	private int quantidadeDePaginas;
	
	public Producao(String cidade) {
		this.cidade = cidade;
	}
	
	public void setQuantidadeDePaginas(int quantidade) {
		this.quantidadeDePaginas = quantidade;
	}
	
	public String getCidade() {
		return this.cidade;
	}
	
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
	
	public int calculaQuantidadeDePaginas(String pagina) {
		if(this.validaPagina(pagina) == true ) {
			int quantidade = Integer.parseInt(pagina);
			return quantidade;
		}
		
		else {
			return -1; //valor de erro
		}
	}
	
	public int calculaQuantidadeDePaginas(String paginaInicial, String paginaFinal) {
		if(this.validaPagina(paginaInicial) == true && this.validaPagina(paginaFinal) == true) {
			int inicial = Integer.parseInt(paginaInicial);
			int fim = Integer.parseInt(paginaFinal);
			
			int soma = fim - inicial + 1;
			
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
	
	public int getQuantidadeDePaginas() {
		return this.quantidadeDePaginas;
	}
	
	public String retornaStringVaziaCasoIntMenorQue1(int numero) {
		if(numero < 1) {
			return "";
		}
		return Integer.toString(numero);
	}
	
}
