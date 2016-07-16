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
		for (String palavra : linha.replaceAll("[,.:;!?(){}\\[\\]<>/\\\\]", "").split("\\s+")) {
			if (tokens.isEmpty()) tokens.add(new Token(palavra));
			else for (Token token : tokens) {
				if (palavra.equals(token.getPalvra())) {
					token.incQtd();
				} else tokens.add(new Token(palavra));
			}
		}
		return tokens;
	}
}
