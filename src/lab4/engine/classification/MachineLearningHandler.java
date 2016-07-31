package lab4.engine.classification;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lab4.model.Document;
import lab4.util.FileManager;

/**
 * Created by Ariel on 31-Jul-16.
 */

public class MachineLearningHandler {

	private List<Document> documents;
	private HashMap<String, Long> idfs;

	public MachineLearningHandler(List<Document> documents) {
		this.documents = documents;
		idfs = new HashMap<>();
	}

	public void init() {
		populateTermFrequency();
		populateIDF();
		populateTermImportance();
	}

	public void generateTrainingModel(String filename) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		Map<String, Long> terms = idfs.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
				.limit(1000).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		documents.forEach(document -> {
			stringBuilder.append("class=" + document.getName()).append(" ");
			for (Map.Entry<String, Long> term : terms.entrySet()) {
				long aLong = Math.round(document.getTermImportance().get(term.getKey()));
				stringBuilder.append(term.getKey() + "=" + aLong).append(" ");
			}
			stringBuilder.append(System.getProperty("line.separator"));
		});

		(new FileManager()).writeToFile(filename, stringBuilder.toString());
	}

	private void populateTermFrequency() {
		documents.forEach(document -> document.init(false));
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public HashMap<String, Long> getIdfs() {
		return idfs;
	}

	public Long getIDF(String term) {
		if (idfs.containsKey(term)) return idfs.get(term);
		else return 0l;
	}

	public void setIdfs(HashMap<String, Long> idfs) {
		this.idfs = idfs;
	}

	private void populateIDF() {
		documents.forEach(document -> {
			document.getTermsMap().forEach((k, v) -> {
				if (idfs.containsKey(k)) idfs.put(k, idfs.get(k) + 1);
				else idfs.put(k, 1l);
			});
		});
		// idfs.forEach((s, v) -> idfs.put(s,Math.log(documents.size()/v)));
		idfs.forEach((s, v) -> idfs.put(s, v));
	}

	private void populateTermImportance() {
		documents.forEach(document -> {
			document.getTfs().forEach((s, aLong) -> {
				Long factor = idfs.get(s);
				document.getTermImportance().put(s, Double.valueOf(factor));// aLong*factor);
			});
		});

		// add idfs zero para termos nao existentes no doc
		idfs.forEach((s, aLong) -> documents.forEach(document -> {
			if (!document.getTermImportance().containsKey(s)) document.getTermImportance().put(s, 0d);
		}));
	}

}
