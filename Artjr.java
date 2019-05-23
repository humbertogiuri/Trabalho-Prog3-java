
public class Artjr extends Producao {
	
	private String dataDePublicacao;
	private String ISSN;
	
	public Artjr(String cidade, String dataDePublicacao, String iSSN) {
		super(cidade);
		this.dataDePublicacao = dataDePublicacao;
		ISSN = iSSN;
	}
		
}
