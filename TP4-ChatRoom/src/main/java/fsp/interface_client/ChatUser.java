/*
 * ChatUser.java                                  26 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp.interface_client;

import fsp.exception.PseudoDejaUtiliseException;
import fsp.exception.PseudoInconnuException;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author François de Saint Palais
 */
public interface ChatUser extends Remote {

    /**
     * Afficher un message expédié par le chatroom.
     */
    public void afficher(String message) throws RemoteException;

}
