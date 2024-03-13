package org.fsp.tp5venteauxenchere.client;

import org.fsp.tp5venteauxenchere.interface_client.Participant;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ParticipantImpl extends UnicastRemoteObject implements Participant {

    private IHMParticipant ihm;

    public ParticipantImpl(IHMParticipant participant) throws RemoteException {
        ihm = participant;
    }


    @Override
    public void signalerOuvertureEnchere() throws RemoteException {
        ihm.signaleOuvertureEnchere();

    }

    @Override
    public void signalerFermetureEnchere(String message) throws RemoteException {
        ihm.signaleFermetureEnchere(message);
    }

    @Override
    public void communiquerProduit(String produit) throws RemoteException {
        ihm.afficherProduit(produit);
    }

    @Override
    public void communiquerMeilleureOffre(int offre) throws RemoteException {
        ihm.afficherMeilleureOffre(offre);
    }
}
