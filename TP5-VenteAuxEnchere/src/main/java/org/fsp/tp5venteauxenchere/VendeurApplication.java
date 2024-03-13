/*
 * VendeurApplication.java                                  28 févr. 2024
 * IUT de Rodez, pas de droit d'auteur
 */

package org.fsp.tp5venteauxenchere;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fsp.tp5venteauxenchere.client.IHMVendeur;

/**
 * @author François de Saint Palais
 */
public class VendeurApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(IHMVendeur.class.getResource("vendeur.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setTitle("Vente aux enchères - Vendeur");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
