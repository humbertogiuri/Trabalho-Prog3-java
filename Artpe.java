
public class Artpe extends Producao{

	private String editora;
	private String volume;
	private String fasciculo;
	private String serie;
	private String ISSN;
	
	public Artpe(String titulo, String natureza, String idioma, String cidade, String paginaInicial,
			String paginaFinal, String editora, String volume, String fasciculo, String serie, String ISSN) {
		super(titulo, natureza, idioma, cidade, paginaInicial, paginaFinal);
		this.editora = editora;
		this.fasciculo = fasciculo;
		this.serie = serie;
		this.ISSN = ISSN;
	}

}
