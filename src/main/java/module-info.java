module ni.edu.uam.pae_eventos_javafx_equipo {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens ni.edu.uam.pae_eventos_javafx_equipo to javafx.fxml;
    opens ni.edu.uam.pae_eventos_javafx_equipo.application to javafx.fxml;
    opens ni.edu.uam.pae_eventos_javafx_equipo.controller to javafx.fxml;

    opens ni.edu.uam.pae_eventos_javafx_equipo.models to javafx.base;

    exports ni.edu.uam.pae_eventos_javafx_equipo;
    exports ni.edu.uam.pae_eventos_javafx_equipo.application;
    exports ni.edu.uam.pae_eventos_javafx_equipo.controller;
    exports ni.edu.uam.pae_eventos_javafx_equipo.models;
}