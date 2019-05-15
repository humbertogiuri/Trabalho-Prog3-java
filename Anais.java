public class Anais extends Producao{
	private String evento;
	
	public Anais(String titulo, String natureza, String idioma, String cidade, String paginaInicial, String paginaFinal, String evento) {
		super(titulo, natureza, idioma, cidade, paginaInicial, paginaFinal);
		this.evento = evento;
	}
	
}
