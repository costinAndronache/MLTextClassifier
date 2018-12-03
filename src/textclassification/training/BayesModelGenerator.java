/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textclassification.training;

import java.io.Serializable;
import java.util.*;
import textclassification.BayesClassificationModel;

/**
 *
 * @author Costin
 */
public class BayesModelGenerator implements Serializable {
    
    public BayesClassificationModel generateModelFrom(List<TrainingInstance> instances) {
        Map<String, Double> labelsWithAprioriProbabilites = new HashMap<>();
        Map<String, Map<String, Double>> wordProbabilitiesInLabels = new HashMap<>();
        
        Map<String, Integer> labelsCount = new HashMap<>();
        instances.forEach(inst -> {
            labelsCount.put(inst.label(), labelsCount.getOrDefault(inst.label(), 0) + 1);
        });
        
        labelsCount.forEach((label, count) -> {
            labelsWithAprioriProbabilites.put(label, (double)count/(double)instances.size());
        });
        
        Map<String, Map<String, Double>> wordOccurencesInLabels = new HashMap<>();
        Map<String, Double> wordCountPerLabel = new HashMap<>();
        instances.forEach(inst -> {
            for(String word : inst.words()){
                Map<String, Double> countInLabel = wordOccurencesInLabels.get(word);
                if(countInLabel == null){
                    countInLabel = new HashMap<>();
                }
                countInLabel.put(inst.label(), countInLabel.getOrDefault(inst.label(), 0.0) + 1.0);
                wordOccurencesInLabels.put(word, countInLabel);
            }
            wordCountPerLabel.put(inst.label(), wordCountPerLabel.getOrDefault(inst.label(), 0.0) + (double)inst.words().length);
        });
        
        double vocabularyCount = (double)wordOccurencesInLabels.size();
        wordOccurencesInLabels.forEach((word, occurenceInLabels) -> {
             Map<String, Double> probabilitiesInLabels = new HashMap<>();
             occurenceInLabels.forEach((label, count) -> {
                 probabilitiesInLabels.put(label, count / 
                         (wordCountPerLabel.get(label) + vocabularyCount));
             });
             
             //special case: for all labels which do not contain the current word
             //the associated probability will be set
             Set<String> voidLabels = new HashSet<>(labelsCount.keySet());
             voidLabels.removeAll(occurenceInLabels.keySet());
             voidLabels.forEach(label -> {
                 probabilitiesInLabels.put(label, 1 / 
                         (wordCountPerLabel.get(label) + vocabularyCount));
             }); 
             
             wordProbabilitiesInLabels.put(word, probabilitiesInLabels);

         });
        
        return new BayesClassificationModel(labelsWithAprioriProbabilites, wordProbabilitiesInLabels);
    }
}
