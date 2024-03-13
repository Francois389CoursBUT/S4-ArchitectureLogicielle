package org.fsp.tp8boutique.donnees;

import java.util.ArrayList;
import java.util.List;

public class Commande  {

    private static int compNumeros = 10;
    private int numeroCommande; 
    private Client client;
    private List<LigneCommande> lignesCommandes;  
    private float total;
    
    /** Crée une commande vide avec un numéro de commande. */
    public Commande() {
        numeroCommande = compNumeros++;
        lignesCommandes = new ArrayList();
        total = 0;
    }

    /** Ajoute une ligne de commande avec le produit et la quantité specifiés. */
    public void ajouterLigneCommande(Produit p, int quantite) {
        LigneCommande lc = new LigneCommande(p, quantite);
        lignesCommandes.add(lc);
        total += p.getPrix() * quantite;
    }

    /** Associe à la commande le client spécifié et ajoute la commande à la liste des commandes
     * du client.
     */
    public void setClient(Client client) {
        this.client = client;
        client.ajouterCommande(this);
    }
    
    public int getNumeroCommande() {
        return numeroCommande;
    }

    public float getTotal() {
        return total;
    }

    /**
   * Renvoie un texte correspondant aux items d'un tableau Html avec la balise tbody
   * Texte de la forme :
   * "<tr> <td> [Libelle de produit 1] </td>
   *       <td> [Libelle de produit 2] </td>
   *        ... 
   *  </tr>"
   */
    public String balisesTdItemsCommande() {
        String result ="";   
        Produit produit;
        int quantite;
        for(LigneCommande ligneCommande : lignesCommandes) {
            produit = ligneCommande.getProduit();
            quantite = ligneCommande.getQuantite();
            result += "<tr> <td>"+ produit.getRefproduit()+"</td>"
                            +"<td>"+ produit.getLibelle()+"</td>"
                            +"<td>"+ produit.getPrix()+"</td>"
                            +"<td align=\"center\">"+ quantite+"</td>"
                            +"<td>"+ produit.getPrix()*quantite+"</td>"
                      +"</tr>";
        } 
        return result;
    }
    
}
