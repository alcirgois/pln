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
	private char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase().toCharArray();
	private char[] letrasEspeciais = "¡…Õ”⁄√’¬ Œ‘€¿»Ã“Ÿ«".toLowerCase().toCharArray();
	private char[] numeros = "0123456789".toCharArray();

	public GeradorDeTxt() {
		gestorArq = new FileManager();
		aleatorio = new Random();
		gerarTexto();
	}

	private void gerarTexto() {
		List<String> texto = new ArrayList<String>();
		int numLinhas = 10 + aleatorio.nextInt(91);
		System.out.println("N˙mero de linhas  = " + numLinhas);
		for (int i = 0; i < numLinhas; i++) {
			System.out.println("==========Linha "+i+"==========");
			texto.add(gerarLinha());
			System.out.println("============================");
		}
		try {
			gestorArq.writeToFile(ARQUIVO, texto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String gerarLinha() {
		StringBuffer buffer = new StringBuffer();
		int qtd = 5 + aleatorio.nextInt(6);
		System.out.println("Qtd de palavras = " + qtd);
		for (int j = 0; j < qtd; j++) {
			buffer.append(gerarPalavra(100 / qtd));
			if (j < qtd - 1) buffer.append(" ");
			else buffer.append(".");
		}
		return buffer.toString();
	}

	private String gerarPalavra(int limite) {
		StringBuffer buffer = new StringBuffer();
		int num, tam = 1 + aleatorio.nextInt(limite);
		System.out.println("Tamanho da palavra = " + tam);
		for (int i = 0; i < tam; i++) {
			num = aleatorio.nextInt(10);
			if (num < 6) buffer.append(gerarCaracter(letras));
			else if (num < 9) buffer.append(gerarCaracter(numeros));
			else buffer.append(gerarCaracter(letrasEspeciais));
		}
		return buffer.toString();
	}

	private char gerarCaracter(char[] caracteres) {
		return caracteres[aleatorio.nextInt(caracteres.length)];
	}

	public static void main(String[] args) {
		new GeradorDeTxt();
	}
}
