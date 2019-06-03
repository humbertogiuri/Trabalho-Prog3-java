
public class Partmu extends Producao implements Comparable<Partmu> {
	
	private String natureza;
	private String editora;
	private String formacaoInstrumental;
	
	public Partmu(String cidade, String natureza, String editora, String formacaoInstrumental) {
		super(cidade);
		this.natureza = natureza;
		this.editora = editora;
		this.formacaoInstrumental = formacaoInstrumental;
	}

	public int compareTo(Partmu o) {
		int comp = this.natureza.compareTo(o.natureza);
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
					else {
						comp = this.formacaoInstrumental.compareTo(o.formacaoInstrumental); {
						if(comp != 0) {
							return comp;
						}
					}
				}
			}
		}
		return this.getQuantidadeDePaginas() - o.getQuantidadeDePaginas();
	}

	public void imprimirPartmuFormatadaParte5() {
		if(this.getQuantidadeDePaginas() != -1) {
			System.out.println(this.natureza + ";" + this.editora
					+ ";" + this.getCidade() + ";" + this.formacaoInstrumental
					+ ";" + this.getQuantidadeDePaginas());
		}
		
		else {
			System.out.println(this.natureza + ";" + this.editora
					+ ";" + this.getCidade() + ";" + this.formacaoInstrumental
					+ ";");
		}
		
	}
	
	
}
