/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textclassification;
import java.io.*;
import java.util.*;
import textclassification.training.*;
/**
 *
 * @author Costin
 */
public class TrainOperation implements Operation  {

    @Override
    public void execute(String[] cmdLineArgs) {
        String trainingDirectory = cmdLineArgs[1];
        String outputModelPath = cmdLineArgs[2];
        List<TrainingInstance> instances = new DirectoryTrainingInstancesProvider().
                getTrainingInstancesIn(trainingDirectory);
        BayesClassificationModel model = new BayesModelGenerator().generateModelFrom(instances);
        
        try {
            try (FileOutputStream fileOut = new FileOutputStream(outputModelPath)) {
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(model);
                out.close();
            }
         System.out.println("Serialized the model in " + outputModelPath);
      } catch (IOException i) {
         System.out.println("Could not serialize the model. Reason " + i.getMessage());
      }
    }
}
