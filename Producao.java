public abstract class Producao {
	private String titulo;
	private String natureza;
	private String idioma;
	private String cidade;
	private String paginaInicial;
	private String paginaFinal;
	private int quantidadeDePaginas;
	
	public Producao(String titulo, String natureza, String idioma, String cidade, String paginaInicial, String paginaFinal) {
		this.titulo = titulo;
		this.natureza = natureza;
		this.idioma = idioma;
		this.cidade = cidade;
		this.paginaInicial = paginaInicial;
		this.paginaFinal = paginaFinal;
	}
	
	private boolean validaPaginaInicial() {
		try {
			int pagina = Integer.parseInt(this.paginaInicial);
			if(pagina < 0) {
				return false;
			}
		}
		catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	private boolean validaPaginaFinal() {
		try {
			int pagina = Integer.parseInt(this.paginaFinal);
			if(pagina < 0) {
				return false;
			}
		}
		catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	private void calculaQuantidadeDePaginas() {
		if(this.validaPaginaInicial() == true && this.validaPaginaFinal() == true) {
			int paginaInicial = Integer.parseInt(this.paginaInicial);
			int paginaFinal = Integer.parseInt(this.paginaFinal);
			
			int soma = paginaFinal - paginaInicial + 1;
			
			if(soma > 2000) {
				this.quantidadeDePaginas = -1; //valor de erro
			}
			
			else if(paginaFinal < paginaInicial) {
				this.quantidadeDePaginas = -1; //valor de erro
			}
			else {
				this.quantidadeDePaginas = soma;
			}
		}
		
		else {
			this.quantidadeDePaginas = -1; //valor de erro
		}
	}
	
	public int getQuantidadeDePaginas() {
		this.calculaQuantidadeDePaginas();
		return this.quantidadeDePaginas;
	}
	
}
