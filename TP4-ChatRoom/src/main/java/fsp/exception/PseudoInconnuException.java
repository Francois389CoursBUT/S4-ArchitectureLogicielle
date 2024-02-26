/*
 * PseudoInconnuException.java                                  26 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp.exception;

/**
 * @author François de Saint Palais
 */
public class PseudoInconnuException extends Exception {

    public PseudoInconnuException() {
    }

    public PseudoInconnuException(String message) {
        super(message);
    }

    public PseudoInconnuException(String message, Throwable cause) {
        super(message, cause);
    }

    public PseudoInconnuException(Throwable cause) {
        super(cause);
    }

    public PseudoInconnuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
