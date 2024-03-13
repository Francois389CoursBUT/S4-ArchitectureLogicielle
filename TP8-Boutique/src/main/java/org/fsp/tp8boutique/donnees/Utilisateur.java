package org.fsp.tp8boutique.donnees;

public abstract class Utilisateur {
    private String identifiant;   
    private String motDePasse;

    public Utilisateur(String identifiant, String motDePasse) {
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
