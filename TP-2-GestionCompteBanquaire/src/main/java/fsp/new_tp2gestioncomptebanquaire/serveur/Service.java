/*
 * Service.java                                  07 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp.new_tp2gestioncomptebanquaire.serveur;

import fsp.new_tp2gestioncomptebanquaire.message.*;

import java.io.*;
import java.net.Socket;

/**
 * @author François de Saint Palais
 */
public class Service extends Thread {
    private Socket socket;

    private double monCompteBancaire;

    public Service(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        monCompteBancaire = 0;
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Message message;
            boolean fin = false;
            while (!fin) {
                message = (Message) in.readObject(); // lecture message
                System.out.println("RECU: " + message);// trace locale
                if (message.equals("FIN_CONNEXION")) fin = true;

                if (!fin) {
                    Message aRetouner = traitementRequete(message);
                    out.writeObject(aRetouner); // renvoi d'un reçu
                }
            }
            socket.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }

    }

    private Message traitementRequete(Message message) {
        Message reponse;
        if (message instanceof Operation) {
//            Operation operation = (Operation) message;
//            System.out.println(operation.getCode());
            switch (((Operation) message).getCode()) {
                case CREDITER:
                    monCompteBancaire += ((Operation) message).getMontant();
                    reponse = new Retour(monCompteBancaire);
                    break;

                case DEBITER:
                    if (((Operation) message).getMontant() > monCompteBancaire) {
                        reponse = new Erreur("Fond insuffisant");
                    } else {
                        monCompteBancaire -= ((Operation) message).getMontant();
                        reponse = new Retour(monCompteBancaire);
                    }
                    break;

                case SOLDE:
                    reponse = new Retour(monCompteBancaire);
                    break;
                default:
                    reponse = new Erreur("Code inconnu");
            }
        } else {
            reponse = new Erreur("Message inconnu");
        }
        return reponse;
    }
}
