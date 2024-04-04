package org.fsp.tp8boutique.donnees;

import java.util.ArrayList;
import java.util.List;

public class Client  {

    private String identifiant;   
    private String motDePasse;
    private Panier panier;
    private List<Commande> commandes;

    public Client(String identifiant, String motDePasse) {
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        commandes = new ArrayList<>();
        panier = new Panier(this);
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    
    public Panier getPanier() {
        return panier;
    }
    
    /** Ajoute à la liste des commandes la commande passée en paramètre. 
     */
    public void ajouterCommande(Commande commande) {
         commandes.add(commande);
     }

    /** Renvoie la commande dont le numéro est passé en paramètre.
     */
    public Commande getCommande(int numero) {
        for(Commande c: commandes) {
            if(c.getNumeroCommande() == numero) return c;
        }
        return null;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    /**
     * Renvoie un texte correspondant aux items d'une liste déroulante Html avec la balise select
     * Texte de la forme :
     * "<option value=[Numéro de commande 1]>
     *        [Numero de commande 1] - [Total] : [montant de la commande]
     *  </option>
     *  <option value=[Référence du produit 2]>
     *        [Numero de commande 2] - [Total] : [montant de la commande]
     *  </option>
     *  ... "
     */
    public String balisesCommandesEnregistrees() {
        String result = "";
        for(Commande commande: commandes) {
            result +="<option value = "+ commande.getNumeroCommande()+">"
                    + "Commande n° : "+ commande.getNumeroCommande()+" - Total : "+commande.getTotal()
                    +"</option>\n";
        }
        return result;                              
    }

}
