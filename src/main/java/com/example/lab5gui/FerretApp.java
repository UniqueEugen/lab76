package com.example.lab5gui;

import com.example.lab5gui.controllers.AddController;
import com.example.lab5gui.controllers.MainSceneController;
import com.example.lab5gui.entities.FerretDB;
import com.example.lab5gui.service.MasterService;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Data;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.io.InputStream;


@SpringBootApplication
@Data
public class FerretApp extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private ConfigurableApplicationContext springContext;
    private ObservableList<FerretDB> listMarten;
    private ObservableList<FerretDB> listOtter;
    @Qualifier("mainView")
    @Autowired
    private ControllerConfig.ViewHolder view;
    @Autowired
    private MasterService masterService;
    public FerretApp(){
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
    }
    //getters/setters

    @Override
    public void init(){
        springContext = new SpringApplicationBuilder()
                .sources(this.getClass())
                .run();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Ferrets application");
        showBaseWindow();
    }


    public void showBaseWindow() {
        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FerretApp.class.getResource("/scenes/MainScene.fxml"));
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);*/
        FxWeaver fxWeaver = springContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(MainSceneController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        InputStream iconStream = getClass().getResourceAsStream("/images/Icon.jpg");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
        listMarten = masterService.getMartens();
        listOtter = masterService.getOtters();
        MainSceneController controller = fxWeaver.loadController(MainSceneController.class);
        controller.setFerretApp(this, masterService);
        primaryStage.show();
    }
    public void showCreateWindow() {
        FxWeaver fxWeaver = springContext.getBean(FxWeaver.class);
            /*FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FerretApp.class.getResource("/scenes/AddScene.fxml"));
            AnchorPane page = loader.load();*/
        AnchorPane page = fxWeaver.loadView(AddController.class);
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Ng-ng-ng");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        dialogStage.setScene(new Scene(page));
        AddController controller = fxWeaver.loadController(AddController.class);
        controller.set(masterService);
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }
    @Override
    public void stop() throws Exception {
        springContext.stop();
    }
    public static void main(String[] args) {
        launch();
    }
}