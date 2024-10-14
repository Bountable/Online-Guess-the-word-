package pkg;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet("/game")
public class GameController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

          // Prevent page caching
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0); // Proxies.

        // Start a new game
        HttpSession session = request.getSession();
        GameBean gameBean = (GameBean) session.getAttribute("game");

        if (gameBean == null || "new".equals(request.getParameter("action"))) {
            gameBean = new GameBean();
            String word = WordManager.getRandomWord(getServletContext().getRealPath("/WEB-INF/words.txt"));
            gameBean.setCurrentWord(word);
            session.setAttribute("game", gameBean);
        }

        

        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Response headers for no caching
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    HttpSession session = request.getSession();
    GameBean gameBean = (GameBean) session.getAttribute("game");
    String action = request.getParameter("action");

    // Handle different actions
    if ("save".equals(action)) {
        handleSaveAction(request, response, session);
    } else if ("load".equals(action)) {
        handleLoadAction(request, response, session);
    } else {
        // Handle guessing a letter
        if (gameBean == null) {
            response.sendRedirect("game?action=new");
            return;
        }

        String guess = request.getParameter("guess");
        if (guess != null && guess.length() == 1 && Character.isLetter(guess.charAt(0))) {
            char guessedLetter = Character.toUpperCase(guess.charAt(0));

            if (gameBean.getGuessedLetters().contains(guessedLetter)) {
                session.setAttribute("message", "This letter has already been guessed. Try a different one.");
            } else {
                gameBean.guessLetter(guessedLetter);
                gameBean.updateScore();
            }
        } else {
            session.setAttribute("message", "Invalid input. Please enter a single letter.");
        }

        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }
}

    //handles saving of a game
    private void handleSaveAction(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String username = request.getParameter("username");
        GameBean gameBean = (GameBean) session.getAttribute("game");
    
        if (username != null && gameBean != null) {
            Database db = Database.getInstance(getServletContext());
            db.saveGame(username, gameBean);
            session.setAttribute("message", "Game saved successfully.");
            response.sendRedirect("game.jsp");
        }
    }

    //handles loading of a saved game
    private void handleLoadAction(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String username = request.getParameter("username");
        Database db = Database.getInstance(getServletContext());
        GameBean loadedGame = db.loadGame(username);
    
        if (loadedGame != null) {
            session.setAttribute("game", loadedGame);
            session.setAttribute("message", "Game loaded successfully.");
            request.getRequestDispatcher("/game.jsp").forward(request, response);
        } else {
            session.setAttribute("message", "No saved game found for username: " + username);
            response.sendRedirect("noGameFound.jsp");
        }
    }
}
