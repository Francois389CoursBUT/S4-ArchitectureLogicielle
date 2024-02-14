/*
 * Messagerie.java                                  07 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp.tp3messagerie;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author François de Saint Palais
 */
public interface Messagerie extends Remote {
    public void posterMessage(String message, String pseudo) throws RemoteException;
    public ArrayList<String> getMessage() throws RemoteException;
}
