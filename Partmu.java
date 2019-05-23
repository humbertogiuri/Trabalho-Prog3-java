
public class Partmu extends Producao {
	
	private String editora;
	private String formacaoInstrumental;
	
	public Partmu(String titulo, String natureza, String idioma, String cidade, String paginaInicial,
			String paginaFinal, String editora, String formacaoInstrumental) {
		super(titulo, natureza, idioma, cidade, paginaInicial, paginaFinal);
		this.editora = editora;
		this.formacaoInstrumental = formacaoInstrumental;
	}
	
	
}
