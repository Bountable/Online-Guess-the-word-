Online "Guess the word" Game
Developed using Tomcat and JSP

Game Initialization:
At the beginning of each game, the system randomly selects an English word from a text file. Only 2/3 of the letters (and at least one) in the word are initially masked. The masked word is presented to the user as a puzzle, and the user's goal is to guess the word by revealing the masked letters step by step. The text file will be given, it has one word in each line with up to 20 lines.

Game Rounds:
In each round, the player will nominate one letter from A-Z. If the nominated letter is part of the word, it is revealed (unmasked) along with any previously revealed letters and the remaining masked letters, presented to the player for the next round. Note, a letter could appear multiple times in a word. If the letter is not part of the word, the game continues to the next round.

Game Termination:
The game terminates when the player correctly guesses the entire word (all letters are unmasked).

Scoring System:
The game rewards the player based on how quickly they guess the word. The reward formula is (2 * word length - round number) * 10. For example, guessing a word of length 7 in 10 rounds awards the player with 40 points.

Game Interface:
The first page allows the player to start a new game or load an existing game. During a new game, the player inputs a letter to guess the word, and the system reveals correct letters accordingly. The game displays all previously nominated letters (up to the last three rounds) to assist the player. Save and Load Game: The game provides an option to "Save" the current game state, which is stored on the server, for example, in a text file. Each user can have only one saved game, and saving a new game overwrites the previous saved data. Loading a saved game clears the current game progress and loads the saved state referenced by the user's username. These requirements ensure an engaging and challenging gameplay experience while preventing cheating through save/load mechanisms

• The application’s structure, i.e. MVC and relationships among objects etc. Model GameBean manage game state DataBase handles game state data

Controller GameController processes HTTP request manipulate game states

View JSP pages which show game Pages

• What is the purpose of each of your objects? GameBean stores word masked word guessed letters, scroe, round info manages game logic DataBase singleton class, loads gamestate found in WEB-INF

GameController servlet that handles requests manages game sessiosn and makes responsed baseed on user input

• How did you implement session tracking? using HTTPSession to track sessions GameBean to store instance in session attribute

• How did you implement game saving? uses DataBase to save state in serialized file system retrieved using string

• The URL the marker needs to visit to start the application. http://localhost:8080/c3404758_assignment2/
