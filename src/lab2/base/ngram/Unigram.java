package lab2.base.ngram;

import lab2.util.tokens.Token;

/**
 * @author Emanuel
 *
 */
public class Unigram {
	private String palavra;
	private long qtd;
	private double p;

	public Unigram(Token token) {
		palavra = token.getPalvra();
		qtd = token.getQtd();
	}

	public String getPalavra() {
		return palavra;
	}

	public long getQtd() {
		return qtd;
	}

	public void addQtd(long qtd) {
		this.qtd += qtd;
	}

	public double getP() {
		return p;
	}

	// P(palavra[i]) = qtd(palavra[i]) / qtd(palavras)
	public void setP(long qtdTotal) {
		p = ((double) qtd) / qtdTotal;
	}

	@Override
	public boolean equals(Object obj) {
		if (palavra.equals(obj)) return true;
		return false;
	}

	@Override
	public String toString() {
		return palavra + " : [qtd = " + qtd + ", p = " + p + "]";
	}
}
