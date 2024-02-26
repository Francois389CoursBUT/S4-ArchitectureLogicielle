package fsp.serveur;


import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class LanceServeur {
  public static void main(String[] args) {
      int port = 1099;
      try {
          LocateRegistry.createRegistry(port);
          Naming.bind("serveur", new ServeurImpl());
          System.out.println("Serveur enregistré sous RMI, prêt à recevoir des messages.");
          System.out.println("Url serveur = rmi://"+InetAddress.getLocalHost()
                            .getHostAddress()+":"+port+"/serveur");
                  
      }
      catch(Exception e) {
          System.out.println(e);
          System.out.println("Serveur non enregistré sous RMI");
      }
  }
}
