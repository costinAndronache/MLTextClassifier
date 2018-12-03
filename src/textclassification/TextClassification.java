/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textclassification;
import java.util.Arrays;
import java.util.*;
import textclassification.training.*;

/**
 *
 * @author Costin
 */
public class TextClassification {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Map<String, Operation> operations = new HashMap<>();
        operations.put("train", new TrainOperation());
        operations.put("classify", new ClassifyOperation());
        
        String operation = args[0];
        Operation o = operations.getOrDefault(operation, new UnrecognizedOperation());
        o.execute(args);
    }
    
}
