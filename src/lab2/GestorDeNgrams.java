package lab2;

import java.util.ArrayList;
import java.util.List;

import lab2.base.ngram.Bigram;
import lab2.base.ngram.Unigram;
import lab2.util.tokens.Token;
import lab2.util.tokens.Tokenizador;

/**
 * @author Emanuel
 *
 */
public class GestorDeNgrams {
	private List<String> texto;
	private List<Unigram> unigrams;
	private List<Bigram> bigrams;
	private int qtdLinhas;

	public GestorDeNgrams(List<String> texto) {
		this.texto = texto;
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

	public int getQtdLinhas() {
		return qtdLinhas;
	}

	public void gerarNgrams() {
		List<Token> tokens = new ArrayList<Token>();
		Tokenizador tokenizador = new Tokenizador();
		int posAtual = 0;
		long qtdTotal = 0;
		qtdLinhas = texto.size();
		for (String linha : texto) {
			tokens = tokenizador.gerarTokens(linha);
			int tam;
			boolean achou;
			for (Token token : tokens) {
				qtdTotal += token.getQtd();
				posAtual = tokens.indexOf(token);
				if (posAtual == 0) gerarBigrams("<ini>", token.getPalvra());
				if (!unigrams.isEmpty()) {
					tam = unigrams.size();
					achou = false;
					for (int i = 0; i < tam; i++) {
						if (unigrams.get(i).equals(token.getPalvra())) {
							unigrams.get(i).addQtd(token.getQtd());
							achou = true;
						}
					}
					if (!achou) unigrams.add(new Unigram(token));
				} else unigrams.add(new Unigram(token));
				if (posAtual < tokens.size() - 1) gerarBigrams(token.getPalvra(), tokens.get(posAtual + 1).getPalvra());
				else gerarBigrams(token.getPalvra(), "<fim>");
			}
		}
		calcularP(qtdTotal);
	}

	private void gerarBigrams(String palavra, String proxPalavra) {
		int tam;
		boolean achou;
		if (!bigrams.isEmpty()) {
			tam = bigrams.size();
			achou = false;
			for (int i = 0; i < tam; i++) {
				if (bigrams.get(i).equals(new Bigram(palavra, proxPalavra))) {
					bigrams.get(i).incQtd();
					achou = true;
				}
			}
			if (!achou) bigrams.add(new Bigram(palavra, proxPalavra));
		} else bigrams.add(new Bigram(palavra, proxPalavra));
	}

	public void calcularP(long qtdTotal) {
		for (Unigram unigram : unigrams) {
			unigram.setP(qtdTotal);
		}
		for (Bigram bigram : bigrams) {
			if (bigram.getPalavra().equals("<ini>")) bigram.setP(qtdLinhas);
			else for (Unigram unigram : unigrams) {
				if (bigram.getPalavra().equals(unigram.getPalavra())) {
					bigram.setP(unigram.getQtd());
					break;
				}
			}
		}
	}
}
