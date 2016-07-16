package lab2.base;

import lab2.util.tokens.Token;

/**
 * @author Emanuel
 *
 */
public class Bigram extends Unigram {

	private String proxPalavra;

	public Bigram(Token token, String proxPalavra) {
		super(token);
		this.proxPalavra = proxPalavra;
	}

	public String getProxPalavra() {
		return proxPalavra;
	}

	public void addQtd() {
		token.setCounter(token.getCounter() + 1);
	}

	/*
	 * palavraSeguinte = palavra[i] 
	 * P(palavra[i]|palavra[i-1]) = qtd(palavra[i-1],palavra[i])/qtd(palavra[i-1]) 
	 * Bigram.getQtd() == qtd(palavra[i-1],palavra[i]) 
	 * Unigram.getQtd() == qtd(palavra[i-1])
	 */
	public void setP(long qtd) {
		this.p = token.getCounter() / qtd;
	}

	public boolean equals(Object obj, Object obj2) {
		if (!super.equals(obj)) return false;
		if (proxPalavra.equals(obj2)) return true;
		return false;
	}
}
