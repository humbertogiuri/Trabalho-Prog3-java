public abstract class Producao {
	private String titulo;
	private String natureza;
	private String idioma;
	private String cidade;
	private int paginaInicial;
	private int paginaFinal;
	private int quantidadeDePaginas;
	
	public Producao(String titulo, String natureza, String idioma, String cidade) {
		this.titulo = titulo;
		this.natureza = natureza;
		this.idioma = idioma;
		this.cidade = cidade;
	}
	
	
}
