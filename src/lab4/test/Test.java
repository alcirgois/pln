package lab4.test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import lab4.engine.classification.MaxentTrainer;
import opennlp.maxent.BasicEventStream;
import opennlp.maxent.DataStream;
import opennlp.maxent.PlainTextByLineDataStream;
import opennlp.model.AbstractModel;
import opennlp.model.EventStream;
import opennlp.model.MaxentModel;

/**
 * @author Emanuel
 *
 */
public class Test {
	public static void main(String[] args) {
		DataStream dataStream;
		try {
			// Gera um eventStream da data
			dataStream = new PlainTextByLineDataStream(new FileReader("res/lab4/corpus_filmow_positivas.txt"));
			EventStream eventStream = new BasicEventStream(dataStream);
			MaxentTrainer  maxentTrainer = new MaxentTrainer();
			MaxentModel maxentModel = maxentTrainer.trainModel(eventStream, 100);
			maxentTrainer.saveModel("test", (AbstractModel) maxentModel);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
