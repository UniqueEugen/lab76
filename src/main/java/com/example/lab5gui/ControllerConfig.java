package com.example.lab5gui;

import com.example.lab5gui.controllers.MainSceneController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ControllerConfig {

    @Bean(name = "mainView")
    public ViewHolder getMainView() throws IOException {
        return loadView("/scenes/MainScene.fxml");
    }
    @Bean
    public MainSceneController getMainSceneController() throws IOException {
        return (MainSceneController) getMainView().getController();
    }

    protected ViewHolder loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new ViewHolder(loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    public class ViewHolder {
        private Parent view;
        private Object controller;

        public ViewHolder(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
    }
}
