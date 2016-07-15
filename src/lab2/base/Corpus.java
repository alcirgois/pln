package lab2.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emanuel
 *
 */
public class Corpus {

	private List<String> texto;
	private int divisorDeConj;

	public Corpus(String arquivo) {
		texto = new ArrayList<String>();
		lerArquivo(arquivo);
		divisorDeConj = (int) (texto.size() * 0.8);
	}

	public List<String> getTexto() {
		return texto;
	}

	private void lerArquivo(String arquivo) {
		try {
			BufferedReader leitor = Files.newBufferedReader(Paths.get(arquivo), Charset.defaultCharset());
			String linha = leitor.readLine();
			while (leitor != null) {
				texto.add(linha);
				linha = leitor.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> getConjDeTreinamento() {
		return texto.subList(0, divisorDeConj);
	}

	public List<String> getConjDeTeste() {
		return texto.subList(divisorDeConj, texto.size());
	}
}
