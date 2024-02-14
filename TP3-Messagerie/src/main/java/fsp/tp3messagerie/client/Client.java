/*
 * Client.java                                  07 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp.tp3messagerie.client;

import fsp.tp3messagerie.HelloApplication;
import fsp.tp3messagerie.Messagerie;
import fsp.tp3messagerie.serveur.ServeurMessagerie;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author François de Saint Palais
 */
public class Client extends Application {

    private Messagerie messagerie;

    @FXML
    public Button btnConnecter;

    @FXML
    public Button btnRafraichir;

    @FXML
    public Button btnEnvoyer;

    @FXML
    public TextArea messageDepose;

    @FXML
    public TextField urlServeur;

    @FXML
    public TextField pseudo;

    @FXML
    public TextField messageAEnvoyer;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("client.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        messageAEnvoyer.setDisable(true);
        messageDepose.setDisable(true);
        btnEnvoyer.setDisable(true);
    }

    /**
     * Envoyer à l'objet serveur le message saisi dans txtMessageAenvoyer avec le pseudo saisi
     * dans txtPseudo.
     */
    @FXML
    private void btnEnvoyerAction() {
        if (!messageAEnvoyer.getText().isEmpty())
            try {
                String message = messageAEnvoyer.getText();
                // Envoi message au serveur
                messagerie.posterMessage(message, pseudo.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Connexion serveur imposible.\n" + e);
                alert.showAndWait();
            }
    }

    /**
     * Recherche dans le serveur d'objets RMI (identifié par l'url saisie dans txtUrlServeur)
     * l'objet "serveur" et le mémorise dans serveur, puis affiche dans txtMessagesDeposes les
     * messages renvoyés par serveur.getMessages().
     * Affiche un message d'erreur si url non valide ou pseudo vide.
     */
    @FXML
    private void btnConnecterAction() {
        if (urlServeur.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Url serveur non saisie!");
            alert.showAndWait();
        } else if (pseudo.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Pseudo non saisi!");
            alert.showAndWait();
        } else try {
            messagerie = (Messagerie) Naming.lookup(urlServeur.getText());
            List<String> messages = messagerie.getMessage();
            for (String message : messages)
                messageDepose.appendText(message + "\n");
            urlServeur.setDisable(true);
            pseudo.setDisable(true);
            btnConnecter.setDisable(true);

            messageAEnvoyer.setDisable(false);
            btnEnvoyer.setDisable(false);
            messageAEnvoyer.setText("Saisir votre message"); // Remettre à vide si reconnexion
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Connexion serveur imposible.\n" + e);
            alert.showAndWait();
        }
    }

    /**
     * Réafficher dans txtMessagesDeposes les messages renvoués par erveur.getMessages().
     */
    @FXML
    private void btnRafraichirAction() {
        try {
            List<String> messages = messagerie.getMessage();
            messageDepose.setText("");
            for (String message : messages)
                messageDepose.appendText(message + "\n");
        } catch (RemoteException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur d'accès au serveur.\n" + e);
            alert.showAndWait();
        }
    }

    @FXML
    private void txtMessageAEnvoyerMouseClicked() {
        messageAEnvoyer.setText("");
    }
}
