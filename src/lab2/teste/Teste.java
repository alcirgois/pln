package lab2.teste;

import lab2.base.Corpus;

/**
 * @author Emanuel
 *
 */
public class Teste {
	public static void main(String[] args) {
		Corpus corpus = new Corpus("res/lab2/teste.txt");
		for (String linha : corpus.getConjDeTeste()) {
			System.out.println(linha);
		}
	}
}
