package lab2.base.ngram;

public class BigramMimico implements ImplBigram {
	
	private String palavra, proxPalavra;
	
	public BigramMimico(String palavra, String proxPalavra) {
		this.palavra = palavra;
		this.proxPalavra = proxPalavra;
	}
	
	public String getPalavra() {
		return palavra;
	}
	
	public String getProxPalavra() {
		return proxPalavra;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (palavra.equals(((ImplBigram) obj).getPalavra())
				&& proxPalavra.equals(((ImplBigram) obj).getProxPalavra())) return true;
		return false;
	}

}
