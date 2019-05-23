
public class Artjr extends Producao {
	
	private String editora;
	private String dataDeLancamento;
	private String ISSN;
	
	public Artjr(String titulo, String natureza, String idioma, String cidade, String paginaInicial,
			String paginaFinal, String editora, String dataDeLancamento, String ISSN) {
		
		super(titulo, natureza, idioma, cidade, paginaInicial, paginaFinal);
		this.editora = editora;
		this.dataDeLancamento = dataDeLancamento;
		this.ISSN = ISSN;
		
		
	}

}
