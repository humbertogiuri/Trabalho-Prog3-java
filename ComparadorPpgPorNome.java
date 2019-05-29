import java.util.Comparator;

public class ComparadorPpgPorNome implements Comparator<PPG> {
	public int compare(PPG ppg, PPG outroPpg) {
		return ppg.getNome().compareTo(outroPpg.getNome());
	}
}
