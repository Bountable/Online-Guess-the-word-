<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guess the Word Game</title>
    <link rel="stylesheet" href="style.css"> <!-- Link to your CSS for styling -->
    <% if (session.getAttribute("message") != null) { %>
    <p><%= session.getAttribute("message") %></p>
    <% session.removeAttribute("message"); %>
<% } %>
</head>
<body>
    <div id="main-container">
        <h1>Welcome to Guess the Word!</h1>
        <p>Try to guess the word by suggesting letters. Can you figure it out?</p>
        
        <form action="game" method="get">
            <input type="hidden" name="action" value="new">
            <button type="submit">Start New Game</button>
        </form>
        
         <form action="game" method="post">
        <input type="hidden" name="action" value="load">
        <input type="text" name="username" required placeholder="Username to load game">
        <button type="submit">Load Game</button>
        </form>
        
     
</form>
    </div>
</body>
</html>
