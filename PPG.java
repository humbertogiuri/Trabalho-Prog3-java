import java.util.*;
public class PPG implements Comparable<PPG> {
	
	private String codigo;
	private String nome;
	private List<Producao> producoes = new ArrayList<Producao>();
	private List<Instituicao> instituicoes = new ArrayList<Instituicao>();
	
	
	public PPG(String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
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
	
	public int retornaTamanhoDaListaDeInstituicoes() {
		return this.instituicoes.size();
	}

	@Override
	public int compareTo(PPG o) {
		return this.codigo.compareTo(o.codigo);
	}

	public void ordernarInstituicoes() {
		Collections.sort(this.instituicoes);
		
	}

	public String getNome() {
		return this.nome;
	}
	
	public void imprimirInstituicoes() {
		for(int i = 0; i < this.instituicoes.size(); i++) {
			instituicoes.get(i).imprimirNomeSiglaFormatados();
		}
	}
	
	public boolean verificaPPGsEmRede() {
		if(this.instituicoes.size() > 1) {
			return true;
		}
		return false;
	}
	
	public int[] retornaQuantidadeDasProducoes() {
		int[] tiposDeProducoes = new int[7];
		for(Producao producao : this.producoes) {
			if(producao instanceof Anais) {
				tiposDeProducoes[0]++;
			}
			
			else if(producao instanceof Artjr) {
				tiposDeProducoes[1]++;
			}
			
			else if(producao instanceof Artpe) {
				tiposDeProducoes[2]++;
			}
			
			else if(producao instanceof Livro) {
				tiposDeProducoes[3]++;
			}
			
			else if(producao instanceof Partmu) {
				tiposDeProducoes[4]++;
			}
			
			else if(producao instanceof Tradu) {
				tiposDeProducoes[5]++;
			}
			
			else if(producao instanceof Outro) {
				tiposDeProducoes[6]++;
			}
		}
		
		return tiposDeProducoes;
	}
}
