
public class Outro extends Producao {
	
	private String natureza;
	private String idioma;
	private String editora;
	
	public Outro(String cidade, String natureza, String idioma, String editora) {
		super(cidade);
		this.natureza = natureza;
		this.idioma = idioma;
		this.editora = editora;
	}
	
}
