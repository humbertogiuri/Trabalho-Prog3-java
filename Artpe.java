
public class Artpe extends Producao{

	private String natureza;
	private String idioma;
	private String editora;
	private String volume;
	private String fasciculo;
	private String serie;
	private String ISSN;
	
	public Artpe(String cidade, String natureza, String idioma, String editora, String volume, String fasciculo,
			String serie, String iSSN) {
		super(cidade);
		this.natureza = natureza;
		this.idioma = idioma;
		this.editora = editora;
		this.volume = volume;
		this.fasciculo = fasciculo;
		this.serie = serie;
		ISSN = iSSN;
	}
	
	
}


