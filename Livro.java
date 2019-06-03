
public class Livro extends Producao implements Comparable<Livro>{
	private String natureza;
	private String idioma;
	private String editora;
	private String ISBN;
	private String titulo;
	
	public Livro(String cidade, String natureza, String idioma, String editora, String iSBN, String titulo) {
		super(cidade);
		this.natureza = natureza;
		this.idioma = idioma;
		this.editora = editora;
		this.ISBN = iSBN;
		this.titulo = titulo;
	}

	@Override
	public int compareTo(Livro o) {
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
								comp = this.ISBN.compareTo(o.ISBN);
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
	
	public void imprimirLivroFormatadaParte5() {
		if(this.getQuantidadeDePaginas() != -1) {
			System.out.println(this.natureza + ";" + this.titulo
					+ ";" + this.idioma + ";" + this.editora
					+ ";" + this.getCidade() + ";" + 
					this.ISBN + ";" + this.getQuantidadeDePaginas());
		}
		
		else {
			System.out.println(this.natureza + ";" + this.titulo
					+ ";" + this.idioma + ";" + this.editora
					+ ";" + this.getCidade() + ";" + 
					this.ISBN + ";" + this.getQuantidadeDePaginas());
		}
		
	}

}
