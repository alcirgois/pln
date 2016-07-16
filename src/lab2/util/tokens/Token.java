package lab2.util.tokens;

/**
 * Baseado no Token de ericm
 * 
 * @author Emanuel
 *
 */
public class Token {
	private String palavra;
	private long qtd;

	public Token(String palavra) {
		this.palavra = palavra;
		qtd = 1;
	}
	
	public long getQtd() {
        return qtd;
    }

    public void incQtd() {
        qtd++;
    }

    public String getPalvra() {
        return palavra;
    }
}
