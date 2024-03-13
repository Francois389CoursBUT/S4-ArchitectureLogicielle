package org.fsp.tp8boutique.donnees;

public class Produit  {

    private String refproduit;
    private String libelle;
    private float prix;
    private int quantite;

    public Produit(String refproduit, String libelle, float prix, int quantite) {
        this.refproduit = refproduit;
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
    }

    public String getRefproduit() {
        return refproduit;
    }

    public String getLibelle() {
        return libelle;
    }

    public float getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setRefproduit(String refproduit) {
        this.refproduit = refproduit;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

}
