package fsp.serveur;

import fsp.interface_serveur.Serveur;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServeurImpl extends UnicastRemoteObject 
        implements Serveur {
    
  private ArrayList<String> messages;

  public ServeurImpl() throws RemoteException {
    messages = new ArrayList();
  }
  public void postMessage(String message, String pseudoExpediteur) 
              throws RemoteException {
      messages.add(pseudoExpediteur +" >> "+ message);
  }

  public ArrayList<String> getMessages() throws RemoteException{
    return messages;
  }
   
}
