package lab2.base.ngram;

import lab2.util.tokens.Token;

/**
 * @author Emanuel
 *
 */
public class Unigram {
	private Token token;
	private float p;

	public Unigram(Token token) {
		this.token = token;
	}

	public String getPalavra() {
		return token.getText();
	}

	public long getQtd() {
		return token.getCounter();
	}

	public void addQtd(long qtd) {
		token.setCounter(token.getCounter() + qtd);
	}

	public float getP() {
		return p;
	}

	// P(palavra[i]) = qtd(palavra[i]) / qtd(palavras)
	public void setP(long qtdTotal) {
		this.p = token.getCounter() / qtdTotal;
	}

	@Override
	public boolean equals(Object obj) {
		if (getPalavra().equals(obj)) return true;
		else return false;
	}

	@Override
	public String toString() {
		return token.getText() + " : [qtd = " + token.getCounter() + ", p = " + p + "]";
	}
}
