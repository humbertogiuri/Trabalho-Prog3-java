
public class Livro extends Producao {
	private String natureza;
	private String idioma;
	private String editora;
	private String ISBN;
	
	public Livro(String cidade, String natureza, String idioma, String editora, String iSBN) {
		super(cidade);
		this.natureza = natureza;
		this.idioma = idioma;
		this.editora = editora;
		ISBN = iSBN;
	}
	
	

}
