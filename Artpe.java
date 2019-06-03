import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Artpe extends Producao implements Comparable<Artpe>{

	private String natureza;
	private String idioma;
	private String editora;
	private String volume;
	private String fasciculo;
	private String serie;
	private String ISSN;
	
	public Artpe(String cidade, String natureza, String idioma, String editora, String volume, String fasciculo,
			String serie, String ISSN) {
		super(cidade);
		this.natureza = natureza;
		this.idioma = idioma;
		this.editora = editora;
		try {
			int volumeTeste = Integer.parseInt(volume);
			this.volume = volume;
		}
		catch(NumberFormatException e) {
			this.volume = "";
		}
		try {
			int fasciculoTeste = Integer.parseInt(fasciculo);
			this.fasciculo = fasciculo;
		}
		catch(NumberFormatException e) {
			this.fasciculo = "";
		}
		try{
			int serieTeste = Integer.parseInt(serie);
			this.serie = serie;
		}
		catch(NumberFormatException e) {
			this.serie = "";
		}
		this.ISSN = ISSN;
	}

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
							comp = this.volume.compareTo(o.volume);
							if(comp != 0) {
								return comp;
							}
							
							else {
								comp = this.fasciculo.compareTo(o.fasciculo);
								if(comp != 0) {
									return comp;
								}
								
								else {
									comp = this.serie.compareTo(o.serie);
									if(comp != 0) {
										return comp;
									}
									else {
										comp = this.ISSN.compareTo(o.ISSN);
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
		return this.getQuantidadeDePaginas() - o.getQuantidadeDePaginas();
		
	}

	public void imprimirArtpeFormatadaParte5() {
		if(this.getQuantidadeDePaginas() != -1) {
			System.out.println(this.natureza + ";" + this.idioma
					+ ";" + this.editora + ";" + this.getCidade()
					+ ";" + this.volume + ";" + this.fasciculo + ";" +
					this.serie + ";" + this.ISSN + ";" + this.getQuantidadeDePaginas());
		}
		
		else {
			System.out.println(this.natureza + ";" + this.idioma
					+ ";" + this.editora + ";" + this.getCidade()
					+ ";" + this.volume + ";" + this.fasciculo + ";" +
					this.serie + ";" + this.ISSN + ";");
		}
		
	}
	
	
}


