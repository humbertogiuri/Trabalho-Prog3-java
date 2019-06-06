package Producoes;

/**
 * classe que define a producao Tradu
 * @author humberto
 *
 */
public class Tradu extends Producao implements Comparable<Tradu>{
	
	private String natureza;
	private String titulo;
	private String idioma;
	private String editora;
	private String idiomaTraducao;
	
	/**
	 * construtor da classe
	 * @param cidade
	 * @param natureza
	 * @param titulo
	 * @param idioma
	 * @param editora
	 * @param idiomaTraducao
	 */
	public Tradu(String cidade, String natureza, String titulo, String idioma, String editora, String idiomaTraducao) {
		super(cidade);
		this.natureza = natureza;
		this.titulo = titulo;
		this.idioma = idioma;
		this.editora = editora;
		this.idiomaTraducao = idiomaTraducao;
	}
	
	/**
	 * Reescrevemos a funcao compareTo para comparar duas anais da maneiro que foi 
	 * determinada pelo trabalho
	 */
	@Override
	public int compareTo(Tradu o) {
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
						comp = this.editora.compareTo(o.editora); {
						if(comp != 0) {
							return comp;
						}
						else {
							comp = this.getCidade().compareTo(o.getCidade());
							if(comp != 0) {
								return comp;
							}
							else {
								comp = this.idiomaTraducao.compareTo(o.idiomaTraducao);
								if(comp != 0) {
									return comp;
								}
							}
						}
						
					}
				}
			}
		}
		return this.getQuantidadeDePaginas() - o.getQuantidadeDePaginas();
	}
	
	/**
	 * funcao que imprimi as caracteristicas da producao numa formatacao determinada
	 */
	public void imprimirTraduFormatadaParte5() {
		System.out.println(this.natureza + ";" + this.titulo
				+ ";" + this.idioma + ";" + this.editora
				+ ";" + this.getCidade() + ";" + this.idiomaTraducao 
				+ ";" + this.retornaStringVaziaCasoIntMenorQue1(getQuantidadeDePaginas()));
		
	}
	
	
}
