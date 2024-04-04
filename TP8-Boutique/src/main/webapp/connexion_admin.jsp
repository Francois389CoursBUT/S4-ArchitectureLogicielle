
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Boutique en ligne</title>
        <link type="text/css" rel="stylesheet" href="styles.css"/>
    </head>
    <body>
      <h1 align="center">Boutique en ligne</h1>
      <jsp:useBean id="boutique" scope="application" class="org.fsp.tp8boutique.javabeans.Boutique" />
      <fieldset> <legend align="center">Connexion Admin</legend>
        <form action="AdminServlet">
        <div align="center">
            Identifiant <input type="text" name="identifiant" value="" />
            Mot de passe <input type="text" name="motDePasse" value="" />
            <input type="submit" name="btnSeConnecter" value="Se connecter" />
        </div>
      </form>
      </fieldset>
    </body>
</html>
