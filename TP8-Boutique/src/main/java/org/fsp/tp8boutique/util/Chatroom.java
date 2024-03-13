package org.fsp.tp8boutique.util;

import java.util.ArrayList;
import java.util.List;


public class Chatroom {
 
  private ArrayList<String> pseudosConnectes = new ArrayList();

  private ArrayList<String> messages = new ArrayList();

  // Chatroom est un objet singleton

  private static Chatroom instance = new Chatroom();   
  private Chatroom() {}
  public static Chatroom getInstance() {
      return instance;
  }

  // Opérations exécutées par les utilisateurs 

  public List<String> getMessages(){
      return this.messages;
  }

  public void connecter(String pseudo) throws PseudoException {
    if (pseudo.equals("")) throw 
            new PseudoException("Pseudo vide !");
    else if(pseudosConnectes.contains(pseudo)) 
        throw new PseudoException("Pseudo déjà connecté!");
    else pseudosConnectes.add(pseudo);
  }
  public void deconnecter(String pseudo){
    pseudosConnectes.remove(pseudo);
    System.out.println("=== > Déconnexion de "+pseudo);
  }
  public void postMessage(String pseudo, String message){
      String fullMessage = pseudo + " >> " + message;
      System.out.println(fullMessage);
      messages.add(0,fullMessage);
  }
}