package lab4.test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import lab4.engine.classification.MaxentTrainer;
import opennlp.maxent.BasicEventStream;
import opennlp.maxent.DataStream;
import opennlp.maxent.PlainTextByLineDataStream;
import opennlp.model.EventStream;
import opennlp.model.MaxentModel;

/**
 * @author Emanuel
 *
 */
public class Test {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		DataStream dataStream;
		try {
			// Gera um eventStream da data
			dataStream = new PlainTextByLineDataStream(new FileReader(""));
			EventStream eventStream = new BasicEventStream(dataStream);
			MaxentModel maxentModel = new MaxentTrainer().trainModel(eventStream, 100);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
