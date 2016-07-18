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
	private Perplexidade avaliador;
	private LaplaceAddOne lapAddOne;

	public Teste(String arquivo) {
		corpus = new Corpus(arquivo);
		gestorDeNgrams = new GestorDeNgrams(corpus.getConjDeTreinamento());
		unigrams = gestorDeNgrams.getUnigrams();
		bigrams = gestorDeNgrams.getBigrams();
		lapAddOne = new LaplaceAddOne(unigrams, bigrams);
		modelo = lapAddOne.suavizar(gestorDeNgrams.getQtdLinhas());
		if (modelo == null) modelo = bigrams;
		avaliador = new Perplexidade(corpus.getConjDeTeste(), modelo);
	}

	public void imprimirUnigrams() {
		System.out.println("====================Unigrams====================");
		Collections.sort(unigrams);
		for (Unigram unigram : unigrams) {
			System.out.println(unigram);
		}
	}

	public void imprimirBigrams() {
		System.out.println("\n====================Bigrams====================");
		Collections.sort(bigrams);
		for (Bigram bigram : bigrams) {
			System.out.println(bigram);
		}
	}

	public void imprimirSuavizacaoLaplaceAddOne() {
		System.out.println("\n============Laplace Smoothing Add-1============");
		Collections.sort(modelo);
		for (Bigram bigram : modelo) {
			System.out.println(bigram);
		}
	}

	public void imprimirAvaliacao() {
		System.out.println("\n==================Perplexidade=================");
		System.out.println("Resultado de Avaliação: " + avaliador.calcular());
	}

	public static void main(String[] args) {
		Teste teste = new Teste("res/lab2/teste.txt");
		teste.imprimirUnigrams();
		teste.imprimirBigrams();
		teste.imprimirSuavizacaoLaplaceAddOne();
		teste.imprimirAvaliacao();
	}
}
