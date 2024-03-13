package org.fsp.tp5venteauxenchere.serveur;

import org.fsp.tp5venteauxenchere.exception.*;
import org.fsp.tp5venteauxenchere.interface_client.Participant;
import org.fsp.tp5venteauxenchere.interface_client.Vendeur;
import org.fsp.tp5venteauxenchere.interface_serveur.SalleDeVente;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class SalleDeVenteImpl extends UnicastRemoteObject implements SalleDeVente {

    private Vendeur vendeur;
    private HashMap<String, Participant> participants;

    private String produit;

    private int meilleureOffre; // initialisé au prix d'ouverture
    private String pseudoMeilleureOffre;

    private boolean enchereOuverte;

    protected SalleDeVenteImpl() throws RemoteException {
        super();
        participants = new HashMap<>();
        enchereOuverte = false;
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            SalleDeVente salleDeVente = new SalleDeVenteImpl();
            Naming.rebind("salle_de_vente", salleDeVente);
            System.out.println("Salle de vente lancée");
            //Print url serveur
            System.out.println("Serveur URL : " + "rmi://"
                    + InetAddress.getLocalHost().getHostAddress()
                    + ":1099/salle_de_vente");
        } catch (Exception e) {
            System.out.println("Erreur lors du lancement du serveur\n : " + e);
        }
    }


    @Override
    public void enregistrerVendeur(Vendeur vendeur) throws RemoteException, VendeurDejaConnecteException {
        if (this.vendeur != null) {
            throw new VendeurDejaConnecteException();
        }
        this.vendeur = vendeur;
        System.out.println("Vendeur enregistré");
    }

    @Override
    public void ouvrirEnchere(String produit, int prix) throws RemoteException, EnchereDejaOuverteException {
        if (enchereOuverte) {
            throw new EnchereDejaOuverteException();
        }

        this.produit = produit;
        meilleureOffre = prix;
        pseudoMeilleureOffre = null;
        enchereOuverte = true;

        System.out.println("Enchère ouverte : " + produit + " au prix de " + prix);

        for (Participant participant : participants.values()) {
            participant.signalerOuvertureEnchere();
            participant.communiquerProduit(produit);
            participant.communiquerMeilleureOffre(meilleureOffre);
        }
    }

    @Override
    public void fermerEnchere() throws RemoteException, AucuneEnchereOuverteException {

    }

    @Override
    public void retirerVendeur() throws RemoteException, NonEnregistreException {

    }

    @Override
    public void enregistrerParticipant(Participant participant, String pseudo) throws RemoteException, AucuneEnchereOuverteException, ParticipantDejaEnregistreException {
        if (participants.containsKey(pseudo)) {
            throw new ParticipantDejaEnregistreException();
        }
        participants.put(pseudo, participant);

        if (enchereOuverte) {
            participant.signalerOuvertureEnchere();
            participant.communiquerProduit(produit);
            participant.communiquerMeilleureOffre(meilleureOffre);
        }

        System.out.println("Participant " + pseudo + " enregistré");
    }

    @Override
    public void retirer(String pseudo) throws RemoteException, NonEnregistreException, RetraitNonAutoriseException {

    }

    @Override
    public void faireOffre(String pseudo, int offre) throws RemoteException, AucuneEnchereOuverteException {
        if (!enchereOuverte) {
            throw new AucuneEnchereOuverteException();
        }

        if (offre > meilleureOffre) {
            meilleureOffre = offre;
            pseudoMeilleureOffre = pseudo;

            for (Participant participant : participants.values()) {
                participant.communiquerMeilleureOffre(meilleureOffre);
            }
            vendeur.communiquerMeilleureOffre(meilleureOffre, pseudo);
        }
    }
}
