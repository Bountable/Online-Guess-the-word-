package pkg;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GameBean {
    private String currentWord;
    private String maskedWord;
    private Set<Character> guessedLetters;
    private int roundNumber;
    private int score;
    private boolean isMaskedWordInitialized = false;


    public GameBean() {
        guessedLetters = new HashSet<>();
        roundNumber = 0;
        score = 0;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
        initializeMaskedWord(); // Generate the initial masked word only once
    }

    public String getMaskedWord() {
        return maskedWord;
    }

    private void setMaskedWord(String maskedWord) {
        this.maskedWord = maskedWord;
    }

    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void incrementRoundNumber() {
        this.roundNumber++;
    }

    public int getScore() {
        return score;
    }

  
    public void updateScore() {
        // (2 * word length - round number) * 10
        this.score = (2 * currentWord.length() - roundNumber) * 10;
    }

    private void initializeMaskedWord() {
        if (currentWord == null || isMaskedWordInitialized) {
            return; // skip if already initalized
        }
    
        int totalLettersToMask = (int) Math.ceil(currentWord.length() * 2 / 3.0); // hide 2/3rds of the word
        List<Integer> indexesToMask = new ArrayList<>();
        for (int i = 0; i < currentWord.length(); i++) {
            indexesToMask.add(i); // Add every word
        }
        Collections.shuffle(indexesToMask); // Randomly shuffle the indexes
    
        StringBuilder maskedWordBuilder = new StringBuilder(currentWord);
        for (int i = 0; i < totalLettersToMask; i++) {
            maskedWordBuilder.setCharAt(indexesToMask.get(i), '_'); //mask the the random index
        }
    
        // Ensure one letter is shown
        if (totalLettersToMask >= currentWord.length()) {
            int indexToUnmask = indexesToMask.get(0); // show first letter 
            maskedWordBuilder.setCharAt(indexToUnmask, currentWord.charAt(indexToUnmask));
        }
    
        this.maskedWord = maskedWordBuilder.toString();
    
        isMaskedWordInitialized = true; 
    }

    public void guessLetter(char letter) {
        if (currentWord == null) {
            return;
        }
    
        // add letter if upperacse or lowercase
        if (currentWord.toUpperCase().contains(String.valueOf(letter).toUpperCase())) {
            guessedLetters.add(Character.toUpperCase(letter)); // store in all upperacse makes it look nicer
        }
    
        incrementRoundNumber();
    
        // Update oonlyt the importand parts
        StringBuilder updatedMaskedWord = new StringBuilder(maskedWord);
        for (int i = 0; i < currentWord.length(); i++) {
            if (Character.toUpperCase(currentWord.charAt(i)) == Character.toUpperCase(letter)) {
                updatedMaskedWord.setCharAt(i, currentWord.charAt(i)); // Reveal the correctly guessed letter
            }
        }
        maskedWord = updatedMaskedWord.toString();
    
        // update score 
    }

   

    private void maskWord() {
        if (currentWord == null || currentWord.isEmpty()) {
            return;
        }
    
        int totalLettersToMask = (int) Math.ceil(currentWord.length() * 2 / 3.0); // Calculate 2/3 of the word to be masked
        List<Integer> indexesToMask = new ArrayList<>();
        for (int i = 0; i < currentWord.length(); i++) {
            indexesToMask.add(i); // Add every index of the word to the list
        }
        Collections.shuffle(indexesToMask); // Randomly shuffle the indexes
    
        StringBuilder maskedWordBuilder = new StringBuilder(currentWord);
        for (int i = 0; i < totalLettersToMask; i++) {
            maskedWordBuilder.setCharAt(indexesToMask.get(i), '_'); // Mask the letter at the shuffled index
        }
    
        // Ensure at least one letter is always unmasked, especially important for short words
        if (totalLettersToMask >= currentWord.length()) {
            int indexToUnmask = indexesToMask.get(0); // Unmask the first letter in the shuffled list
            maskedWordBuilder.setCharAt(indexToUnmask, currentWord.charAt(indexToUnmask));
        }
    
        this.maskedWord = maskedWordBuilder.toString();
    }

    
   
    // Check if the game is over (i.e., the word has been fully guessed)
    public boolean isGameOver() {
        return !maskedWord.contains("_");
    }
}
