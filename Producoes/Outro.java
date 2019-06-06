package Producoes;

/**
 * classe que define a producao Outro
 * @author humberto
 *
 */
public class Outro extends Producao implements Comparable<Outro>{
	
	private String natureza;
	private String idioma;
	private String editora;
	
	public Outro(String cidade, String natureza, String idioma, String editora) {
		super(cidade);
		this.natureza = natureza;
		this.idioma = idioma;
		this.editora = editora;
	}
	
	/**
	 * Reescrevemos a funcao compareTo para comparar duas anais da maneiro que foi 
	 * determinada pelo trabalho
	 */
	@Override
	public int compareTo(Outro o) {
		int comp = this.natureza.compareTo(o.natureza);
		if(comp != 0) {
			return comp;
		}
		
		else {
			comp = this.idioma.compareTo(o.idioma);
			if(comp != 0) {
				return comp;
			}
				
			else {
				comp = this.editora.compareTo(o.editora); 
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
		return this.getQuantidadeDePaginas() - o.getQuantidadeDePaginas();
	}
	
	/**
	 * funcao que imprimi as caracteristicas da producao numa formatacao determinada
	 */
	public void imprimirOutroFormatadaParte5() {
		System.out.println(this.natureza + ";" + this.idioma
				+ ";" + this.editora + ";" + this.getCidade() + ";" 
				+ this.retornaStringVaziaCasoIntMenorQue1(getQuantidadeDePaginas()));
		
	}
	
}
