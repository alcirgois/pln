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
		double[] spanProbs = tokenizador.getProbs(sentenca);
		String[] tokens = tokenizador.getTokens(sentenca);
		for (int i = 0; i < nameSpans.length; i++) {
			System.out.print(tokens[nameSpans[i].getStart()] + "\t|\t");
			System.out.print(nameSpans[i].toString() + "\t|\t");
			System.out.println(spanProbs[i]);
		}
	}

	public static void main(String[] args) {
		Teste teste = new Teste();
		String sentencasEN[] = { 
				"He was the last person to see Fred.", 
				"Joe was the last person to see Fred.",
				"Joe was the last person to see Sally with Fred." 
		};
//		String sentencasPT[] = { 
//				"Ele foi a última pessoa a ver Frederico.", 
//				"Joao foi a última pessoa a ver Frederico.",
//				"Joao was the last person to see Sara with Frederico." 
//		};
//		System.out.println("Entidade 	| 	Rotulos 	| 	Probabilidade");
		System.out.println("Entity 	| 	Span 		| 	Probability");
		for (String sentenca : sentencasEN) {
			teste.imprimir(sentenca);
		}
		System.out.println();
	}
}
