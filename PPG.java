import java.util.*;
public class PPG {
	
	private String codigo;
	private List<Producao> producoes = new ArrayList<Producao>();
	private List<Instituicao> instituicoes = new ArrayList<Instituicao>();
	
	
	public PPG(String codigo) {
		this.codigo = codigo;
	}
	
	public void adicionaInstituicaNaLista(Instituicao instituicao) {
		if(!this.verificaSeInstituicaoExisteNaLista(instituicao)) {
			this.instituicoes.add(instituicao);
		}
	}
	
	public void adicionaProducaoNaLista(Producao producao) {
		this.producoes.add(producao);
	}
	
	private boolean verificaSeInstituicaoExisteNaLista(Instituicao instiuicao) {
		String instituicaoNomeSigla = instiuicao.getNome() + instiuicao.getSigla();
		for(int i = 0; i < this.instituicoes.size(); i++) {
			String instituicaoLista = this.instituicoes.get(i).getNome() + this.instituicoes.get(i).getSigla();
			if(instituicaoLista.equals(instituicaoNomeSigla)) {
				return true;
			}
		}
		return false;
	}
	
	
	public int retornaQuantidadeDeProducoesNaLista() {
		return this.producoes.size();
	}
	
	
	public static int hashCode(String codigo) {
		return codigo.hashCode();
	}
	
	@Override
	public int hashCode() {
		return PPG.hashCode(this.codigo);
	}


	@Override
	public boolean equals(Object obj) {
		PPG novo = (PPG) obj;
		if(this.codigo.equals(novo.codigo)) {
			return true;
		}
		return false;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public int[] retornaQuantidadeDePaginasProdValidas() {
		int soma = 0;
		int producoesValidas = 0;
		for(int i = 0; i < this.producoes.size(); i++) {
			if(this.producoes.get(i).getQuantidadeDePaginas() != -1) {
				soma += this.producoes.get(i).getQuantidadeDePaginas();
				producoesValidas++;
			}
		}
		int[] quantidadeEValidas = new int[2];
		quantidadeEValidas[0] = soma;
		quantidadeEValidas[1] = producoesValidas;
		return quantidadeEValidas;
	}

}
