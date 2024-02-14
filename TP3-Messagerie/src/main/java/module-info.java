module fsp.tp3messagerie {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;


    opens fsp.tp3messagerie to javafx.fxml;
    exports fsp.tp3messagerie;
    opens fsp.tp3messagerie.client to javafx.fxml;
    exports fsp.tp3messagerie.client;
}