package lab2.base.ngram;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author Emanuel
 *
 */
public class Bigram implements ImplBigram, Comparable<Bigram> {

	private String palavra, proxPalavra;
	private long qtd;
	private BigDecimal p;

	public Bigram(String palavra, String proxPalavra) {
		this.palavra = palavra;
		this.proxPalavra = proxPalavra;
		qtd = 1;
	}

	public String getPalavra() {
		return palavra;
	}

	public String getProxPalavra() {
		return proxPalavra;
	}

	public long getQtd() {
		return qtd;
	}

	public void incQtd() {
		qtd++;
	}

	public BigDecimal getP() {
		return p;
	}

	public void setP(long qtdDaPalavra) {
		BigDecimal bigQtd = new BigDecimal(qtd);
		BigDecimal bigQtdDaPalavra = new BigDecimal(qtdDaPalavra);
		p = bigQtd.divide(bigQtdDaPalavra, MathContext.DECIMAL64);
	}

	public boolean equals(Object obj, Object obj2) {
		if (palavra.equals(obj) && proxPalavra.equals(obj2)) return true;
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (palavra.equals(((ImplBigram) obj).getPalavra())
				&& proxPalavra.equals(((ImplBigram) obj).getProxPalavra())) return true;
		return false;
	}

	@Override
	public String toString() {
		return "(" + proxPalavra + " | " + palavra + ") : [qtd = " + qtd + ", p = " + p + "]";
	}

	@Override
	public int compareTo(Bigram bigram) {
		if (!this.getPalavra().equals(bigram.getPalavra())) return this.getPalavra().compareTo(bigram.getPalavra());
		else return this.getProxPalavra().compareTo(bigram.getProxPalavra());
	}

	public void setQtd(long qtd) {
		this.qtd = qtd;
	}
}
