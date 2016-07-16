package lab2.teste;

import java.util.ArrayList;
import java.util.List;

import lab2.base.*;
import lab2.util.tokens.*;

/**
 * @author Emanuel
 *
 */
public class Teste {

	private Corpus corpus;
	private List<Unigram> unigrams;
	private List<Bigram> bigrams;
	private long qtdTotal = 0;

	public Teste() {
		corpus = new Corpus("res/lab2/teste.txt");
		unigrams = new ArrayList<Unigram>();
		bigrams = new ArrayList<Bigram>();
		setQtdTotal();
	}

	private void setQtdTotal() {
		for (String linha : corpus.getConjDeTreinamento()) {
			linha = linha.replace("(", "").replace(")", "").replace("!", "").replace(".", "").replace(",", "")
					.replace("?", "").replace(":", "").replace(";", "").replace("\"", "");
			qtdTotal += linha.split("\\s+").length;
		}
	}

	public void gerarNgrams() {
		List<Token> tokens = new ArrayList<Token>();
		Tokenizer tokenizador = new Tokenizer();
		int posAtual = 0;
		for (String linha : corpus.getConjDeTreinamento()) {
			tokens = tokenizador.textToTokens(linha);
			for (Token token : tokens) {
				posAtual = tokens.indexOf(token);
				if (posAtual == 0) gerarBigrams("<ini>", token.getText());
				for (Unigram unigram : unigrams) {
					if (unigram.equals(token.getText())) unigram.addQtd(token.getCounter());
					else unigrams.add(new Unigram(token));
				}
				if (posAtual < tokens.size()) gerarBigrams(token.getText(), tokens.get(posAtual + 1).getText());
				else gerarBigrams(token.getText(), "<fim>");
			}
		}
	}

	private void gerarBigrams(String palavra, String palavraSeguinte) {
		for (Bigram bigram : bigrams) {
			if (bigram.equals(palavra, palavraSeguinte)) bigram.addQtd(1);
			else bigrams.add(new Bigram(new Token(palavra, 1), palavraSeguinte));
		}
	}

	public void calcularP() {
		for (Unigram unigram : unigrams) {
			unigram.setP(qtdTotal);
		}
		for (Bigram bigram : bigrams) {
			if (bigram.getPalavra().equals("<ini>")) bigram.setP(corpus.getConjDeTreinamento().size());
			else for (Unigram unigram : unigrams) {
				if (bigram.getPalavra().equals(unigram.getPalavra())) {
					bigram.setP(unigram.getQtd());
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		Teste teste = new Teste();
		teste.gerarNgrams();
		teste.calcularP();
	}
}
