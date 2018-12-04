/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textclassification.training;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import textclassification.Utils;

/**
 *
 * @author Costin
 */
public class DirectoryTrainingInstancesProvider {
    
    public List<TrainingInstance> getTrainingInstancesIn(String directoryPath){
        List<TrainingInstance> result = new ArrayList<>();
        File dir = new File(directoryPath);
        File[] files = dir.listFiles();
        if(files == null){
            System.out.println("EMPTY TRAINING DIRECTORY " + dir.getAbsolutePath());
            return new ArrayList<>();
        }
        for(File labelDir : dir.listFiles()){
            if(labelDir.isDirectory()){
                for(File textFile: labelDir.listFiles()){
                if(textFile.isFile()) {
                    String[] words = Utils.readWords(textFile);
                    if(words.length > 0){
                        result.add(new TrainingInstance(words, labelDir.getName()));
                    }
                }
             }
          }
            
        }
        
        return result;
    }
}
