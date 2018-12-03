/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textclassification.training;
/**
 *
 * @author Costin
 */
public class TrainingInstance {
    private String[] words;
    private String label; 
    public TrainingInstance(String[] words, String label){
        this.words = words;
        this.label = label;
    }
    
    public String label() {
        return label;
    }
    
    public String[] words() {
        return words;
    }
}
