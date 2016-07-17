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
		boolean achou;
		for (String palavra : linha.toLowerCase().replaceAll("[,.:;!?(){}\\[\\]<>/\\\\]", "").split("\\s+")) {
			if (!tokens.isEmpty()) {
				tam = tokens.size();
				achou = false;
				for (int i = 0; i < tam; i++) {
					if (palavra.equals(tokens.get(i).getPalvra())) {
						tokens.get(i).incQtd();
						achou = true;
					}
				}
				if (!achou) tokens.add(new Token(palavra));
			} else tokens.add(new Token(palavra));
		}
		return tokens;
	}
}
