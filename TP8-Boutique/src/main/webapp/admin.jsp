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
        <h3 align="center">Gestion du stock</h3>
        <form action="AdminServlet">
            <fieldset>
                <div align="center">
                    <span class="label">Référence</span>     
                    <select name="produits"> 
                        ${boutique.balisesOptionsRefsProduits()}           
                    </select>
                    <br><br>
                    <input type="submit" name="btnModifierProduit" value="Modifier produit"/>
                    <input type="submit" name="btnNouveauProduit" value="Nouveau produit" />
                    <input type="submit" name="btnSeDeconnecter" value="Se déconnecter" />
                </div>
            </fieldset>
        </form>
    </body>
</html>
