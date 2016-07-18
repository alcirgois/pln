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
	private char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase().toCharArray();
	private char[] letrasEspeciais = "¡…Õ”⁄√’¬ Œ‘€¿»Ã“Ÿ«".toLowerCase().toCharArray();
	private char[] numeros = "0123456789".toCharArray();
	private final String ARQUIVO = "res/lab2/teste.txt";
	private FileManager gestorArq;
	private Random aleatorio;
	private List<String> palavras;
	private int qtdTotal;

	/**
	 * Gera o texto com qtd aleatoria de linhas
	 */
	public GeradorDeTxt() {
		this(0);
	}

	/**
	 * Gera o texto com a qtd do numLinhas
	 * 
	 * @param numLinhas
	 */
	public GeradorDeTxt(int numLinhas) {
		palavras = new ArrayList<String>();
		gestorArq = new FileManager();
		aleatorio = new Random();
		qtdTotal = 0;
		gerarTexto(numLinhas);
	}

	private void gerarTexto(int numLinhas) {
		List<String> texto = new ArrayList<String>();
		if (numLinhas == 0) numLinhas = 10 + aleatorio.nextInt(91);
		int limite = numLinhas * 2;
		for (int i = 0; i < limite; i++) {
			palavras.add(gerarPalavra());
		}
		for (int i = 0; i < numLinhas; i++) {
			texto.add(gerarLinha());
		}
		try {
			gestorArq.writeToFile(ARQUIVO, texto);
			System.out
					.println("Arquivo " + ARQUIVO + " gerado com " + numLinhas + " linhas e " + qtdTotal + " palavras");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String gerarPalavra() {
		StringBuffer buffer = new StringBuffer();
		int tam, num;
		tam = 1 + aleatorio.nextInt(10);
		for (int j = 0; j < tam; j++) {
			num = aleatorio.nextInt(100);
			if (num < 90) buffer.append(gerarCaracter(letras));
			else if (num < 95) buffer.append(gerarCaracter(numeros));
			else buffer.append(gerarCaracter(letrasEspeciais));
		}
		return buffer.toString();
	}

	private String gerarLinha() {
		StringBuffer buffer = new StringBuffer();
		int qtd = 5 + aleatorio.nextInt(6);
		qtdTotal += qtd;
		for (int j = 0; j < qtd; j++) {
			buffer.append(getPalavra());
			if (j < qtd - 1) buffer.append(" ");
			else buffer.append(".");
		}
		return buffer.toString();
	}

	private String getPalavra() {
		return palavras.get(aleatorio.nextInt(palavras.size()));
	}

	private char gerarCaracter(char[] caracteres) {
		return caracteres[aleatorio.nextInt(caracteres.length)];
	}

	public static void main(String[] args) {
		new GeradorDeTxt();
	}
}
