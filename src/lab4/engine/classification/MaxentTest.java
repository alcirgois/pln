package lab4.engine.classification;

import opennlp.model.DataIndexer;
import opennlp.model.FileEventStream;
import opennlp.model.MaxentModel;
import opennlp.model.OnePassDataIndexer;

public class MaxentTest {
	
	public void classify(String testFileName, MaxentModel model, String[] features) {
		try {
			int correct = 0, wrong = 0;
			DataIndexer index = new OnePassDataIndexer(new FileEventStream(testFileName));
			int[][] fContextos = index.getContexts();
			String[] classesEsperadas = index.getOutcomeLabels();
			String[][] contextos = prepareContextos(features, fContextos, classesEsperadas.length);
			for (int i = 0; i < classesEsperadas.length; i++) {
				double[] classesProb = model.eval(contextos[i]);
				String classeObtida = model.getBestOutcome(classesProb);
				if (classeObtida.equals(classesEsperadas[i])) {
					correct++;
				} else {
					wrong++;
				}
			}
			System.out.println("Predições corretas " + correct);
			System.out.println("Predições erradas " + wrong);
			System.out.println("Taxa de erro " + wrong / (correct + wrong));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String[][] prepareContextos(String[] features, int[][] fContextos, int numContextos) {
		String[][] saida = new String[numContextos][];
		for (int i = 0; i < numContextos; i++) {
			saida[i] = new String[features.length];
			for (int j = 0; j < features.length; j++) {
				saida[i][j] = features[j] + "=" + fContextos[i][j];
			}
		}
		return saida;
	}
}
