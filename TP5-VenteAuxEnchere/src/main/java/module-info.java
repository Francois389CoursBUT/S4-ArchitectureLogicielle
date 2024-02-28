module org.fsp.tp5venteauxenchere {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.fsp.tp5venteauxenchere to javafx.fxml;
    exports org.fsp.tp5venteauxenchere;

    opens org.fsp.tp5venteauxenchere.client to javafx.fxml;
    exports org.fsp.tp5venteauxenchere.client;
}