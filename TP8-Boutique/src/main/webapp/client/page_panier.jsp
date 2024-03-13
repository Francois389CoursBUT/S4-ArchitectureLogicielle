<%--
  Created by IntelliJ IDEA.
  User: francois
  Date: 13/03/2024
  Time: 08:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>BOutique en ligne</title>
    </head>
    <body>
    <h1>Boutique en  ligne</h1>
    <h2>Panier de </h2>

    <table>
        <tr>
            <th>Produit</th>
            <th>Prix</th>
            <th>Quantité</th>
            <th>Total</th>
        </tr>
        <!-- boucle sur les produits du panier -->
    </table>
    <h3>Total : </h3>
    <form action="page_achats.jsp">
        <input type="submit" value="Continuer mes achats">
    </form>
    <form action="page_commande.jsp">
        <input type="submit" value="Commander">
    </form>

    </body>
</html>
