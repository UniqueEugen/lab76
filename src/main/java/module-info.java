module com.example.lab5gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.databind;
    requires static lombok;
    requires MyLibrary;
    requires java.sql;
    requires java.persistence;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires static javassist;
    opens com.example.lab5gui to javafx.fxml;
    exports com.example.lab5gui;
    exports com.example.lab5gui.controllers;
    opens com.example.lab5gui.controllers to javafx.fxml;
    exports com.example.lab5gui.entities;
    opens com.example.lab5gui.entities to javafx.fxml;
    exports com.example.lab5gui.entities.master;
    opens com.example.lab5gui.entities.master to javafx.fxml;
    exports com.example.lab5gui.entities.ferret;
    opens com.example.lab5gui.entities.ferret to javafx.fxml;
}