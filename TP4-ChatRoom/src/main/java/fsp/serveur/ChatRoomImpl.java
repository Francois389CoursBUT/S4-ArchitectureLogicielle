package fsp.serveur;

import fsp.Message;
import fsp.interface_client.ChatUser;
import fsp.exception.PseudoDejaUtiliseException;
import fsp.exception.PseudoInconnuException;
import fsp.interface_serveur.ChatRoom;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatRoomImpl extends UnicastRemoteObject
        implements ChatRoom {

    private ArrayList<Message> messages;

    private HashMap<String, ChatUser> utilisateurs;

    public ChatRoomImpl() throws RemoteException {
        messages = new ArrayList<>();
        utilisateurs = new HashMap<>();
    }

    @Override
    public void connecter(ChatUser utilisateur, String pseudo) throws RemoteException, PseudoDejaUtiliseException {
        if (utilisateurs.containsKey(pseudo)) {
            throw new PseudoDejaUtiliseException();
        }
        utilisateurs.put(pseudo, utilisateur);

        for (ChatUser utilisateurExistant : utilisateurs.values()) {
            utilisateurExistant.afficher(pseudo + " vient de se connecter");
        }
    }

    @Override
    public void deconnecter(String pseudo) throws RemoteException, PseudoInconnuException {
        if (!utilisateurs.containsKey(pseudo)) {
            throw new PseudoInconnuException();
        }
        utilisateurs.remove(pseudo);

        for (ChatUser utilisateurExistant : utilisateurs.values()) {
            utilisateurExistant.afficher(pseudo + " vient de se déconnecter");
        }
    }

    @Override
    public void poster(String message, String pseudo) throws RemoteException, PseudoInconnuException {
        Message m = new Message(message, pseudo);
        messages.add(m);

        for (ChatUser utilisateurExistant : utilisateurs.values()) {
            utilisateurExistant.afficher(m.toString());
        }
    }
}
