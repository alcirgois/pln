package lab2.teste;

import java.util.Collections;

import lab2.GestorDeNgrams;
import lab2.base.Corpus;
import lab2.base.ngram.Bigram;
import lab2.base.ngram.Unigram;

/**
 * @author Emanuel
 *
 */
public class Teste {

	private Corpus corpus;
	private GestorDeNgrams gestorDeNgrams;

	public Teste(String arquivo) {
		corpus = new Corpus(arquivo);
		gestorDeNgrams = new GestorDeNgrams(corpus);
	}

	public void imprimirUnigrams() {
		System.out.println("====================Unigrams====================");
		Collections.sort(gestorDeNgrams.getUnigrams());
		for (Unigram unigram : gestorDeNgrams.getUnigrams()) {
			System.out.println(unigram);
		}
	}

	public void imprimirBigrams() {
		System.out.println("\n====================Bigrams====================");
		Collections.sort(gestorDeNgrams.getBigrams());
		for (Bigram bigram : gestorDeNgrams.getBigrams()) {
			System.out.println(bigram);
		}
	}

	public static void main(String[] args) {
		 Teste teste = new Teste("res/lab2/Lorem ipsum.txt");
		 teste.imprimirUnigrams();
		 teste.imprimirBigrams();
	}
}
