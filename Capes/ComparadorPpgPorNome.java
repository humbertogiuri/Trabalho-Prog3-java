package Capes;
import java.util.Comparator;

/**
 * Clase usada para comparar um PPG pelo seu nome
 * @author humberto
 *
 */
public class ComparadorPpgPorNome implements Comparator<PPG> {
	
	/**
	 * funcao que recebe duas PPGS e compara seus nomes
	 */
	public int compare(PPG ppg, PPG outroPpg) {
		return ppg.getNome().compareTo(outroPpg.getNome());
	}
}
