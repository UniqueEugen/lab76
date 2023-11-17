package com.example.lab5gui;

import com.example.lab5gui.controllers.AddController;
import com.example.lab5gui.controllers.MainSceneController;
import com.example.lab5gui.dao.DB;
import com.example.lab5gui.entities.FerretDB;
import com.example.lab5gui.service.MasterService;
import com.example.lab5gui.service.MasterServiceImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@Data
public class FerretApp extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private ObservableList<FerretDB> listMarten = FXCollections.observableArrayList();;
    private ObservableList<FerretDB> listOtter = FXCollections.observableArrayList();;
    public FerretApp() throws SQLException, ClassNotFoundException {
        /*listMarten.add(new Ferret("Fluffy", "Pug", 8, "Жлобин", 2, "Marten"));
        listOtter.add(new Ferret("Archie", "Poodle", 3, "Могилев", 6, "Otter"));
        listMarten.add(new Ferret("Willie", "Bulldog", 5, "Брест", 4, "Marten"));
        listOtter.add(new Ferret("Hector", "Shepherd", 9, "Гродно", 6, "Otter"));
        listMarten.add(new Ferret("Duncan", "Dachshund", 1, "Hogwarts", 9,"Marten"));
        DB db = new DB();
        listMarten.forEach(ferret -> {
            try {
                db.insertFerret(ferret);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });*/
        MasterService masterService = new MasterServiceImpl();
        listMarten = masterService.getMartens();
        listOtter = masterService.getOtters();
    }
    //getters/setters


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Ferrets application");
        showBaseWindow();
    }

    public void showBaseWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FerretApp.class.getResource("/scenes/MainScene.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            InputStream iconStream = getClass().getResourceAsStream("/images/Icon.jpg");
            Image image = new Image(iconStream);
            primaryStage.getIcons().add(image);
            MainSceneController controller = loader.getController();
            controller.setFerretApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showCreateWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FerretApp.class.getResource("/scenes/AddScene.fxml"));
            AnchorPane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ng-ng-ng");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(new Scene(page));
            AddController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}