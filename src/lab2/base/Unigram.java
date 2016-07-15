package lab2.base;

import lab2.util.tokens.Token;

/**
 * @author Emanuel
 *
 */
public class Unigram {
	private Token token;
	private float p;

	public Unigram(String palavra) {
		this.token = new Token(palavra);
	}

	public Unigram(String palavra, long qtd) {
		this.token = new Token(palavra, qtd);
	}

	public String getPalavra() {
		return token.getText();
	}

	public void addQtd(long qtd) {
		token.setCounter(token.getCounter() + qtd);
	}

	public float getP() {
		return p;
	}

	public void setP(long qtdTotal) {
		this.p = token.getCounter() / qtdTotal;
	}

}
