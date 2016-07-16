package lab2.base.ngram;

/**
 * @author Emanuel
 *
 */
public class Bigram {

	private String palavra, proxPalavra;
	private long qtd;
	private float p;

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

	public void setP(long qtdDaPalavra) {
		this.p = this.qtd / qtdDaPalavra;
	}

	public boolean equals(Object obj, Object obj2) {
		if (!super.equals(obj)) return false;
		if (proxPalavra.equals(obj2)) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "("+proxPalavra+" | "+palavra+") : [qtd = " + qtd + ", p = " + p + "]";
	}
}
