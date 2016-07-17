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
	
	public List<Bigram> suavizar() {
		// Há o problema (ou não) de "<ini>" não ser um unigram
		// bigram("<ini>", palavra) não serão suavizados
		List<Bigram> bigramsSmoothing = new ArrayList<Bigram>();
		for (Unigram unigram1: unigrams) {
			for (Unigram unigram2: unigrams) {
				Bigram bigramSmooth = new Bigram(unigram1.getPalavra(), unigram2.getPalavra());
				bigramsSmoothing.add(bigramSmooth);
				if (bigrams.contains(bigramSmooth)) {
					bigramSmooth.setQtd(bigrams.get(bigrams.indexOf(bigramSmooth)).getQtd() + 1);
				} // o atributo qtd do bigram inicia com valor 1
				
				bigramSmooth.setP(unigram1.getQtd() + unigrams.size());
			}
		}
		return bigramsSmoothing;
	}
}
