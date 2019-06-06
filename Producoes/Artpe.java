package Producoes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * classe que define a producao Artpe
 * @author humberto
 *
 */
public class Artpe extends Producao implements Comparable<Artpe>{

	private String natureza;
	private String idioma;
	private String editora;
	private int volume;
	private int fasciculo;
	private int serie;
	private String ISSN;
	
	/**
	 * construtor da classe
	 * @param cidade
	 * @param natureza
	 * @param idioma
	 * @param editora
	 * @param volume
	 * @param fasciculo
	 * @param serie
	 * @param ISSN
	 */
	public Artpe(String cidade, String natureza, String idioma, String editora, String volume, String fasciculo,
			String serie, String ISSN) {
		super(cidade);
		this.natureza = natureza;
		this.idioma = idioma;
		this.editora = editora;
		try {
			this.volume = Integer.parseInt(volume);
		}
		catch(NumberFormatException e) {
			this.volume = 0;
		}
		try {
			this.fasciculo = Integer.parseInt(fasciculo);
		}
		catch(NumberFormatException e) {
			this.fasciculo = 0;
		}
		try{
			this.serie = Integer.parseInt(serie);
		}
		catch(NumberFormatException e) {
			this.serie = 0;
		}
		this.ISSN = ISSN;
	}
	
	/**
	 * Reescrevemos a funcao compareTo para comparar duas anais da maneiro que foi 
	 * determinada pelo trabalho
	 */
	@Override
	public int compareTo(Artpe o) {
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
						comp = this.getCidade().compareTo(o.getCidade()); {
						if(comp != 0) {
							return comp;
						}
						else {
							comp = this.volume - o.volume;
							if(comp != 0) {
								return comp;
							}
							
							else {
								comp = this.fasciculo - o.fasciculo;
								if(comp != 0) {
									return comp;
								}
								
								else {
									comp = this.serie - o.serie;
									if(comp != 0) {
										return comp;
									}
									else {
										comp = this.getQuantidadeDePaginas() - o.getQuantidadeDePaginas();
										if(comp != 0) {
											return comp;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return this.ISSN.compareTo(o.ISSN);
		
	}
	
	/**
	 * funcao que imprimi as caracteristicas da producao numa formatacao determinada
	 */
	public void imprimirArtpeFormatadaParte5() {
		System.out.println(this.natureza + ";" + this.idioma
				+ ";" + this.editora + ";" + this.getCidade()
				+ ";" + this.retornaStringVaziaCasoIntMenorQue1(this.volume) + ";" 
				+ this.retornaStringVaziaCasoIntMenorQue1(this.fasciculo) + ";" +
				this.retornaStringVaziaCasoIntMenorQue1(this.serie) 
				+ ";" + this.ISSN + ";" + this.retornaStringVaziaCasoIntMenorQue1(this.getQuantidadeDePaginas()));
	}
	
	
}


