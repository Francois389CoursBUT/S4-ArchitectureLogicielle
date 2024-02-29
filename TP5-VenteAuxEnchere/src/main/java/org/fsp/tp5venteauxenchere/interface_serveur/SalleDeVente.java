package org.fsp.tp5venteauxenchere.interface_serveur;


import org.fsp.tp5venteauxenchere.exception.ParticipantDejaException;
import org.fsp.tp5venteauxenchere.exception.VendeurDejaPresentException;
import org.fsp.tp5venteauxenchere.interface_client.Participant;
import org.fsp.tp5venteauxenchere.interface_client.Vendeur;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SalleDeVente extends Remote {

    public void connecterVendeur() throws RemoteException, VendeurDejaPresentException;

    public void connecterParticipant(String pseudo) throws RemoteException, ParticipantDejaException;

    public void ouvrirEnchere(String produit, int prixOuverture) throws RemoteException;

    public void fermerEnchere() throws RemoteException;

    public void faireOffre(int prix);

    public void seRetirerDeEnchere();
}
