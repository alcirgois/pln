package lab2.teste;

import java.util.Collections;
import java.util.List;

import lab2.GestorDeNgrams;
import lab2.base.Corpus;
import lab2.base.avalicao.Perplexidade;
import lab2.base.ngram.Bigram;
import lab2.base.ngram.Unigram;

/**
 * @author Emanuel
 *
 */
public class Teste {

	private Corpus corpus;
	private GestorDeNgrams gestorDeNgrams;
	private List<Bigram> modelo;

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
		modelo = gestorDeNgrams.getBigrams();
		Collections.sort(modelo);
		for (Bigram bigram : gestorDeNgrams.getBigrams()) {
			System.out.println(bigram);
		}
	}
	
	public void imprimirAvaliacao() {
		System.out.println("\n====================Bigrams====================");
		Perplexidade avaliador = new Perplexidade(corpus.getConjDeTeste(), modelo);
		System.out.println("Resultado de Avaliação: " + avaliador.calcular()
				+ " de Perplexidade");
	}

	public static void main(String[] args) {
		 Teste teste = new Teste("res/lab2/Lorem ipsum.txt");
		 teste.imprimirUnigrams();
		 teste.imprimirBigrams();
		 teste.imprimirAvaliacao();
	}
}
