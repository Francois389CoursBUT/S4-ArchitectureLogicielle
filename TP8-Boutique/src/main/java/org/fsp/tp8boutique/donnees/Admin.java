package org.fsp.tp8boutique.donnees;


public class Admin  {

    private String identifiant;   
    private String motDePasse;
    
    public Admin(String identifiant, String motDePasse) {
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    
}
