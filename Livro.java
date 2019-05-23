
public class Livro extends Producao {
	
	private String editora;
	private String ISBN;
	
	public Livro(String titulo, String natureza, String idioma, String cidade, String paginaInicial,
			String paginaFinal, String editora, String ISBN) {
		super(titulo, natureza, idioma, cidade, paginaInicial, paginaFinal);
		this.editora = editora;
		this.ISBN = ISBN;
	}

}
