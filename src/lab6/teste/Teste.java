package lab6.teste;

import lab6.base.Tokenizador;
import opennlp.tools.util.Span;

/**
 * @author Emanuel
 *
 */
public class Teste {
	private Tokenizador tokenizador;

	public Teste() {
		tokenizador = new Tokenizador();
	}

	public void imprimir(String sentenca) {
		Span[] nameSpans = tokenizador.getNameSpans(sentenca);
		String[] tokens = tokenizador.getTokens();
		for (int i = 0; i < nameSpans.length; i++) {
			System.out.print(nameSpans[i].toString() + " | ");
			System.out.println(tokens[nameSpans[i].getStart()]);
		}
	}

	public static void main(String[] args) {
		Teste teste = new Teste();
		String sentencas[] = { "Ele era a ultima pessoa a ver Frederico.", "João foi a ultima pessoa a ver Frederico.",
				"João foi a ultima pessoa a ver Sara com Frederico." };
		System.out.println("Span | Entidade");
		for (String sentenca : sentencas) {
			teste.imprimir(sentenca);
		}
		System.out.println();
	}
}
