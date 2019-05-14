import java.util.*;
public class PPG {
	private String codigo;
	private Producao tipoDaProducao;
	private String instituicao;
	private String siglaDaInstituicao;
	
	public PPG(String codigo, String instituicao, String siglaDaInstituicao) {
		this.codigo = codigo;
		this.instituicao = instituicao;
		this.siglaDaInstituicao = siglaDaInstituicao;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public String getSigladaInstituicao() {
		return this.siglaDaInstituicao;
	}
}
