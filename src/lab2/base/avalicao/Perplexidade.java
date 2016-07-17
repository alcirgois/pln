package lab2.base.avalicao;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import lab2.base.ngram.Bigram;
import lab2.base.ngram.BigramMimico;
import lab2.util.BigDecimalMath;

public class Perplexidade {
	
	private List<String> conjDeTeste;
	private List<Bigram> modelo;
	
	public Perplexidade(List<String> conjDeTeste, List<Bigram> bigrams) {
		this.conjDeTeste = conjDeTeste;
		this.modelo = bigrams;
	}
	
	public BigDecimal calcular() {
		BigDecimal prob = new BigDecimal("1.0");
		
		int n = 0;

		for (String linha: conjDeTeste) {
			String palavraAnterior = "<ini>";
			String[] palavras = linha.toLowerCase().split("[ ,.:;!?(){}\\[\\]<>/\\\\]+");
			n += palavras.length;
			for (String palavra: palavras) {
				BigramMimico mimico = new BigramMimico(palavraAnterior, palavra);
				if (modelo.contains(mimico)) {
					BigDecimal p = modelo.get(modelo.indexOf(mimico)).getP();
					prob = prob.multiply(p, MathContext.DECIMAL64);
				} else {
					prob = prob.multiply(new BigDecimal("0.00001"), MathContext.DECIMAL128);
				}
				palavraAnterior = palavra;
			}
			BigramMimico mimico = new BigramMimico(palavraAnterior, "<fim>");
			if (modelo.contains(mimico)) {
				BigDecimal p = modelo.get(modelo.indexOf(mimico)).getP();
				prob = prob.multiply(p, MathContext.DECIMAL64);
			} else {
				prob = prob.multiply(new BigDecimal("0.00001"), MathContext.DECIMAL128);
			}
		}
		
		//******REMOVER*****************************
		System.out.println(prob);
		//******REMOVER*****************************
		BigDecimal termo1 = (new BigDecimal("1")).divide(prob, MathContext.DECIMAL128);
		BigDecimal termo2 = (new BigDecimal("1")).divide(new BigDecimal(n), MathContext.DECIMAL128);
		
		return BigDecimalMath.pow(termo1, termo2);
	}
}
