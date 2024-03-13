/*
 * VendeurController.java                                  28 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.tp5venteauxenchere.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.fsp.tp5venteauxenchere.exception.EnchereDejaOuverteException;
import org.fsp.tp5venteauxenchere.interface_serveur.SalleDeVente;

import java.rmi.RemoteException;

/**
 * @author François de Saint Palais
 */
public class IHMVendeur {
    public TextField urlServeur;
    public TextField textProduit;
    public TextField prixOuverture;
    public TextField meilleurOffre;
    public TextField proprietaireDeLOffre;
    public Label msgConnexion;

    private SalleDeVente salleDeVente;

    @FXML
    public void initialize() {
        // TODO
        textProduit.setDisable(true);
        prixOuverture.setDisable(true);
        meilleurOffre.setDisable(true);
        proprietaireDeLOffre.setDisable(true);
    }

    public void handleSeConnecter(ActionEvent actionEvent) {
        if (urlServeur.getText().isEmpty()) {
            showErreur("URL Serveur vide", "Veuillez renseigner l'URL du serveur");
        } else {
            try {
                salleDeVente = (SalleDeVente) java.rmi.Naming.lookup(urlServeur.getText());
                salleDeVente.enregistrerVendeur(new VendeurImpl(this));

                textProduit.setDisable(false);
                meilleurOffre.setDisable(false);
                proprietaireDeLOffre.setDisable(false);
                prixOuverture.setDisable(false);

                urlServeur.setDisable(true);
                msgConnexion.setText("Connecté");
            } catch (Exception e) {
                showErreur("Erreur lors de la connexion", e.getMessage());
            }
        }
    }

    public void handleNouvelleEnchere(ActionEvent actionEvent) throws RemoteException {
        try {
            if (textProduit.getText().isEmpty()) {
                showErreur("Produit vide", "Veuillez renseigner le produit à vendre");
            } else if (prixOuverture.getText().isEmpty()) {
                showErreur("Prix d'ouverture vide", "Veuillez renseigner le prix d'ouverture");
            } else if (Integer.parseInt(prixOuverture.getText()) <= 0) {
                showErreur("Prix d'ouverture négatif", "Veuillez renseigner un prix d'ouverture positif");
            } else {
                salleDeVente.ouvrirEnchere(textProduit.getText(), Integer.parseInt(prixOuverture.getText()));
                prixOuverture.setDisable(true);
                textProduit.setDisable(true);

                msgConnexion.setText("Enchère ouverte");
            }
        } catch (EnchereDejaOuverteException e) {
            showErreur("Enchère déjà ouverte.", e.getMessage());
        } catch (NumberFormatException e) {
            showErreur("Prix d'ouverture invalide", "Veuillez renseigner un prix d'ouverture valide");
        }
    }

    private static void showErreur(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void handleFermerEnchere(ActionEvent actionEvent) {
    }

    public void afficherMeilleureOffre(int offre, String pseudo) {
        meilleurOffre.setText(String.valueOf(offre));
        proprietaireDeLOffre.setText(pseudo);
    }
}
