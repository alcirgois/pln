package lab4;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import lab4.engine.SearchAgent;
import opennlp.maxent.io.GISModelReader;
import opennlp.model.DataReader;
import opennlp.model.MaxentModel;
import opennlp.model.PlainTextFileDataReader;

/**
 * Created by Ariel on 29-Jul-16.
 */
public class Main {

	public static void main(String[] args) throws IOException {

		/*
		 * // Read reviews and get 1000 most important by idf FileManager fm =
		 * new FileManager(); List<Document> documents = new ArrayList<>();
		 * System.out.println("******Lendo reviews******"); documents.add(new
		 * Document("positivas",fm.readFileToString(
		 * "corpus/corpus_positives_reviews.txt"),"positive"));
		 * documents.add(new Document("negativas",fm.readFileToString(
		 * "corpus/corpus_negatives_reviews.txt"),"negative"));
		 * 
		 * TermDocumentHelper termDocumentHelper = new
		 * TermDocumentHelper(documents); System.out.println(
		 * "******Preparando Matriz de Importancia TermoXDocumento******");
		 * termDocumentHelper.init(); String[] terms =
		 * termDocumentHelper.getTermsbyIDF();
		 * 
		 * DataIndexer indexer = new OnePassDataIndexer(new
		 * FileEventStream("corpus/corpus_positives_reviews.txt")); MaxentModel
		 * trainedMaxentModel = GIS.trainModel(10000, indexer); // 10.000
		 * iterations
		 * 
		 * // Storing the trained model into a file for later use (gzipped) File
		 * outFile = new File("trained-model.maxent.gz"); AbstractModelWriter
		 * writer = new SuffixSensitiveGISModelWriter((AbstractModel)
		 * trainedMaxentModel, outFile); writer.persist();
		 */

		// Loading the gzipped model from a file
		FileInputStream inputStream = new FileInputStream("trained-model.maxent.gz");
		InputStream decodedInputStream = new GZIPInputStream(inputStream);
		DataReader modelReader = new PlainTextFileDataReader(decodedInputStream);
		MaxentModel loadedMaxentModel = new GISModelReader(modelReader).getModel();

		// Now predicting the outcome using the loaded model
		String[] context = { SearchAgent.getReview("filmow", "") };
		double[] outcomeProbs = loadedMaxentModel.eval(context);
		String outcome = loadedMaxentModel.getBestOutcome(outcomeProbs);
		String outcome2 = loadedMaxentModel.getAllOutcomes(outcomeProbs);
		System.out.println(outcome2);
		System.out.println(outcome);
	}
}
