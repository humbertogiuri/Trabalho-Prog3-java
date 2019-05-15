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
	
}
