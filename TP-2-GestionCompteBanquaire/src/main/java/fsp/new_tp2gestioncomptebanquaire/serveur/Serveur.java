/*
 * Serveur.java                                  07 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp.new_tp2gestioncomptebanquaire.serveur;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author François de Saint Palais
 */
public class Serveur {
    public static void main(String[] args) {
        int port = 432;
        try {
            ServerSocket ecoute = new ServerSocket(port);
            System.out.println("Serveur lancé !\nEcoute sur le port " + port + " ...");
            while (true) {
                // Acceptation connexion, création d'un socket de communication
                Socket service = ecoute.accept();
                System.out.println("Connexion acceptée !");
                // Communication avec le client
                new Service(service).start();
            }
        } catch (Exception e) {
            System.out.println(e);
        } // traitement d'erreur
    }
}
