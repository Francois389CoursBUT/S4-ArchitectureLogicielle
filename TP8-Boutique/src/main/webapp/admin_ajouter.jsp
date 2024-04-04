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
        <h3 align="center">Ajouter un produit</h3>
        <form action="AdminServlet">
            <fieldset>
                <span class="label">Référence</span>
                <input type="text" name="reference"/><br>
                <span class="label">Libellé &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="text" name="libelle"/>
                <input type="submit" name ="btnValiderAjout" value="Valider " /><br>
                <span class="label"> Prix TTC&nbsp;&nbsp;</span>
                <input type="text" name="prix"/>
                <input type="submit" name ="btnAnnuler" value="Annuler" /><br>
                <span class="label">Qté stock&nbsp;</span>
                <input type="text" name="quantite"/><br>
            </fieldset>
        </form>
    </body>
</html>
