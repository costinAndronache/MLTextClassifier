/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textclassification;

import java.util.*;
import java.util.Map;

/**
 *
 * @author Costin
 */
public class BayesModelSerialization {
    public static String toText(BayesClassificationModel model){
        StringBuilder sb = new StringBuilder();
        
        Map<String, Double> labelsProbabilities = model.labelsWithAprioriProbabilities();
        List<String> labels = new ArrayList<>(labelsProbabilities.keySet());
        labels.forEach(label -> sb.append(label + " "));
        sb.append("\n");
        labels.forEach(label -> sb.append(labelsProbabilities.get(label)));
        sb.append("\n");
        Map<String, Map<String, Double>> wordProbs = model.wordProbabilitiesInLabels();
        Set<String> words = wordProbs.keySet();
        words.forEach(word -> {
            sb.append(word + " ");
            labels.forEach(label -> {sb.append(wordProbs.get(word).get(label) + " ");});
            sb.append("\n");
        });
        
        return sb.toString();
    }
    
    public static BayesClassificationModel fromText(String text) {
        return null;
    }
}
