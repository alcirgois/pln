package lab4.util;

import lab3.model.Document;

import java.util.List;

/**
 * Created by Ariel on 31-Jul-16.
 */
public class MachineLearningHandler {

    public void getStatistics(List<Document> documents, List<String> classes) {
        int results[] = {0, 0, 0, 0}; //results[0] = nTrue, results[1] = nWrong, results[2] = nTruePositives, results[3] = nFalsePositives

        documents.forEach(document -> {
            if (classes.remove(0).equals(document.getClasse())) {
                results[0]++;
                if (document.getClasse() == "positivas")
                    results[2]++;
            } else {
                System.out.println("Erro ao classificar instancia: " + (results[0] + results[1]));
                results[1]++;
                if (document.getClasse() == "positivas")
                    results[3]++;
            }
        });

        System.out.println("\n------Calculando taxa de erros e acertos------\n");

        System.out.println("Correct predictions:  " + results[0]);
        if ((results[2] + results[3]) != 0)
            System.out.println("Reccal: " + results[2]/(results[2] + results[3]));
        else
            System.out.println("Reccal: " + 0);
        if ((results[0]) != 0)
            System.out.println("Precision: " + results[2]/results[0]);
        else
            System.out.println("Precision: " + 0);
        System.out.println("True Positive Rate: " + results[2]);
        System.out.println("True Negative Rate: " + (results[1] - results[3]));
        System.out.println("Accuracy: " + (results[0]/(results[0]+results[1]))*100 + "%");
    //    System.out.println("F-Measure: ");
    }
}
