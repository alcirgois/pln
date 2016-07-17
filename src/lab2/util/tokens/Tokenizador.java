package lab2.util.tokens;

import java.util.ArrayList;
import java.util.List;

/**
 * Baseado no Tokenizer de ericm
 * 
 * @author Emanuel
 * 
 */
public class Tokenizador {
	public List<Token> gerarTokens(String linha) {
		List<Token> tokens = new ArrayList<Token>();
		int tam;
		for (String palavra : linha.replaceAll("[,.:;!?(){}\\[\\]<>/\\\\]", "").split("\\s+")) {
			if (!tokens.isEmpty()) {
				tam = tokens.size();
				for (int i = 0; i < tam; i++) {
					if (palavra.equals(tokens.get(i).getPalvra())) {
						tokens.get(i).incQtd();
					}
				}
				tokens.add(new Token(palavra));
			} else tokens.add(new Token(palavra));
		}
		return tokens;
	}
}
