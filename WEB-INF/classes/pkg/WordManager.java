package pkg;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class WordManager {

  //get random word from the txt file provdied
    public static String getRandomWord(String filePath) {
        try {
            // Read all lines 
            List<String> words = Files.readAllLines(Paths.get(filePath));
            
            // Check if the list is empty to avoid IndexOutOfBoundsException.
            if (words.isEmpty()) {
                return null; 
            }
            
            // Select word
            Random random = new Random();
            return words.get(random.nextInt(words.size())).trim();
        } catch (IOException e) {
            
            e.printStackTrace();
            return null; 
        }
    }
}
