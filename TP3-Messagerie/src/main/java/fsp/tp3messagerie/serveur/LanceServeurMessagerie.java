/*
 * LanceServeurMessagerie.java                                  07 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp.tp3messagerie.serveur;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author François de Saint Palais
 */
public class LanceServeurMessagerie {
    public static void main(String[] args) {
        int port = 1099;

        try {
            LocateRegistry.createRegistry(port);
            ServeurMessagerie serveur = new ServeurMessagerie();

            Naming.bind("serveur", serveur);

            System.out.println("Serveur lancé");
            System.out.println("Url serveur = rmi://"+ InetAddress.getLocalHost()
                            .getHostAddress()+":"+port+"/serveur");

        } catch (MalformedURLException | RemoteException | UnknownHostException e) {
            e.printStackTrace();
            System.err.println("Serveur non enregistré sous RMI");
        } catch (AlreadyBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
