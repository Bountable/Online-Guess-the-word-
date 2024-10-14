<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
    <title>Guess the Word Game</title>
    <link rel="stylesheet" href="style.css"> 
    <% 
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache"); 
		response.setHeader ("Expires", "0"); 
    %>

    <% if (session.getAttribute("message") != null) { %>
    <p><%= session.getAttribute("message") %></p>
    <% session.removeAttribute("message"); %>
<% } %>

</head>
<body>

<h2>Game Interface</h2>
<c:choose>
    <c:when test="${not fn:contains(game.maskedWord, '_')}">
        <h3>Congratulations! You've completed the word: ${game.currentWord}</h3>
        <p>Score: ${game.score}</p>
        <form action="game" method="get">
            <input type="hidden" name="action" value="new">
            <button type="submit">Start a New Game</button>
        </form>
    </c:when>
    <c:otherwise>
        <p>Word to guess: ${game.maskedWord}</p>
        <p>Guessed Letters: ${game.guessedLetters}</p>
        <p>Score: ${game.score}</p>
        <p>Round: ${game.roundNumber}</p>
        <form action="game" method="post">
            Guess a letter: <input type="text" name="guess" maxlength="1" required>
            <button type="submit">Submit</button>
        </form>
            <% if (session.getAttribute("message") != null) { %>
        <div class="error">
            <p><%= session.getAttribute("message") %></p>
        </div>
        <% session.removeAttribute("message"); %>
    <% } %>
    </c:otherwise>
</c:choose>

        <form action="game" method="post">
        <input type="hidden" name="action" value="save">
        <input type="text" name="username" required placeholder="Username to save game">
        <button type="submit">Save Game</button>
</form>
    </div>

    
</body>
</html>
