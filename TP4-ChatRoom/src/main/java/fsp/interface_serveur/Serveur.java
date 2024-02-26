package fsp.interface_serveur;


import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Serveur extends Remote {
    public void postMessage(String message, String pseudoExpediteur) 
            throws RemoteException;
    public List<String> getMessages() throws RemoteException;
}
