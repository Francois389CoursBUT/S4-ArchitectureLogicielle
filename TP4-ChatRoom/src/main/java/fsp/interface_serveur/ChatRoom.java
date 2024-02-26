package fsp.interface_serveur;


import fsp.interface_client.ChatUser;
import fsp.exception.PseudoDejaUtiliseException;
import fsp.exception.PseudoInconnuException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatRoom extends Remote {

    /**
     * Enregistrer le chatuser passé en paramètre
     * comme utilisateur connecté au chatroom avec le pseudo passé en paramètre
     * puis informer tous les chatusers connecés de la connexion de pseudo.
     * Lève PseudoDejaUtiliseException si pseudo déjà utilisé
     * par un autre chatuser encore connecté.
     */
    public void connecter(ChatUser utilisateur, String pseudo) throws RemoteException, PseudoDejaUtiliseException;

    /**
     * Poster message sur le chatroom
     * et le communiquer à tous les chatusers connectés avec l'en-tête pseudo.
     * Lève PseudolnconnuException si pseudo ne correspond à aucun
     * chatuser connecté.
     */
    public void deconnecter(String pseudo) throws RemoteException, PseudoInconnuException;

    /**
     * Retirer le chatuser pseudo de l'ensemble des chatusers connectés
     * puis informer tous les chatusers connecés du départ de pseudo..
     * Lève PseudolnconnuException si pseudo ne correspond à aucun chatuser connecté.
     */
    public void poster(String message, String pseudo) throws RemoteException, PseudoInconnuException;
}
