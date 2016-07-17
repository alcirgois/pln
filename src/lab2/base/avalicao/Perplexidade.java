package lab2.base.avalicao;

import java.util.List;

import lab2.base.ngram.Bigram;
import lab2.base.ngram.BigramMimico;

public class Perplexidade {
	
	private List<String> conjDeTeste;
	private List<Bigram> modelo;
	
	public Perplexidade(List<String> conjDeTeste, List<Bigram> bigrams) {
		this.conjDeTeste = conjDeTeste;
		this.modelo = bigrams;
	}
	
	public double calcular() {
		double log = 0.0;
		double prob_ = 1.0;
		
		int n = 0;

		for (String linha: conjDeTeste) {
			String palavraAnterior = "<ini>";
			String[] palavras = linha.toLowerCase().split("[ ,.:;!?(){}\\[\\]<>/\\\\]+");
			n += palavras.length;
			for (String palavra: palavras) {
				BigramMimico mimico = new BigramMimico(palavraAnterior, palavra);
				if (modelo.contains(mimico)) {
					double p = modelo.get(modelo.indexOf(mimico)).getP();
					log += Math.log(p);
					prob_ *= p;
				} //else
				// ??? ignorar é o mesmo que dizer log += 0 -> p = 1, o que não é verdade
				palavraAnterior = palavra;
			}
			BigramMimico mimico = new BigramMimico(palavraAnterior, "<fim>");
			if (modelo.contains(mimico)) {
				double p = modelo.get(modelo.indexOf(mimico)).getP();
				log += Math.log(p);
				prob_ *= p;
			} //else
			// ??? ignorar é o mesmo que dizer prob *= 1, o que não é verdade
		}
		
		double prob = Math.exp(log);
		//******REMOVER*****************************
		System.out.println(prob + " = " + prob_ + "?");
		//******REMOVER*****************************
		return Math.pow((1.0 / prob), (1.0 / n));
	}
}
