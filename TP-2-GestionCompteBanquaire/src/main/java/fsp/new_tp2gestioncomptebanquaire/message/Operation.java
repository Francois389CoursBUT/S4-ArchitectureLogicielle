/*
 * Operation.java                                  07 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp.new_tp2gestioncomptebanquaire.message;

/**
 * @author François de Saint Palais
 */
public class Operation implements Message {
    private CodeOperation code;
    private double montant;

    public Operation(CodeOperation code, double montant) {
        this.code = code;
        this.montant = montant;
    }

    public Operation(CodeOperation code) {
        this.code = code;
        this.montant = montant;
    }

    public CodeOperation getCode() {
        return code;
    }

    public double getMontant() {
        return montant;
    }
}
