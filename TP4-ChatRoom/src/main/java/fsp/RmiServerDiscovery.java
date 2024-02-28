
package fsp;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RmiServerDiscovery {

    public static Set<String> discoverServers(String network) throws Exception {
        Set<String> serverUrls = new HashSet<>();

        // Parcourir tous les hôtes du réseau
        for (int i = 12; i <= 255 && serverUrls.isEmpty() ; i++) {
            String host = network + "." + i;

            System.out.println("Recherche de serveurs RMI sur " + host + " ... ");

            try {
                // Rechercher le registre RMI sur l'hôte
                Registry registry = LocateRegistry.getRegistry(host);

                System.out.println(registry);

                // Obtenir la liste des objets distants enregistrés auprès du registre
                String[] names = registry.list();

                System.out.println("Noms trouvés : " + Arrays.toString(names));

                // Ajouter les URL des objets distants à la liste des serveurs
                for (String name : names) {
                    serverUrls.add("rmi://" + host + "/" + name);
                }

            } catch (Exception e) {
                // Ignorer les exceptions levées lors de la recherche d'hôtes inexistants ou inaccessibles
                System.out.println("Pas de serveur");
            }
        }

        return serverUrls;
    }

    public static void main(String[] args) throws Exception {
        String network = "10.108.0"; // Adresse IP du réseau à rechercher
        System.out.println("Recherche des serveurs RMI sur le réseau " + network + " ...");
        Set<String> serverUrls = discoverServers(network);

        System.out.println("Serveurs RMI disponibles :");
        for (String url : serverUrls) {
            System.out.println(url);
        }
    }
}
