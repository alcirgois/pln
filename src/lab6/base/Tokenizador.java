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
 * 
 * @author Emanuel
 *
 */
public class Tokenizador {
	private Tokenizer tokenizador;
	private NameFinderME buscadorDeNomes;

	public Tokenizador() {
		TokenizerModel modeloTokenizador;
		TokenNameFinderModel modeloEntidade;
		try {
			modeloTokenizador = new TokenizerModel(
				GestorDeArq.lerArquivo(GestorDeArq.DIR_DOS_MODELOS_EN + "en-token.bin")
			);
			tokenizador = new TokenizerME(modeloTokenizador);

			modeloEntidade = new TokenNameFinderModel(
				GestorDeArq.lerArquivo(GestorDeArq.DIR_DOS_MODELOS_EN + "en-ner-person.bin")
			);
			buscadorDeNomes = new NameFinderME(modeloEntidade);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] getTokens(String sentenca) {
		return tokenizador.tokenize(sentenca);
	}

	public Span[] getNameSpans(String sentenca) {
		return buscadorDeNomes.find(getTokens(sentenca));
	}

	public double[] getProbs(String sentenca) {
		return buscadorDeNomes.probs(getNameSpans(sentenca));
	}
}
