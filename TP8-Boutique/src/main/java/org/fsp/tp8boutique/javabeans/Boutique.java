/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fsp.tp8boutique.javabeans;

import org.fsp.tp8boutique.donnees.Admin;
import org.fsp.tp8boutique.donnees.Client;
import org.fsp.tp8boutique.donnees.IdentifiantsException;
import org.fsp.tp8boutique.donnees.InfosProduitException;
import org.fsp.tp8boutique.donnees.Produit;

import java.util.HashMap;

/**
 *
 * @author lbath
 */

public class Boutique {

    private HashMap<String, Produit> stock = new HashMap();
    private HashMap<String, Client> clients = new HashMap();
    private Admin admin;
    
    public Boutique() {
        stock.put("R001", new Produit("R001","Smart TV",1500, 20));
        stock.put("R002", new Produit("R002","Smartphone",650, 15));
        stock.put("R003", new Produit("R003","Chaine HIFI",450, 30));
        clients.put("toto", new Client("toto","123"));
        clients.put("kiki", new Client("kiki","456"));
        admin = new Admin("admin","007");
    }
    
    /**
     * Lève IdentifiantsException si l'identifiant et le mot de passe Admin sont incorrects.
     */
    public void verifierIdentifiantsAdmin(String identifiant, String motDePasse)
            throws IdentifiantsException {
        if (identifiant.isEmpty() || motDePasse.isEmpty())
            throw new IdentifiantsException("Identifiant ou mot de passe vides(s) !");
        else if(!admin.getIdentifiant().equals(identifiant)
                || ! admin.getMotDePasse().equals(motDePasse)) 
            throw new IdentifiantsException("Identifiant ou mot de passe incorrect(s) !");
    }
    
    /**
     * Vérifie si les identifiants passés en paramètre correspondent à ceux d'un client
     * et renvoie le client concerné en cas de succès.
     * Lève IdentifiantsException sinon.
     */
    public Client getClient(String identifiant, String motDePasse) 
            throws IdentifiantsException {
        if (identifiant==null || identifiant.isEmpty())
            throw new IdentifiantsException("Identifiant ou mot de passe vides(s) !");
        for(Client client: clients.values()) {
            if(client.getIdentifiant().equals(identifiant) 
                    && client.getMotDePasse().equals(motDePasse))
                return client;
        }
        throw new IdentifiantsException("Identifiant ou mot de passe incorrects !");
    }
    
    /** 
     * Vérifie si un identifiant est disponible (différent de "admin" et non déjà 
     * attribué à un client enregistré). 
     */
    private boolean identifiantDisponible(String identifiant) {
        if(identifiant.equals("admin")) return false;
        for(Client client: clients.values()) {
            if(client.getIdentifiant().equals(identifiant)) return false;
        }
        return true;
    }
    
    /**
     * Enregistre un nouveau client (dans clients) après avoir vérifié que l'identifiant et le
     * mot de passe sont non vides et disponibles. Lève IdentifiantsException sinon.
     */
    public Client enregistrerClient(String identifiant, String motDePasse) 
                throws IdentifiantsException {
        if (identifiant==null || identifiant.isEmpty())
            throw new IdentifiantsException("Pseudo ou mot de passe non saisi(s) !");
        else if (!identifiantDisponible(identifiant))
            throw new IdentifiantsException("L'identifiant "+identifiant+ " n'est pas disponible !");
        else {
            Client client = new Client(identifiant, motDePasse);
            clients.put(identifiant, client);
            return client;
        }
    }
    
    public Produit getProduit(String refProduit)  {
        return stock.get(refProduit);
    }
  
    /**
     * Renvoie un texte correspondant aux items d'une liste déroulante Html avec la balise select
     * Texte de la forme :
     * "<option value=[Référence du produit 1]>
     *        [Référence produit 1] [Libellé du produit 1] - [Unités en stock] : [nombre d'unités]
     *  </option>
     *  <option value=[Référence du produit 2]>
     *        [Référence produit 2] [Libellé du produit 2] - [Unités en stock] : [nombre d'unités]
     *  </option>
     *  ... "
     */
    public String balisesOptionsInfosProduits() {
        String result = "";
        for(Produit produit: stock.values()) {
            if(produit.getQuantite()!=0)
                result +="<option value = "+ produit.getRefproduit()+">"
                    + produit.getRefproduit()+" "+produit.getLibelle()+" - Unités en stock : "+produit.getQuantite()
                    +"</option>\n";
        }
        return result;                              
    }
    
    /**
     * Renvoie un texte correspondant aux items d'une liste déroulante Html avec la balise select
     * Texte de la forme :
     * "<option value=[Référence du produit 1]>
     *        [Référence du produit 1]
     *  </option>
     *  <option value=[Référence du produit 2]>
     *        [Libellé du produit 2]
     *  </option>
     *  ... "
     */
    public String balisesOptionsRefsProduits() {
        String result = "";
        for(Produit produit: stock.values()) {
            result +="<option value = "+ produit.getRefproduit()+">"
                    + produit.getRefproduit()
                    +"</option>\n";
        }
        return result;                              
    }
    
    /** Modifie le produit dont la référence est passée en patramètre avec les infos indiquées.
     *  Ne fait rien si le produit n'existe pas.
     */
    public void modifierProduit(String refProduit, String libelle, float prix, int quantite) 
                throws InfosProduitException {
        if(libelle.isEmpty() ) throw new InfosProduitException("Libellé non saisi !");
        if(prix <= 0) throw new InfosProduitException("Le prix doit être un réel > 0 !");
        if(quantite <= 0) throw new InfosProduitException("La quantité doit être un entier > 0 !");
        Produit p = stock.get(refProduit);
        p.setLibelle(libelle);p.setPrix(prix);p.setQuantite(quantite);
    }
    
    /** Ajoute au stock le produit avec les infos indiquées. Lève InfosProduitException si infos
     * incorrectes : référence ou libelle vides, prix ou quantités non positifs.
     */
    public void ajouterProduit(String refProduit, String libelle, float prix, int quantite) 
                throws InfosProduitException {
        if(refProduit.isEmpty() ) throw new InfosProduitException("Référence non saisie !");
        if(libelle.isEmpty() ) throw new InfosProduitException("Libellé non saisi !");
        if(prix <= 0) throw new InfosProduitException("Le prix doit être un réel > 0 !");
        if(quantite <= 0) throw new InfosProduitException("La quantité doit être un entier > 0 !");
        if(stock.containsKey(refProduit)) 
            throw new InfosProduitException("La référence "+refProduit+" est déjà utilisée !");
        stock.put(refProduit, new Produit(refProduit, libelle, prix, quantite));
    }
}
