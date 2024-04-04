<%@page import="org.fsp.tp8boutique.donnees.Produit"%>
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
        <h3 align="center">Modifier produit </h3>
        <form action="AdminServlet">
            <fieldset>
                <span class="label">Référence&nbsp</span>
                <input type="text" name="reference" value=${reference} readonly /><br>
                <span class="label">Libellé &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="text" name="libelle" 
                       value=${boutique.getProduit(reference).getLibelle()} /><br>
                <span class="label"> Prix TTC &nbsp;&nbsp;</span>
                <input type="text" name="prix"
                       value=${boutique.getProduit(reference).getPrix()} /><br>
                <span class="label">Qté stock &nbsp;</span>
                <input type="text" name="quantite"
                       value=${boutique.getProduit(reference).getQuantite()} />
                <input type="submit" name ="btnValiderModifs" value="Valider" >
                <input type="submit" name ="btnAnnuler" value="Annuler" />
            </fieldset>
        </form>
    </body>
</html>
