/*
 * ParticipantApplication.java                                  28 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.tp5venteauxenchere;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fsp.tp5venteauxenchere.client.IHMParticipant;

/**
 * @author François de Saint Palais
 */
public class ParticipantApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(IHMParticipant.class.getResource("participant.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setTitle("Vente aux enchères - org.fsp.tp5venteauxenchere.interface_client.Participant");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
