package lab4.engine.classification;

import java.io.File;
import java.io.IOException;

import opennlp.maxent.GIS;
import opennlp.maxent.io.GISModelWriter;
import opennlp.maxent.io.SuffixSensitiveGISModelReader;
import opennlp.maxent.io.SuffixSensitiveGISModelWriter;
import opennlp.model.AbstractModel;
import opennlp.model.EventStream;
import opennlp.model.OnePassDataIndexer;

/**
 * @author Emanuel
 *
 */
public class MaxentTrainer {

	public AbstractModel trainModel(EventStream eventStream, int iterations) {
		try {
			return GIS.trainModel(iterations, new OnePassDataIndexer(eventStream));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public AbstractModel trainModel(EventStream eventStream, int cutoff, int iterations) {
		try {
			return GIS.trainModel(iterations, new OnePassDataIndexer(eventStream, cutoff));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public AbstractModel loadModel(String modelFileName) {
		try {
			return new SuffixSensitiveGISModelReader(new File(modelFileName + ".bin.gz")).getModel();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveModel(String modelFileName, AbstractModel model) {
		GISModelWriter modelWriter;
		try {
			modelWriter = new SuffixSensitiveGISModelWriter(model, new File(modelFileName + ".bin.gz"));
			modelWriter.persist();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
