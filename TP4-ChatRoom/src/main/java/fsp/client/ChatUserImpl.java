/*
 * ChatUserImpl.java                                  26 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp.client;

import fsp.interface_client.ChatUser;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author François de Saint Palais
 */
public class ChatUserImpl extends UnicastRemoteObject implements ChatUser {

    private final IHMChatUser ihmChatUser;

    @Override
    public void afficher(String message) throws RemoteException {
        ihmChatUser.afficher(message);
    }

    public ChatUserImpl(IHMChatUser ihmChatUser) throws RemoteException {
        super();
        this.ihmChatUser = ihmChatUser;
    }
}
