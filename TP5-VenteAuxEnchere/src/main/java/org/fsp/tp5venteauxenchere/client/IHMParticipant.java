/*
 * IHMParticipant.java                                  28 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.tp5venteauxenchere.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.fsp.tp5venteauxenchere.interface_serveur.SalleDeVente;

/**
 * @author François de Saint Palais
 */
public class IHMParticipant {
    public TextField urlServeur;
    public TextField textProduit;
    public TextField nouvelleOffre;
    public TextField meilleurOffre;
    public TextField textPseudo;
    public Label msgConnexion;

    private SalleDeVente salleDeVente;
    private String pseudo;

    @FXML
    public void initialize() {
        // TODO
        textProduit.setDisable(true);
        nouvelleOffre.setDisable(true);
        meilleurOffre.setDisable(true);
    }

    public void handleSEnregistrer(ActionEvent actionEvent) {
        if (urlServeur.getText().isEmpty()) {
            showErreur("URL Serveur vide", "Veuillez renseigner l'URL du serveur");
        } else if (textPseudo.getText().isEmpty()) {
            showErreur("Pseudo vide", "Veuillez renseigner votre pseudo");
        } else {
            pseudo = textPseudo.getText().trim();
            try {
                salleDeVente = (SalleDeVente) java.rmi.Naming.lookup(urlServeur.getText());
                salleDeVente.enregistrerParticipant(new ParticipantImpl(this), pseudo);

                textPseudo.setDisable(true);
                urlServeur.setDisable(true);
                msgConnexion.setText("Connecté");
            } catch (Exception e) {
                showErreur("Erreur lors de la connexion", e.getMessage());
            }
        }
    }

    public void handleFaireOffre(ActionEvent actionEvent) {
        try {
            int offre = Integer.parseInt(nouvelleOffre.getText());
            if (nouvelleOffre.getText().isEmpty()) {
                showErreur("Offre vide", "Veuillez renseigner une offre");
            } else if (offre <= 0) {
                showErreur("Offre négative", "Veuillez renseigner une offre positive");
            } else {
                salleDeVente.faireOffre(pseudo, offre);
            }
        } catch (NumberFormatException e) {
            showErreur("Erreur lors de l'offre", "Veuillez renseigner un nombre entier");
        } catch (Exception e) {
            showErreur("Erreur lors de l'offre", e.getMessage());
        }
    }

    private static void showErreur(String Offre_vide, String Veuillez_renseigner_une_offre) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(Offre_vide);
        alert.setContentText(Veuillez_renseigner_une_offre);
        alert.showAndWait();
    }

    public void handleSeRetirer(ActionEvent actionEvent) {
    }

    public void afficherMeilleureOffre(int offre) {
        // TODO
        meilleurOffre.setText(String.valueOf(offre));
    }

    public void afficherProduit(String produit) {
        // TODO
        textProduit.setText(produit);
    }

    public void signaleOuvertureEnchere() {
        // TODO
        textProduit.setDisable(false);
        nouvelleOffre.setDisable(false);
        meilleurOffre.setDisable(false);
    }

    public void signaleFermetureEnchere(String message) {
        // TODO
        textProduit.setDisable(true);
        nouvelleOffre.setDisable(true);
        meilleurOffre.setDisable(true);
    }
}
