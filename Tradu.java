
public class Tradu extends Producao {

	private String editora;
	private String idiomaTraducao;
	
	public Tradu(String titulo, String natureza, String idioma, String cidade, String paginaInicial, String paginaFinal,
			String editora, String idiomaTraducao) {
		super(titulo, natureza, idioma, cidade, paginaInicial, paginaFinal);
		this.editora = editora;
		this.idiomaTraducao = idiomaTraducao;
	}
	
	
}
