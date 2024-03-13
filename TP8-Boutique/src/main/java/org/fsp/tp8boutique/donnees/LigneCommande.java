package org.fsp.tp8boutique.donnees;

import java.io.Serializable;

public class LigneCommande implements Serializable {
    
    private Produit produit;
    private int quantite;

    public LigneCommande(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }
    
    public int getQuantite() {
        return quantite;
    }

}
