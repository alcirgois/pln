package lab2.base.ngram;

import java.math.BigDecimal;
import java.math.MathContext;

import lab2.util.tokens.Token;

/**
 * @author Emanuel
 *
 */
public class Unigram implements Comparable<Unigram> {
	private String palavra;
	private long qtd;
	private BigDecimal p;

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

	public BigDecimal getP() {
		return p;
	}

	// P(palavra[i]) = qtd(palavra[i]) / qtd(palavras)
	public void setP(long qtdTotal) {
		BigDecimal bigQtd = new BigDecimal(qtd);
		BigDecimal bigQtdTotal = new BigDecimal(qtdTotal);
		p = bigQtd.divide(bigQtdTotal, MathContext.DECIMAL64);
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

	@Override
	public int compareTo(Unigram unigram) {
		return this.getP().compareTo(unigram.getP());
	}
}
