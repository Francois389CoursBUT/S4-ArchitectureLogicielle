module org.fsp.tp5venteauxenchere {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;


    opens org.fsp.tp5venteauxenchere to javafx.fxml;
    exports org.fsp.tp5venteauxenchere;

    opens org.fsp.tp5venteauxenchere.client to javafx.fxml;
    exports org.fsp.tp5venteauxenchere.client;
    exports org.fsp.tp5venteauxenchere.serveur;
    opens org.fsp.tp5venteauxenchere.serveur to javafx.fxml;
    exports org.fsp.tp5venteauxenchere.interface_client;
    opens org.fsp.tp5venteauxenchere.interface_client to javafx.fxml;
    exports org.fsp.tp5venteauxenchere.interface_serveur;
    opens org.fsp.tp5venteauxenchere.interface_serveur to javafx.fxml;

    exports org.fsp.tp5venteauxenchere.exception;
    opens org.fsp.tp5venteauxenchere.exception to javafx.fxml;
}