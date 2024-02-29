package org.fsp.tp5venteauxenchere.serveur;

import org.fsp.tp5venteauxenchere.interface_client.Participant;
import org.fsp.tp5venteauxenchere.interface_client.Vendeur;
import org.fsp.tp5venteauxenchere.interface_serveur.SalleDeVente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class SalleDeVenteImpl extends UnicastRemoteObject implements SalleDeVente {

    private Vendeur vendeur;
    private ArrayList<Participant> participants;

    protected SalleDeVenteImpl() throws RemoteException {

    }
}
