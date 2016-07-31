package lab4.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lab4.engine.classification.MaxentTrainer;
import lab4.engine.classification.TermDocumentHelper;
import lab4.model.Document;
import lab4.util.FileManager;
import opennlp.model.AbstractModel;
import opennlp.model.DataIndexer;
import opennlp.model.FileEventStream;
import opennlp.model.MaxentModel;
import opennlp.model.OnePassDataIndexer;

/**
 * @author Emanuel
 *
 */
public class Test {

	private List<Document> documents;

	public Test() {
		documents = new ArrayList<Document>();
	}

	public void readReviews() {
		System.out.println("******Lendo reviews******");
		FileManager fileManager = new FileManager();
		try {
			documents.add(new Document("positivas",
					fileManager.readFileToString("res/lab4/corpus_positives_reviews.txt"), "positive"));
			documents.add(new Document("negativas",
					fileManager.readFileToString("res/lab4/corpus_negatives_reviews.txt"), "negative"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] generateIDF() {
		System.out.println("******Preparando Matriz de Importancia TermoXDocumento******");
		TermDocumentHelper termDocumentHelper = new TermDocumentHelper(documents);
		termDocumentHelper.init();
		return termDocumentHelper.getTermsByIDF();
	}

	public void trainMaxentModel() {
		System.out.println("******Treinando o Modelo Maxent******");
		try {
			DataIndexer dataIndexer = new OnePassDataIndexer(
					new FileEventStream("res/lab4/corpus_positives_reviews.txt"));
			MaxentModel maxentModel = MaxentTrainer.trainModel(dataIndexer, 10000);
			MaxentTrainer.saveModel("test", (AbstractModel) maxentModel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPrediction(boolean onlyBest) {
		System.out.println("******Prevendo a classe******");
		MaxentModel maxentModel = MaxentTrainer.loadModel("test");
		String context[] = { "muito" };
		double outcomeProbs[] = maxentModel.eval(context);
		if (onlyBest) return maxentModel.getBestOutcome(outcomeProbs);
		else return maxentModel.getAllOutcomes(outcomeProbs);
	}

	public static void main(String[] args) {
		Test test = new Test();
		// test.trainMaxentModel();
		System.out.println(test.getPrediction(false));
	}
}
