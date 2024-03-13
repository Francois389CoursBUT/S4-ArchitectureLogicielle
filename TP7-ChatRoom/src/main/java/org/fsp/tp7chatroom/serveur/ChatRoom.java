/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fsp.tp7chatroom.serveur;

import org.fsp.tp7chatroom.exception.PseudoDejaExistantException;
import org.fsp.tp7chatroom.exception.PseudoInexistant;
import org.fsp.tp7chatroom.exception.PseudoInvalideException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author François de Saint Palais
 */
public class ChatRoom {

    private ArrayList<String> users;
    private HashMap<String, String> messages;

    private static ChatRoom instance;

    private ChatRoom() {
        users = new ArrayList<>();
        messages = new HashMap<>();
        users.add("");
    }

    public static ChatRoom getInstance() {
        if (instance == null) {
            instance = new ChatRoom();
        }
        return instance;
    }

    public void connecter(String pseudo) throws PseudoDejaExistantException, PseudoInvalideException {
        if (users.contains(pseudo)) {
            throw new PseudoDejaExistantException();
        } else if (pseudo.equals("")) {
            throw new PseudoInvalideException();
        }
        users.add(pseudo);
        try {

            postMessage("", pseudo + " a rejoint");
        } catch (Exception e) {}
    }

    public void deconnecter(String pseudo) throws PseudoInexistant {
        if (!users.contains(pseudo)) {
            throw new PseudoInexistant();
        }
        users.remove(pseudo);
        System.out.println("===> " + pseudo + " a quitté");

    }

    public void postMessage(String pseudo, String message) throws PseudoInexistant {
        if (!users.contains(pseudo)) {
            throw new PseudoInexistant();
        }
        messages.put(pseudo, message);
    }

    public List<String> getMessages() {
        ArrayList<String> resultat = new ArrayList<>();
        for (Map.Entry<String, String> message : messages.entrySet()) {
            resultat.add(message.getKey() + ">>>" + message.getValue());
        }
        return resultat;
    }

}
