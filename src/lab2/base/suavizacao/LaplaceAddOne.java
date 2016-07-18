package lab2.base.suavizacao;

import java.util.ArrayList;
import java.util.List;

import lab2.base.ngram.Bigram;
import lab2.base.ngram.Unigram;

public class LaplaceAddOne {

	private List<Unigram> unigrams;
	private List<Bigram> bigrams;

	public LaplaceAddOne(List<Unigram> unigrams, List<Bigram> bigrams) {
		this.unigrams = unigrams;
		this.bigrams = bigrams;
	}

	public List<Bigram> suavizar(int qtdLinhas) {
		List<Bigram> bigramsSmoothing = new ArrayList<Bigram>();
		Bigram bigramSmooth;
		for (int i = 0; i < qtdLinhas; i++) {
			for (Unigram unigram : unigrams) {
				bigramSmooth = new Bigram("<ini>", unigram.getPalavra());
				bigramsSmoothing.add(bigramSmooth);
				bigramSmooth = suavizarBigram(bigramSmooth, qtdLinhas);
				bigramSmooth = new Bigram(unigram.getPalavra(), "<fim>");
				bigramsSmoothing.add(bigramSmooth);
				bigramSmooth = suavizarBigram(bigramSmooth, unigram.getQtd());
			}
		}
		for (Unigram unigram1 : unigrams) {
			for (Unigram unigram2 : unigrams) {
				bigramSmooth = new Bigram(unigram1.getPalavra(), unigram2.getPalavra());
				bigramsSmoothing.add(bigramSmooth);
				bigramSmooth = suavizarBigram(bigramSmooth, unigram1.getQtd());
			}
		}
		return bigramsSmoothing;
	}

	// Bigram inicia com qtd = 1 e P é calculado com V
	private Bigram suavizarBigram(Bigram bigram, long qtd) {
		if (bigrams.contains(bigram)) {
			bigram.setQtd(bigrams.get(bigrams.indexOf(bigram)).getQtd() + 1);
		}
		bigram.setP(qtd + unigrams.size());
		return bigram;
	}
}
