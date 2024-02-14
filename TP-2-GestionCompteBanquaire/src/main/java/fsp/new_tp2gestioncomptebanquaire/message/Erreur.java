/*
 * Erreur.java                                  07 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp.new_tp2gestioncomptebanquaire.message;

/**
 * @author François de Saint Palais
 */
public class Erreur implements Message {
    private String message;

    public Erreur(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
