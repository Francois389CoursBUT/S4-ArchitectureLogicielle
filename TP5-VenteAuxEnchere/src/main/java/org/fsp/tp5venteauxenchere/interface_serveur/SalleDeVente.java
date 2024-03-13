package org.fsp.tp5venteauxenchere.interface_serveur;


import org.fsp.tp5venteauxenchere.exception.*;
import org.fsp.tp5venteauxenchere.interface_client.Participant;
import org.fsp.tp5venteauxenchere.interface_client.Vendeur;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SalleDeVente extends Remote {

    /**
     * Enregistrer le vendeur.
     */
    public void enregistrerVendeur(Vendeur vendeur) throws RemoteException, VendeurDejaConnecteException;

    /**
     * Ouvre une nouvelle enchère si aucune autre enchère n'est déjà ouverte.
     * Signale cette ouverure et communique le produit et son prix d'ouverture
     * à chaque participant.
     *
     * @throws EnchereDejaOuverteException si une enchère est en cours.
     */
    public void ouvrirEnchere(String produit, int prix) throws RemoteException, EnchereDejaOuverteException;

    /**
     * Si enchère ouverte, ferme l'enchère en cours et en informe tous les participants.
     * Lève AucuneEnchereOuverteException si aucune enchère ouverte.
     */
    public void fermerEnchere() throws RemoteException, AucuneEnchereOuverteException;

    /**
     * Retirer et fermer l'enchère si celle-ci est ouverte.
     * Lève NonEnregistreException si vendeur non enregistré.
     */
    public void retirerVendeur() throws RemoteException, NonEnregistreException;


    /**
     * Enregistre un nouveau participant s'il n'est pas déjà enregistré puis envoie au
     * nouveau participant la description du produit et la meilleure offre de prix..
     *
     * @throws ParticipantDejaEnregistreException si pseudo déjà enregistré.
     * @throws AucuneEnchereOuverteException      si l'enchère n'est pas ouverte.
     */
    public void enregistrerParticipant(Participant participant, String pseudo)
            throws RemoteException, AucuneEnchereOuverteException, ParticipantDejaEnregistreException;

    /**
     * Retire un participant.
     *
     * @throws NonEnregistreException     si pseudo non enregistré.
     * @throws RetraitNonAutoiseException si pseudo est celui qui a fait la meilleure offre.
     */
    public void retirer(String pseudo) throws RemoteException, NonEnregistreException, RetraitNonAutoriseException;

    /**
     * Permet à un participant qui indique son pseudo de faire une offre de prix.
     * L'offre n'est pas retenue si pseudo n'est pas enregistré ou l'offre n'est pas supérieure
     * à la meilleure offre courante.
     * Si l'offre est retenue, le vendeur et chacun des participants en sont informés.
     */
    public void faireOffre(String pseudo, int offre) throws RemoteException, OffreInsuffisanteException, AucuneEnchereOuverteException;
}
