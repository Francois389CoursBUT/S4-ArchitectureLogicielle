package org.fsp.tp5venteauxenchere.interface_client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Vendeur extends Remote {

    public void communiquerMeilleureOffre(int offre, String pseudo) throws RemoteException;

}
