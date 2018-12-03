/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textclassification;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Costin
 */
public class Utils {
    public static String[] readWords(File file){
        try {
            String text = new String(Files.readAllBytes(file.toPath()));
            String[] words = text.trim().split("\\s+");
            return words;

        } catch (IOException ex) {
        }
        return new String[]{};
    }
}