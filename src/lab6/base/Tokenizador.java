package lab6.base;

import java.io.IOException;

import lab6.util.GestorDeArq;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

/**
 * Usando OpenNLP para NER (NLP with Java, p. 107)
 * @author Emanuel
 *
 */
public class Tokenizador {
	private Tokenizer tokenizador;
	private NameFinderME buscadorDeNomes;
	private String[] tokens;
	
	public Tokenizador() {
		TokenizerModel modeloTokenizador;
		TokenNameFinderModel modeloEntidade;
		try {
			modeloTokenizador = new TokenizerModel(GestorDeArq.lerArquivo("modelo_de_tokens.txt"));
			tokenizador = new TokenizerME(modeloTokenizador);
			modeloEntidade = new TokenNameFinderModel(GestorDeArq.lerArquivo("modelo_de_entidades.txt"));
			buscadorDeNomes = new NameFinderME(modeloEntidade);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String[] getTokens(String sentenca) {
		tokens = tokenizador.tokenize(sentenca);
		return tokens;
	}
		
	public String[] getTokens() {
		return tokens;
	}
	
	public Span[] getNameSpans(String sentenca) {
		tokens = tokenizador.tokenize(sentenca);
		return buscadorDeNomes.find(tokens);
	}
	
	public Span[] getNameSpans() {
		return buscadorDeNomes.find(tokens);
	}
}
