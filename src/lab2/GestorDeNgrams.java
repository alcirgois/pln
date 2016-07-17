package lab2;

import java.util.ArrayList;
import java.util.List;

import lab2.base.Corpus;
import lab2.base.ngram.Bigram;
import lab2.base.ngram.Unigram;
import lab2.util.tokens.Token;
import lab2.util.tokens.Tokenizador;

/**
 * @author Emanuel
 *
 */
public class GestorDeNgrams {
	private Corpus corpus;
	private List<Unigram> unigrams;
	private List<Bigram> bigrams;

	public GestorDeNgrams(Corpus corpus) {
		this.corpus = corpus;
		unigrams = new ArrayList<Unigram>();
		bigrams = new ArrayList<Bigram>();
		gerarNgrams();
	}

	public List<Unigram> getUnigrams() {
		return unigrams;
	}

	public List<Bigram> getBigrams() {
		return bigrams;
	}

	public void gerarNgrams() {
		List<Token> tokens = new ArrayList<Token>();
		Tokenizador tokenizador = new Tokenizador();
		int posAtual = 0;
		long qtdTotal = 0;
		for (String linha : corpus.getConjDeTreinamento()) {
			tokens = tokenizador.gerarTokens(linha);
			int tam;
			for (Token token : tokens) {
				qtdTotal += token.getQtd();
				posAtual = tokens.indexOf(token);
				if (posAtual == 0) gerarBigrams("<ini>", token.getPalvra());
				if (!unigrams.isEmpty()) {
					tam = unigrams.size();
					for (int i = 0; i < tam; i++) {
						if (unigrams.get(i).equals(token.getPalvra())) unigrams.get(i).addQtd(token.getQtd());
					}
					unigrams.add(new Unigram(token));
				} else unigrams.add(new Unigram(token));
				if (posAtual < tokens.size() - 1) gerarBigrams(token.getPalvra(), tokens.get(posAtual + 1).getPalvra());
				else gerarBigrams(token.getPalvra(), "<fim>");
			}
		}
		calcularP(qtdTotal);
	}

	private void gerarBigrams(String palavra, String proxPalavra) {
		int tam;
		if (!bigrams.isEmpty()) {
			tam = bigrams.size();
			for (int i = 0; i < tam; i++) {
				if (bigrams.get(i).equals(palavra, proxPalavra)) bigrams.get(i).incQtd();
			}
			bigrams.add(new Bigram(palavra, proxPalavra));
		} else bigrams.add(new Bigram(palavra, proxPalavra));
	}

	public void calcularP(long qtdTotal) {
		for (Unigram unigram : unigrams) {
			unigram.setP(qtdTotal);
		}
		for (Bigram bigram : bigrams) {
			if (bigram.getPalavra().equals("<ini>")) bigram.setP(corpus.getConjDeTreinamento().size());
			else for (Unigram unigram : unigrams) {
				if (bigram.getPalavra().equals(unigram.getPalavra())) {
					bigram.setP(unigram.getQtd());
					break;
				}
			}
		}
	}
}
