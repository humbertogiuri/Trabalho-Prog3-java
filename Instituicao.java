import java.util.*;

public class Instituicao {
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
}
