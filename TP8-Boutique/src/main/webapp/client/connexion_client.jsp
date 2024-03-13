<%--
  Created by IntelliJ IDEA.
  User: francois
  Date: 13/03/2024
  Time: 08:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Boutique</title>
    </head>
    <body>
    <jsp :usebean id="boutique" scope="application" class="org.fsp.tp8boutique.javabeans.Boutique"/>
    <h1>Bienvenue dans la boutique</h1>
    <form action="">
        <label for="login">Login</label>
        <input type="text" name="login" placeholder="Entrez votre login">
        <label for="password">Mot de passe</label>
        <input type="password" name="password" placeholder="Entrez votre mot de passe">
        <input type="submit" value="Se connecter">
        <input type="submit" value="S'enregistrer">
    </form>
    </body>
</html>
