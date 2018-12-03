/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textclassification;

/**
 *
 * @author Costin
 */
public class UnrecognizedOperation implements Operation {

    @Override
    public void execute(String[] cmdLineArgs) {
        System.out.println("Unrecognized operation: " + cmdLineArgs);
        System.out.println("Usage:");
        System.out.println("java -jar program train PATH_TO_TRAINING_DIRECTORY OUTPUT_MODEL_PATH");
        System.out.println("java -jar program classify PATH_TO_MODEL_FILE PATH_TO_TEXT_DOCUMENT");
        System.out.println("PATH_TO_TRAINING_DIRECTORY must consist of one or more subdirectories only, "
                + "each subdirectory must consist of one or mrre text files only\n"
                + "The subdirectory names represent the class/label/category of their text files' content."
                + "Actual file names are not taken into consideration");
        
    }
    
}
