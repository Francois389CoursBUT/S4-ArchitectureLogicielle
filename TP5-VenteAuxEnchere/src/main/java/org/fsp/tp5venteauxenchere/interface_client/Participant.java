package org.fsp.tp5venteauxenchere.interface_client;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Participant extends Remote {


    public void signalerOuvertureEnchere() throws RemoteException;

    /**
     * Signaler la fermeture de l'enchère en cours avec un message indiquant le résultat
     * de la vente.
     */
    public void signalerFermetureEnchere(String message) throws RemoteException;

    public void communiquerProduit(String produit) throws RemoteException;

    public void communiquerMeilleureOffre(int offre) throws RemoteException;

}
