package lab2.base;

/**
 * @author Emanuel
 *
 */
public class Bigram extends Unigram {

	private String palavraSeguinte;

	public Bigram(String palavra, String palavraSeguinte) {
		super(palavra);
		this.palavraSeguinte = palavraSeguinte;
	}

	public Bigram(String palavra, long qtd, String palavraSeguinte) {
		super(palavra, qtd);
		this.palavraSeguinte = palavraSeguinte;
	}

	public String getPalavraCond() {
		return palavraSeguinte;
	}

	public void setP(long qtdSeq) {
		this.p = qtdSeq / token.getCounter();
	}
}
