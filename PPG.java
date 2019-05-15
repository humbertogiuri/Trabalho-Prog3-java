import java.util.*;
public class PPG {
	private String codigo;
	private Producao tipoDaProducao;
	private String instituicao;
	private String siglaDaInstituicao;
	private int idSubTipo;
	
	public PPG(String codigo, String instituicao, String siglaDaInstituicao, int idSubTipo) {
		this.codigo = codigo;
		this.instituicao = instituicao;
		this.siglaDaInstituicao = siglaDaInstituicao;
		this.idSubTipo = idSubTipo;
	}
	
	public void setProducaoAnais(String titulo, String natureza, String idioma, String cidade, String paginaInicial, String paginaFinal, String evento) {
		this.tipoDaProducao = new Anais(titulo, natureza, idioma, cidade, paginaInicial, paginaFinal, evento);
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public String getSigladaInstituicao() {
		return this.siglaDaInstituicao;
	}
}
