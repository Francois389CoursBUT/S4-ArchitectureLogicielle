/*
 * ServeurMessagerie.java                                  07 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp.tp3messagerie.serveur;

import fsp.tp3messagerie.Messagerie;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * @author François de Saint Palais
 */
public class ServeurMessagerie extends UnicastRemoteObject implements Messagerie {

    ArrayList<String> messages;

    protected ServeurMessagerie() throws RemoteException {
        super();
        messages = new ArrayList<>();
    }

    @Override
    public void posterMessage(String message, String pseudi) throws RemoteException {
        messages.add(pseudi + " >> " + message);
    }

    @Override
    public ArrayList<String> getMessage() throws RemoteException {
        return messages;
    }
}
