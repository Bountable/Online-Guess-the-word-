package pkg;


import java.io.*;
import java.util.HashMap;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.io.IOException;

public class Database implements Serializable  {
    private static Database instance;
    private HashMap<String, GameBean> saves;
    private String saveFilePath;

    // Private constructor to prevent instantiation
    private Database(ServletContext context) {
        String relativePath = "/WEB-INF/gameSaves.ser";
        this.saveFilePath = context.getRealPath(relativePath);
        loadSaves();
    }

    // Public method to get instance
    public static synchronized Database getInstance(ServletContext context) {
        if (instance == null) {
            instance = new Database(context);
        }
        return instance;
    }




    @SuppressWarnings("unchecked")
    private void loadSaves() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFilePath))) {
            saves = (HashMap<String, GameBean>) ois.readObject();
        } catch (Exception e) {
            saves = new HashMap<>(); // Initialize empty map if failed to load or file does not exist
        }
    }

    private void saveSaves() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFilePath))) {
            oos.writeObject(saves);
            System.out.println("Successfully saved games to " + saveFilePath); // Debugging
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveGame(String username, GameBean gameState) {
        saves.put(username, gameState);
        saveSaves(); // Use saveFilePath for saving
        System.out.println("Game saved for username: " + username + " at path: " + saveFilePath);
    }
    
   
    public GameBean loadGame(String username) {
        System.out.println("Attempting to load game for username: " + username);
        if (saves.containsKey(username)) {
            System.out.println("Game found for username: " + username);
            return saves.get(username);
        } else {
            System.out.println("No game found for username: " + username + ". Available usernames: " + saves.keySet());
            return null;
        }
    }
}
