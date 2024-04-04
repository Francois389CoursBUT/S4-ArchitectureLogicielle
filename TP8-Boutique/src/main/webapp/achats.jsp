<%@page import="org.fsp.tp8boutique.donnees.Client"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h3 align="center">Bienvenue ${client.identifiant} </h3>
    <form action="ClientServlet">
        <fieldset>
            <span class="label">Produits </span>     
            <select name="produits"> 
                ${boutique.balisesOptionsInfosProduits()}           
            </select>
            <span class="label"> Quantité </span>
            <input type="text" name="quantite"/>
            <div align="center">
                <input type="submit" name="btnAjouterAuPanier" value="Ajouter au panier"/>
                <input type="submit" name="btnSupprimerDuPanier" value="Supprimer du panier"/>
                <input type="submit" name ="btnVoirPanier" value="Voir panier" />
            </div>
        </fieldset>
        <fieldset>
            Commandes enregistrées 
            <% Client client = (Client)session.getAttribute("client");
                if(client.getCommandes().isEmpty()) out.println(" : Aucune commande");
                else out.println(
                        "<select name=\"commandes\">"
                            + client.balisesCommandesEnregistrees()
                      + "</select><br><br>");
            %>
            <div align="center">                
                <input type="submit" ${client.getCommandes().isEmpty() ? 'disabled' : ''}
                       name="btnAfficherCommande" value="Afficher commande" />
                <input type="submit" name="btnSeDeconnecter" value="Se déconnecter" />
            </div>
        </fieldset>
    </form>
  </body>
</html>
