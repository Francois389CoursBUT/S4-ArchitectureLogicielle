<%--
  Created by IntelliJ IDEA.
  User: francois
  Date: 13/03/2024
  Time: 08:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Boutique en ligne</title>
    </head>
    <body>
    <h1>Bienvenue sur la boutique en ligne</h1>
    <h2>Bienvenu <%= pseudo%>
    </h2>
    <div class="achat">
        <form>
            <label>Produit</label>
            <select name="produit">
                <option value="1">Produit 1</option>
                <option value="2">Produit 2</option>
                <option value="3">Produit 3</option>
            </select>
            <label>Quantit&eacute;</label>
            <input type="number" name="quantite" value="1">
            <input type="submit" value="Ajouter au panier">
            <input type="submit" value="Supprimer du pagnier">
        </form>
        <form action="">
            <input type="submit" value="Voir le panier">
        </form>
    </div>
    <div class="commandes">
        <label>Commande enregistr&eacute;es : </label>
        <form>
            <input type="submit" value="Se déconnecter">
        </form>
    </div>

    </body>
</html>
