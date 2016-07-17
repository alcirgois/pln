package lab2.teste;

import java.util.Collections;
import java.util.List;

import lab2.GestorDeNgrams;
import lab2.base.Corpus;
import lab2.base.avalicao.Perplexidade;
import lab2.base.ngram.Bigram;
import lab2.base.ngram.Unigram;
import lab2.base.suavizacao.LaplaceAddOne;

/**
 * @author Emanuel
 *
 */
public class Teste {

	private Corpus corpus;
	private GestorDeNgrams gestorDeNgrams;
	private List<Unigram> unigrams;
	private List<Bigram> bigrams;
	private List<Bigram> modelo;

	public Teste(String arquivo) {
		corpus = new Corpus(arquivo);
		gestorDeNgrams = new GestorDeNgrams(corpus);
	}

	public void imprimirUnigrams() {
		System.out.println("====================Unigrams====================");
		unigrams = gestorDeNgrams.getUnigrams();
		Collections.sort(unigrams);
		for (Unigram unigram : unigrams) {
			System.out.println(unigram);
		}
	}

	public void imprimirBigrams() {
		System.out.println("\n====================Bigrams====================");
		bigrams = gestorDeNgrams.getBigrams();
		Collections.sort(bigrams);
		for (Bigram bigram : bigrams) {
			System.out.println(bigram);
		}
	}
	
	public void imprimirAvaliacao() {
		System.out.println("\n==================Perplexidade=================");
		if (modelo == null) modelo = bigrams;
		Perplexidade avaliador = new Perplexidade(corpus.getConjDeTeste(), modelo);
		System.out.println("Resultado de Avaliação: " + avaliador.calcular()
				+ " de Perplexidade");
	}
	
	public void imprimirSuavizacaoLaplaceAddOne() {
		System.out.println("\n============Laplace Smoothing Add-1============");
		LaplaceAddOne lapAddOne = new LaplaceAddOne(unigrams, bigrams);
		modelo = lapAddOne.suavizar();
		//Collections.sort(modelo);
		for (Bigram bigram : modelo) {
			System.out.println(bigram);
		}
	}

	public static void main(String[] args) {
		 Teste teste = new Teste("res/lab2/teste.txt");
		 teste.imprimirUnigrams();
		 teste.imprimirBigrams();
		 teste.imprimirSuavizacaoLaplaceAddOne();
		 teste.imprimirAvaliacao();
	}
}
