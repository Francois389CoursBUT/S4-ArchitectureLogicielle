
<%@page import="java.util.ArrayList"%>
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
        <h3 align="center">Merci  ${client.identifiant} d'avoir passé commande !<br>
            Numéro de commande : ${commande.numeroCommande}
        </h3>
        <table border="0" width="80" cellspacing="5" cellpadding="3">
            <thead> 
                <tr>
                    <th>Reférence</th>
                    <th>Libellé</th>
                    <th>Prix</th>
                    <th>Quantité</th>
                    <th>Montant TTC</th>
                </tr>
            </thead>
            <tbody> 
                ${commande.balisesTdItemsCommande()}          
            </tbody>
        </table><br> Total : ${commande.total} <br>
        <form action="ClientServlet">
            <div align="center"><input type="submit" name="btnContinuerAchats" value="Continuer les achats" /></div>
        </form>  
    </body>
</html>
