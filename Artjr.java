import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Artjr extends Producao implements Comparable<Artjr>{
	
	private String titulo;
	private String idioma;
	private String dataDePublicacao;
	private String ISSN;
	
	public Artjr(String titulo, String idioma, String cidade, String dataDePublicacao, String iSSN) {
		
		super(cidade);
		this.titulo = titulo;
		this.idioma = idioma;
		this.dataDePublicacao = dataDePublicacao;
		ISSN = iSSN;
	}
	
	@Override
	public int compareTo(Artjr o) {
		int comp = this.titulo.compareTo(o.titulo);
		if(comp != 0) {
			return comp;
		}
		
		else {
			comp = this.idioma.compareTo(o.idioma);
				if(comp != 0) {
					return comp;
				}
				
				else {
					comp = this.getCidade().compareTo(o.getCidade()); 
					if(comp != 0) {
						return comp;
					}
					else {
						String formato = "dd/MM/yyyy";
						Date data1 = null;
						Date data2 = null;
						try {
							data1 = new SimpleDateFormat(formato).parse(this.dataDePublicacao);
							data2 = new SimpleDateFormat(formato).parse(o.dataDePublicacao);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						comp = data1.compareTo(data2); {
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
		return this.getQuantidadeDePaginas() - o.getQuantidadeDePaginas();
	}
	
	public void imprimirArtjrFormatadaParte5() {
		if(this.getQuantidadeDePaginas() == -1) {
			System.out.println(this.titulo + ";" + this.idioma
					+ ";" + this.getCidade() + ";" + this.dataDePublicacao
					+ ";" + this.ISSN + ";");
		}
		
		else {
			System.out.println(this.titulo + ";" + this.idioma
					+ ";" + this.getCidade() + ";" + this.dataDePublicacao
					+ ";" + this.ISSN + ";" + this.getQuantidadeDePaginas());
		}
		
	}
		
}
