import java.util.*;

public class Instituicao implements Comparable<Instituicao>{
	private String nome;
	private String sigla;
	private List<PPG> PPGs = new ArrayList<PPG>();
	
	public Instituicao(String nome, String sigla) {
		this.nome = nome;
		this.sigla = sigla;
	}
	
	public boolean verificaSePpgExisteNaLIsta(PPG ppg) {
		String codigo = ppg.getCodigo();
		for(int i = 0; i < this.PPGs.size(); i++) {
			String codigoPpgNaLista = this.PPGs.get(i).getCodigo();
			if(codigoPpgNaLista.equals(codigo)) {
				return true;
			}
		}
		return false;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void adicionaPPG(PPG ppg) {
		this.PPGs.add(ppg);
	}

	@Override
	public int compareTo(Instituicao o) {
		int comp = this.sigla.compareTo(o.sigla);
		if(comp != 0) {
			return comp;
		}
		
		return this.nome.compareTo(o.nome);
	}
	
	public void imprimirNomeSiglaFormatados() {
		System.out.println("\t- " + this.sigla + "(" + this.nome + ")");
	}
}
