<%@ page import="java.util.List" %>
<%@ page import="org.fsp.tp7chatroom.serveur.ChatRoom" %><%--
  Created by IntelliJ IDEA.
  User: francois
  Date: 12/03/2024
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>ChatRoom</title>
    </head>
    <% String pseudo = request.getParameter("pseudo"); %>
    <body>
        <h1>Bienvenue <%= pseudo %></h1>
        <form action="ChatRoom" method="post">
            <input type="text" name="message" />
            <input type="submit" value="Envoyer" />
        </form>
        <%
            List<String> messages = ChatRoom.getInstance().getMessages();
            for (String message : messages) {
        %>
            <p><%= message %></p>
        <%
            }
        %>

    </body>
</html>
