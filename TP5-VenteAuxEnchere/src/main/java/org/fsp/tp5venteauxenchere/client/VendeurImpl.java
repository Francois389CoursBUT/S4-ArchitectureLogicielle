package org.fsp.tp5venteauxenchere.client;

import org.fsp.tp5venteauxenchere.interface_client.Vendeur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class VendeurImpl extends UnicastRemoteObject implements Vendeur {

    private IHMVendeur ihm;

    protected VendeurImpl() throws RemoteException {
    }
}
