/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textclassification;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import textclassification.training.*;
/**
 *
 * @author Costin
 */
public class ClassifyOperation implements Operation {

    @Override
    public void execute(String[] cmdLineArgs) {
        String modelPath = cmdLineArgs[1];
        String textFileToClassify = cmdLineArgs[2];
        
         try {
            try (FileInputStream fileIn = new FileInputStream(modelPath); ObjectInputStream in = new ObjectInputStream(fileIn)) {
                BayesClassificationModel model = (BayesClassificationModel) in.readObject();
                String[] words = Utils.readWords(new File(textFileToClassify));
                Map<String, Double> confidenceScores = model.labelsConfidenceScoresForInstance(Arrays.asList(words));
                Optional<Entry<String, Double>> best = confidenceScores.entrySet().stream().reduce((a, b) -> {
                    return a.getValue() > b.getValue() ? a : b;
                });
                
                System.out.println("Confidence scores: ");
                System.out.println(confidenceScores);
                
                System.out.println("Most probable class/label:");
                System.out.println(best.get().getKey());
            }
      } catch (IOException i) {
         System.out.println("Input text file not found, reason: " + i.getMessage());
      } catch (ClassNotFoundException c) {
         System.out.println("Model class not found: " + c.getMessage());
      }
    }
    
}
