/*
 * PseudoDejaUtiliseException.java                                  26 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp.exception;

/**
 * @author François de Saint Palais
 */
public class PseudoDejaUtiliseException extends Exception {

        public PseudoDejaUtiliseException() {
        }

        public PseudoDejaUtiliseException(String message) {
            super(message);
        }

        public PseudoDejaUtiliseException(String message, Throwable cause) {
            super(message, cause);
        }

        public PseudoDejaUtiliseException(Throwable cause) {
            super(cause);
        }

        public PseudoDejaUtiliseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
}
