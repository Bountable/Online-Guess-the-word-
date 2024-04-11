<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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
    <div id="main-container">
        <h1>Guess the Word Game</h1>
        <p>Page 1 of 3</p>
        <p>School of Electrical Engineering and Computer Science</p>
        <p>The University of Newcastle</p>
        <p>SENG2050 Introduction to Web Engineering</p>
        <p>Assignment 2 (15%) – Semester 1, 2024</p>
        
        <h2>Assignment 2 Specification</h2>
        <p>1. Introduction</p>
        <p>The primary objective of this assignment is to develop an online game called "Guess the Word." Below
        are the detailed game rules and functionalities:</p>
        <!-- Rest of the assignment specification goes here -->

        <h2>Game Interface</h2>
    <c:choose>
    <c:when test="${gameWon}">
        <h3>Congratulations! You've guessed the word correctly: ${game.currentWord}</h3>
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
