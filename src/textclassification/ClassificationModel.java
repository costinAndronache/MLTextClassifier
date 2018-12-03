/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textclassification;
import java.util.*;

/**
 *
 * @author Costin
 */
public interface ClassificationModel {
    List<String> labels();
    Map<String, Double> labelsConfidenceScoresForInstance(List<String> words);
}
