package lab2.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Emanuel
 *
 */
public class GeradorDeTxt {
	private FileManager gestorArq;
	private Random aleatorio;
	private final String ARQUIVO = "res/lab2/teste.txt";
	private char[] caracteres = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ¡…Õ”⁄√’¬ Œ‘€¿»Ã“Ÿ«".toCharArray();

	public GeradorDeTxt() {
		gestorArq = new FileManager();
		aleatorio = new Random();
		gerarTexto();
	}

	private void gerarTexto() {
		List<String> texto = new ArrayList<String>();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < gerarNumLinhas(); i++) {
			for (int j = 0; j < gerarNumPalavras(); j++) {
				buffer.append(gerarPalavra());
				if (!(j < gerarNumPalavras() - 1)) buffer.append(" ");
			}
			texto.add(buffer.toString());
		}
		try {
			gestorArq.writeToFile(ARQUIVO, texto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String gerarPalavra() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < gerarTamPalavra(); i++) {
			int index = aleatorio.nextInt(caracteres.length);
			buffer.append(caracteres[index]);
		}
		return buffer.toString();
	}

	private int gerarNumLinhas() {
		return 10 + aleatorio.nextInt(90);
	}

	private int gerarNumPalavras() {
		return 10 + aleatorio.nextInt(10);
	}

	private int gerarTamPalavra() {
		return 1 + aleatorio.nextInt(9);
	}

	public static void main(String[] args) {
		new GeradorDeTxt();
	}
}
