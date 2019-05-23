
public class Tradu extends Producao {
	
	private String natureza;
	private String titulo;
	private String idioma;
	private String editora;
	private String idiomaTraducao;
	
	public Tradu(String cidade, String natureza, String titulo, String idioma, String editora, String idiomaTraducao) {
		super(cidade);
		this.natureza = natureza;
		this.titulo = titulo;
		this.idioma = idioma;
		this.editora = editora;
		this.idiomaTraducao = idiomaTraducao;
	}
	
	
}
