package lab2.base;

import java.io.IOException;
import java.util.List;

import lab2.util.FileManager;

/**
 * @author Emanuel
 *
 */
public class Corpus {

	private List<String> texto;
	private int divisorDeConj;
	private FileManager gestorArq;

	public Corpus(String arquivo) {
		gestorArq = new FileManager();
		try {
			texto = gestorArq.readFile(arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		divisorDeConj = (int) (texto.size() * 0.8);
	}

	public List<String> getTexto() {
		return texto;
	}

	public List<String> getConjDeTreinamento() {
		return texto.subList(0, divisorDeConj);
	}

	public List<String> getConjDeTeste() {
		return texto.subList(divisorDeConj, texto.size());
	}
}
