/*
 * Retour.java                                  07 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp.new_tp2gestioncomptebanquaire.message;

/**
 * @author François de Saint Palais
 */
public class Retour implements Message {
    private double solde;

    public Retour(double solde) {
        this.solde = solde;
    }

    public double getSolde() {
        return solde;
    }
}
