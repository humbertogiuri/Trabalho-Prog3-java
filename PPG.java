import java.util.*;
public class PPG {
	private String codigo;
	private Producao tipoDaProducao;
	private Instituicao instituicao;
	
	public PPG(String codigo, Instituicao instituicao, Producao producao) {
		this.codigo = codigo;
		this.tipoDaProducao = producao;
		this.instituicao = instituicao;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
}
