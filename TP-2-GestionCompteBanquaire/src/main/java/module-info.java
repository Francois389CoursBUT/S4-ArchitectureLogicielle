module fsp.new_tp2gestioncomptebanquaire {
    requires javafx.controls;
    requires javafx.fxml;


    opens fsp.new_tp2gestioncomptebanquaire to javafx.fxml;
    exports fsp.new_tp2gestioncomptebanquaire;

    opens fsp.new_tp2gestioncomptebanquaire.client to javafx.fxml;
    exports fsp.new_tp2gestioncomptebanquaire.client;
}