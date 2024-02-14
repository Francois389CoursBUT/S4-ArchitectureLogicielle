/*
 * Client.java                                  07 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package fsp.new_tp2gestioncomptebanquaire.client;

import fsp.new_tp2gestioncomptebanquaire.message.CodeOperation;
import fsp.new_tp2gestioncomptebanquaire.message.Message;
import fsp.new_tp2gestioncomptebanquaire.message.Operation;
import fsp.new_tp2gestioncomptebanquaire.message.Retour;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
//import java.util.logging.*;

/**
 * @author François de Saint Palais
 */
public class Client extends Application {
    /**
     * Socket de communication avec le serveur
     *
     */
    private Socket socket;
    /**
     * Flux de lecture relié au canal d'entrée du socket
     */
    private ObjectInputStream in;
    /**
     * Flux de sortie relié au canal de sortie du socket
     */
    private ObjectOutputStream out;

    @FXML
    private TextField ipServeurTxt;
    @FXML
    private TextField portServeurTxt;
    @FXML
    private TextField montantTxt;
    @FXML
    private Button btnConnecter;
    @FXML
    private Button btnDeconecter;
    @FXML
    private Button btnAfficherSolde;
    @FXML
    private Button btnCrediter;
    @FXML
    private Button btnDebiter;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("client.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("IUTBanque - Client");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        btnDeconecter.setDisable(true);
        btnCrediter.setDisable(true);
        btnDebiter.setDisable(true);
        btnAfficherSolde.setDisable(true);
        montantTxt.setDisable(true);
    }

    /**
     * Crée un socket de communication avec le serveur en utilisant l'adresse IP et le port
     * saisis dans txtIPserveur et txtPort. Si la demande est acceptée, mémorise dans les attributs
     * in et out les flux d'entrée et sortie du socket, bloque la partie connexion de l'ihm
     * et débloque la partie communication de l'ihm.
     * Affiche un message si l'adresse IP ou le port siaisis sont incorrects ou la demande échoue.
     */
    @FXML
    private void btnConnecterAction() {
        if (ipServeurTxt.getText().isEmpty() || portServeurTxt.getText().isEmpty()) {
            Alert alerteCchampsVides = new Alert(Alert.AlertType.ERROR, "IP ou port serveur non saisi(s) !");
            alerteCchampsVides.showAndWait();
        } else
            try {
                InetAddress ipServeur
                        = InetAddress.getByName(ipServeurTxt.getText());
                int portServeur = Integer.parseInt(portServeurTxt.getText());
                // Demande connexion et récupération des cannaux d'entrée
                // et sortie du socket
                socket = new Socket(ipServeur, portServeur);
                in = new ObjectInputStream(socket.getInputStream());

                out = new ObjectOutputStream(socket.getOutputStream());
                // Désactiver la partie "connexion" de l'ihm
                // et activer la partie "communication" de l'ihm
                ipServeurTxt.setDisable(true);
                portServeurTxt.setDisable(true);
                btnConnecter.setDisable(true);

                btnDeconecter.setDisable(false);

                // Activer la partie de l'ihm relative à la communication
                montantTxt.setDisable(false);
                btnAfficherSolde.setDisable(false);
                btnCrediter.setDisable(false);
                btnDebiter.setDisable(false);
            } catch (UnknownHostException e) {
                Alert ipServeurIncorrecte = new Alert(Alert.AlertType.ERROR, "IP serveur incorrecte !");
                ipServeurIncorrecte.showAndWait();
            } catch (NumberFormatException e) {
                Alert portServeurIncorrect = new Alert(Alert.AlertType.ERROR, "Port serveur incorrect !");
                portServeurIncorrect.showAndWait();
            } catch (IOException e) {
                Alert demandeConnexionEchouee = new Alert(Alert.AlertType.ERROR, "Demande de connexion échouée !");
                demandeConnexionEchouee.showAndWait();
            }
    }//GEN-LAST:event_btnConnecterAction

    /**
     * Envoyer le message saisi dans txyMessageAenvoyer au serveur en le mettant dans
     * le canal out. Lire ensuite l'accusé réception du serveur sur le canal in et l'afficher
     * dans txtMessagesRecus.
     * Affiche un message d'erreur si txtMessagaAenvoyer est vide ou contient le message "FIN".
     */
    public void afficherSolde() {
        Operation operation = new Operation(CodeOperation.SOLDE);
        try {
            out.writeObject(operation);

            Message message = (Message) in.readObject();

            Alert showSolde;

            if (message instanceof Retour) {
                System.out.println("RECU: " + ((Retour) message).getSolde());
                showSolde = new Alert(Alert.AlertType.INFORMATION, "Solde: " + ((Retour) message).getSolde());
            } else if (message instanceof Retour) {
                System.out.println("RECU: " + ((Retour) message).getSolde());
                showSolde = new Alert(Alert.AlertType.INFORMATION, "Solde: " + ((Retour) message).getSolde());
            } else {
                showSolde = new Alert(Alert.AlertType.ERROR, "Erreur !");
            }

            showSolde.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Envoie le message "FIN" au serveur sur le canal out, lit l'accusé réception du serveur
     * sur le canal out et l'affiche dans txtMessagesRecus, ferme le socket de communication avec
     * le serveur, débloque la partie connexion de l'ihm et bloque la partie communication de l'ihm.
     */
    public void crediter() {
        envoyerOperationModification(CodeOperation.CREDITER);
    }

    public void debiter() {
        envoyerOperationModification(CodeOperation.DEBITER);
    }

    private void envoyerOperationModification(CodeOperation codeOperation) {
        try {
            Operation operation = new Operation(codeOperation, Double.parseDouble(montantTxt.getText()));

            out.writeObject(operation);

            Message message = (Message) in.readObject();
            System.out.println("RECU: " + message);

            Alert showSolde = new Alert(Alert.AlertType.INFORMATION, message);
            showSolde.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            Alert montantIncorrect = new Alert(Alert.AlertType.ERROR, "Montant incorrect !");
            montantIncorrect.showAndWait();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deconnecter() {
        try {
            out.writeObject(new Operation(CodeOperation.FIN_CONNEXION));
            socket.close();
            ipServeurTxt.setDisable(false);
            portServeurTxt.setDisable(false);
            btnConnecter.setDisable(false);

            montantTxt.setDisable(true);
            btnAfficherSolde.setDisable(true);
            btnCrediter.setDisable(true);
            btnDebiter.setDisable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
