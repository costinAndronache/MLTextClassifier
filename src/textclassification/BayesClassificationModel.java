/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textclassification;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Costin
 */
public class BayesClassificationModel implements ClassificationModel {
    
    private Map<String, Double> labelsWithAprioriProbabilities;
    private Map<String, Map<String, Double>> wordProbabilitiesInLabels;
    
    public Map<String, Double> labelsWithAprioriProbabilities() {
        return this.labelsWithAprioriProbabilities;
    }
    
    public Map<String, Map<String, Double>> wordProbabilitiesInLabels() {
        return this.wordProbabilitiesInLabels;
    }
    
    public BayesClassificationModel(Map<String, Double> labelsWithAprioriProbabilites,
            Map<String, Map<String, Double>> wordProbabilitiesInLabels) {
        this.labelsWithAprioriProbabilities = labelsWithAprioriProbabilites;
        this.wordProbabilitiesInLabels = wordProbabilitiesInLabels;
    }
    
    
    @Override
    public List<String> labels() {
        return new ArrayList<String>(labelsWithAprioriProbabilities.keySet());
    }

    @Override
    public Map<String, Double> labelsConfidenceScoresForInstance(List<String> words) {
        Set<String> vocabulary = this.wordProbabilitiesInLabels.keySet();
        List<String> filteredWords = words.stream().filter(w -> vocabulary.contains(w)).collect(Collectors.toList());
        Map<String, Double> result = new HashMap<>();
        
        labels().forEach((label) -> {
            double probLabel = this.labelsWithAprioriProbabilities.get(label);
            double probWords = filteredWords.stream().map(w -> this.wordProbabilitiesInLabels.get(w).get(label))
                    .reduce(0.0, (a, b) -> Math.log(a) + Math.log(b));
            result.put(label, Math.log(probLabel) + Math.log(probWords));
        });
        return result;
    }
    
}
