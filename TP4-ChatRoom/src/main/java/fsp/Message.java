/*
 * Message.java                                  26 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp;

/**
 * @author François de Saint Palais
 */
public class Message {
    private String message;
    private String pseudo;

    public Message(String message, String pseudo) {
        this.message = message;
        this.pseudo = pseudo;
    }

    public String getMessage() {
        return message;
    }

    public String getPseudo() {
        return pseudo;
    }

    @Override
    public String toString() {
        return pseudo + ">>>" + message ;
    }
}
