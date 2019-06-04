public class Anais extends Producao implements Comparable<Anais>{
	
	private String natureza;
	private String titulo;
	private String idioma;
	private String evento;
	public Anais(String cidade, String natureza, String titulo, String idioma, String evento) {
		super(cidade);
		this.natureza = natureza;
		this.titulo = titulo;
		this.idioma = idioma;
		this.evento = evento;
	}
	
	public void imprimirAnaisFormatadaParte5() {
		System.out.println(this.natureza + ";" + this.titulo
				+ ";" + this.idioma + ";" + this.evento
				+ ";" + this.getCidade() + ";" 
				+ this.retornaStringVaziaCasoIntMenorQue1(this.getQuantidadeDePaginas()));
		
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	@Override
	public int compareTo(Anais o) {
		int comp = this.natureza.compareTo(o.natureza);
		if(comp != 0) {
			return comp;
		}
		
		else {
			comp = this.titulo.compareTo(o.titulo);
				if(comp != 0) {
					return comp;
				}
				
				else {
					comp = this.idioma.compareTo(o.idioma); 
					if(comp != 0) {
						return comp;
					}
					else {
						comp = this.evento.compareTo(o.evento); {
						if(comp != 0) {
							return comp;
						}
						else {
							comp = this.getCidade().compareTo(o.getCidade());
							if(comp != 0) {
								return comp;
							}
						}
					}
				}
			}
		}
		return this.getQuantidadeDePaginas() - o.getQuantidadeDePaginas();
	}
	
}
