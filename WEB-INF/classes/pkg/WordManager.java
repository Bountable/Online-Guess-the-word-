package pkg;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class WordManager {

    /**
     * Gets a random word from the specified file path.
     * @param filePath the path to the text file containing words.
     * @return A random word from the file.
     */
    public static String getRandomWord(String filePath) {
        try {
            // Read all lines from the specified file into a List.
            List<String> words = Files.readAllLines(Paths.get(filePath));
            
            // Check if the list is empty to avoid IndexOutOfBoundsException.
            if (words.isEmpty()) {
                return null; // Or consider throwing an exception.
            }
            
            // Select a random word from the list.
            Random random = new Random();
            return words.get(random.nextInt(words.size())).trim();
        } catch (IOException e) {
            // Handle the exception (e.g., file not found, access denied).
            e.printStackTrace();
            return null; // Or consider throwing a custom exception.
        }
    }
}
