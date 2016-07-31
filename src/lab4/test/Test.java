package lab4.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lab4.engine.classification.MachineLearningHandler;
import lab4.engine.classification.MaxentTrainer;
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

	private MachineLearningHandler machineLearningHandler;
	private List<Document> documents;

	public Test() throws IOException {
		documents = new ArrayList<Document>();
		readReviews();
		gerarTraningSet();
	}

	private void readReviews() throws IOException {
		System.out.println("******Lendo reviews******");
		FileManager fileManager = new FileManager();
		documents.add(new Document("positivas", fileManager.readFileToString("res/lab4/corpus_positives_reviews.txt"),
				"positive"));
		documents.add(new Document("negativas", fileManager.readFileToString("res/lab4/corpus_negatives_reviews.txt"),
				"negative"));

	}

	private void gerarTraningSet() throws IOException {
		System.out.println("******Gerando conjunto de treinamento******");
		machineLearningHandler = new MachineLearningHandler(documents);
		machineLearningHandler.init();
		machineLearningHandler.generateTrainingModel("res/lab4/test/conjTreinamento.txt");
	}

	public void trainMaxentModel() throws IOException {
		System.out.println("******Treinando o Modelo Maxent******");
		DataIndexer dataIndexer = new OnePassDataIndexer(new FileEventStream("res/lab4/test/conjTreinamento.txt"), 2);
		MaxentModel maxentModel = MaxentTrainer.trainModel(dataIndexer, 10000);
		MaxentTrainer.saveModel("res/lab4/test/test", (AbstractModel) maxentModel);
	}

	public String getPrediction(boolean onlyBest) {
		System.out.println("******Prevendo a classe******");
		MaxentModel maxentModel = MaxentTrainer.loadModel("res/lab4/test/test");
		String context[] = { "generateIDF();" };
		double outcomeProbs[] = maxentModel.eval(context);
		if (onlyBest) return maxentModel.getBestOutcome(outcomeProbs);
		else return maxentModel.getAllOutcomes(outcomeProbs);
	}

	public static void main(String[] args) {
		System.out.println("------Tarefa 01------");
		Test test;
		try {
			test = new Test();
			test.trainMaxentModel();
			System.out.println(test.getPrediction(false));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
