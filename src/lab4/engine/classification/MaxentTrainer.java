package lab4.engine.classification;

import java.io.File;
import java.io.IOException;

import opennlp.maxent.GIS;
import opennlp.maxent.io.GISModelWriter;
import opennlp.maxent.io.SuffixSensitiveGISModelReader;
import opennlp.maxent.io.SuffixSensitiveGISModelWriter;
import opennlp.model.AbstractModel;
import opennlp.model.DataIndexer;

/**
 * @author Emanuel
 *
 */
public class MaxentTrainer {

	public static AbstractModel trainModel(DataIndexer dataIndexer, int iterations) {
		return GIS.trainModel(iterations, dataIndexer);
	}
	
	public static AbstractModel trainModel(DataIndexer dataIndexer, int cutoff, int iterations) {
		return GIS.trainModel(iterations, dataIndexer);
	}

	public static void saveModel(String modelFileName, AbstractModel model) {
		GISModelWriter modelWriter;
		try {
			modelWriter = new SuffixSensitiveGISModelWriter(model, new File(modelFileName + ".bin.gz"));
			modelWriter.persist();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static AbstractModel loadModel(String modelFileName) {
		try {
			return new SuffixSensitiveGISModelReader(new File(modelFileName + ".bin.gz")).getModel();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
