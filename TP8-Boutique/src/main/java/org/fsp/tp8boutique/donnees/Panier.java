package org.fsp.tp8boutique.donnees;

import java.util.ArrayList;

public class Panier {

    private Client client;
    private ArrayList<Produit> produits;
    private ArrayList<Integer> quantites;
    private float total;

    public Panier(Client client) {
        this.client = client;
        produits = new ArrayList();
        quantites = new ArrayList();
        total = 0;
    }

    public void vider() {
        produits = new ArrayList();
        quantites = new ArrayList();
        total = 0;
    }

    /**
     * Tente d'ajouter au panier le produit avec la quantité spécifiés en paramètre. 
     * Lève QuantiteException si la quantité spécifiée dépasse celle disponible en stock. 
     * Sinon si le prduit est déjà présent dans ans le panier, sa quantité est incrémentée 
     * de la valeur spécifiée. Sinon, le produit avec la quantité spécifiés sont ajoutés et
     * le total du panier est mis à jour.
     */
    public void ajouterProduit(Produit produit, int quantite) throws QuantiteException {
        if(produit.getQuantite() < quantite) 
            throw new QuantiteException("Il ne reste plus que : "+produit.getQuantite()
                    + " unités du produit "+produit.getLibelle());
        boolean produitPresent = false;
        for(int i=0; i<produits.size() && !produitPresent; i++) {
            if(produits.get(i).getRefproduit().equals(produit.getRefproduit())) {
                quantites.set(i, quantites.get(i) + quantite);
                produitPresent = true;
            }
        }
        if (!produitPresent) {
            produits.add(produit);
            quantites.add(quantite);
        }
        // Mettre à jour le total du panier
            total += produit.getPrix()*quantite;
    }
    
    /** Supprime du panier le produit spécifié en paramètre et met à jour le total du panier. 
     * Ne fait rien si le produit est absent du panier.
     */
    public void supprimerProduit(String refProduit) {
        for(int i=0; i<produits.size();i++) {
            if(produits.get(i).getRefproduit().equals(refProduit)) {
                // Mettre à jour le total du panier puis supprimer du panier le produit et sa quantité
                total -= produits.get(i).getPrix()*quantites.get(i);
                produits.remove(i);
                quantites.remove(i);
                break;
            }
        }        
    }        

    /** 
     * Crée une commande à partir du panier et met le stock à jour en supprimant les quantités
     * commandées pour chaque produit de la commande.
     * Lève QuantiteException si un produit n'est plus disponible pour la quantité commandée.
     */
    public Commande creerCommande() throws QuantiteException {
        Commande commande = new Commande();
        for(int i=0; i<produits.size(); i++) {
            if(quantites.get(i) > produits.get(i).getQuantite()) // Quantité commandée > quantité en stock
                throw new QuantiteException("La quantité du produit "+produits.get(i).getRefproduit()
                            +" n'est plus disponible !");
            commande.ajouterLigneCommande(produits.get(i), quantites.get(i));
            // Mise à jour des produits du stock
            produits.get(i).setQuantite((int)(produits.get(i).getQuantite()- quantites.get(i)));
        }
        commande.setClient(client);
        client.ajouterCommande(commande);
        return commande;
    }

    public float getTotal() {
        return total;
    }

    
    public boolean estVide() {
        return produits.isEmpty();
    }

    /**
   * Renvoie un texte correspondant aux items d'un tableau Html avec la balise tbody
   * Texte de la forme :
   * "<td> [Libelle de produit 1] </td>
   *  <td> [Libelle de produit 2] </td>
   *  ... "
   */
  public String balisesTdItemsPanier() {
    String result ="";
    for(int i=0; i<produits.size() ; i++) {
        result += "<tr> <td>"+ produits.get(i).getRefproduit()+"</td>"
                        +"<td>"+ produits.get(i).getLibelle()+"</td>"
                        +"<td>"+ produits.get(i).getPrix()+"</td>"
                        +"<td align=\"center\">"+ quantites.get(i)+"</td>"
                        +"<td>"+ produits.get(i).getPrix()*quantites.get(i)+"</td>"
                  +"</tr>";
    } 
    return result;
  }

}
