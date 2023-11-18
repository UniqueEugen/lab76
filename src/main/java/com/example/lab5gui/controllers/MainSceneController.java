package com.example.lab5gui.controllers;

import com.example.lab5gui.FerretApp;
import com.example.lab5gui.converters.StringConverterFerret;
import com.example.lab5gui.dao.DB;
import com.example.lab5gui.entities.FerretDB;
import com.example.lab5gui.entities.SearchItem;
import com.example.lab5gui.entities.ferret.Ferret;
import com.example.lab5gui.entities.master.MasterEntity;
import com.example.lab5gui.service.MasterService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import lombok.Data;
import myLibrary.console.Console;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Data
public class MainSceneController {
    public static final String MARTEN = "Marten";
    //@FXML
    // private TableView<Ferret> ferrets;
    @FXML
    private ComboBox<FerretDB> martenCol;
    @FXML
    private ComboBox<FerretDB> otterCol;
    @FXML
    private ComboBox<FerretDB> masterCol;
    @FXML
    private CheckBox martenCheck;
    @FXML
    private CheckBox otterCheck;
    @FXML
    private CheckBox masterCheck;
    @FXML
    private Pane iteratorPane;
    @FXML
    private TextField findField;
    @FXML
    private TextField findWordsKol;
    @FXML
    private Button srchbtn;
    @FXML
    private Button prevWordBtn;
    @FXML
    private Button nextWordBtn;
    @FXML
    private TextField name;
    @FXML
    private TextField breed;
    @FXML
    private TextField age;
    @FXML
    private TextField city;
    @FXML
    private TextField levelOfTraining;
    @FXML
    private TextField type;
    @FXML
    private TextField mName;
    @FXML
    private TextField mAge;
    @FXML
    private TextField mSex;
    @FXML
    private  ComboBox<FerretDB> ferretMaster;
    private FerretApp ferretApp;
    private String fType;
    private ArrayList<SearchItem> searchList;
    private int iteratorSearchWord;
    private MasterService masterService;
    public void setFerretApp(FerretApp ferretApp, MasterService masterService) {
        this.masterService = masterService;
        this.ferretApp = ferretApp;
        //ferrets.setItems(ferretApp.getListFerret());
        martenCol.getItems().addAll(ferretApp.getListMarten());
        martenCol.setConverter(new StringConverterFerret());
        martenCol.setCellFactory(p -> new ListCell<FerretDB>() {
            @Override
            protected void updateItem(FerretDB item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item.getName().getValue());
                } else {
                    setText(null);
                }
            }
        });
        otterCol.getItems().addAll(ferretApp.getListOtter());
        otterCol.setConverter(new StringConverterFerret());
        otterCol.setCellFactory(p -> new ListCell<FerretDB>() {

            @Override
            protected void updateItem(FerretDB item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    setText(item.getName().getValue());
                } else {
                    setText(null);
                }
            }
        });
    }

    @FXML
    private void initialize() {
        // martenCol.setOnAction(event -> lbl.setText(langsComboBox.getValue()));
        martenCol.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showFerretsInformation(newValue, null, 0, 0);
                });
        otterCol.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showFerretsInformation(newValue, null, 0, 0);
                    iteratorPane.setVisible(false);
                });
        srchbtn.setOnAction(event -> loadData());
        srchbtn.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");
    }

    private void showFerretsInformation(FerretDB ferretDB, String field, int start, int length) {
        AtomicReference<Ferret> ferret=null;
        try {
            List<MasterEntity> masters = masterService.showMasters();
            AtomicReference<Ferret> finalFerret = ferret;
            masters.stream().takeWhile(master -> finalFerret!=null).forEach(master ->
                    master.getFerretEntities().stream()
                            .takeWhile(ferretEntity->ferretEntity.getIndex()==ferretDB.getIndex())
                            .forEach(ferretEntity -> finalFerret.set(new Ferret(ferretEntity))
                    ));
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

        this.fType = ferret.get().getType().getValue();
        name.setStyle("-fx-text-fill: black;");
        breed.setStyle("-fx-text-fill: black;");
        age.setStyle("-fx-text-fill: black;");
        city.setStyle("-fx-text-fill: black;");
        levelOfTraining.setStyle("-fx-text-fill: black;");
        type.setStyle("-fx-text-fill: black;");
        if (ferret.get() != null) {
            name.setText(ferret.get().getName() != null ? ferret.get().getName().getValue() : null);
            breed.setText(ferret.get().getBreed() != null ? ferret.get().getBreed().getValue() : null);
            age.setText(ferret.get().getAge() != null ? String.valueOf(ferret.get().getAge().getValue()) : null);
            city.setText(ferret.get().getCity() != null ? ferret.get().getCity().getValue() : null);
            levelOfTraining.setText(ferret.get().getLevelOfTraining() != null ? String.valueOf(ferret.get().getLevelOfTraining().getValue()) : null);
            type.setText(ferret.get().getType() != null ? ferret.get().getType().getValue() : null);
        } else {
            name.setText("");
            breed.setText("");
            age.setText("");
            city.setText("");
            levelOfTraining.setText("");
            type.setText("");
        }
        if (field != null) {
            setStyle(field, start,length);
        }
    }
    private void setStyle(String field,int start, int length){
        Field[] fieldsThis = MainSceneController.class.getDeclaredFields();
        switch (field) {
            case "name":
                name.setStyle("-fx-background-color: yellow;-fx-font-weight: bold");
                name.requestFocus();
                name.positionCaret(0);
                name.selectRange(start,length+start);
                break;
            case "breed":
                breed.setStyle("-fx-background-color: yellow;-fx-font-weight: bold");
                breed.requestFocus();
                breed.positionCaret(0);
                breed.selectRange(start,length+start);
                break;
            case "age":
                age.setStyle("-fx-background-color: yellow;-fx-font-weight: bold");
                age.requestFocus();
                age.positionCaret(0);
                age.selectRange(start,length+start);
                break;
            case "city":
                city.setStyle("-fx-background-color: yellow; -fx-font-weight: bold");
                city.requestFocus();
                city.positionCaret(0);
                city.selectRange(start,length+start);
                break;
            case "levelOfTraining":
                levelOfTraining.setStyle("-fx-background-color: yellow; -fx-font-weight: bold;");
                levelOfTraining.requestFocus();
                levelOfTraining.positionCaret(0);
                levelOfTraining.selectRange(start,length+start);
                break;
            case "type":
                type.setStyle("-fx-background-color: yellow;-fx-font-weight: bold");
                type.requestFocus();
                type.positionCaret(0);
                type.selectRange(start,length+start);
                break;
            default:
                break;
        }
    }
    @FXML
    private void delete() throws SQLException, ClassNotFoundException {
        if (fType.equals(MARTEN)) {
            hiberDelete(martenCol);
            ferretApp.getListMarten().remove(martenCol.getSelectionModel().getSelectedIndex());
        } else {
            //db.deleteOtter(martenCol.getSelectionModel().getSelectedIndex());
            hiberDelete(otterCol);
            AtomicInteger id;
            ferretApp.getListOtter().remove(otterCol.getSelectionModel().getSelectedIndex());
        }
    }

    private void hiberDelete(ComboBox<FerretDB> otterCol) {
        AtomicInteger id = new AtomicInteger(otterCol.getItems().remove(otterCol.getSelectionModel()
                .getSelectedIndex()).getIndex());
        List<MasterEntity> masters = masterService.showMasters();
        masters.stream().takeWhile(master -> id.get()>0).forEach(master ->
                master.getFerretEntities().stream().takeWhile(ferretEntity -> ferretEntity.getIndex() == id.get())
                        .forEach(ferretEntity -> {
                            if (ferretEntity.getIndex() == id.get()) {
                                master.getFerretEntities().remove(ferretEntity);
                                masterService.updateMaster(master);
                                id.set(-1);
                            }
                        }));
    }

    @FXML
    private void edit() throws SQLException, ClassNotFoundException {
        if (fType.equals(MARTEN)) {
            UpdateFerret(martenCol);
        } else {
            UpdateFerret(otterCol);
        }
    }

    private void UpdateFerret(ComboBox<FerretDB> ferretCol) throws SQLException, ClassNotFoundException {
        AtomicInteger id = new AtomicInteger(ferretCol.getItems().get(ferretCol.getSelectionModel().getSelectedIndex()).getIndex());
        ferretCol.getItems().set(ferretCol.getSelectionModel().getSelectedIndex(),
                new FerretDB(id.get(), name.getText()));
        List<MasterEntity> masters = masterService.showMasters();
        masters.stream().takeWhile(master -> id.get() >0)
                .forEach(master ->
                master.getFerretEntities().stream().takeWhile(ferretEntity -> ferretEntity.getIndex() == id.get())
                        .forEach(ferretEntity -> {
                            if (ferretEntity.getIndex()== id.get()){
                                ferretEntity.setName(name.getText());
                                ferretEntity.setAge(Integer.parseInt(age.getText()));
                                ferretEntity.setCity(city.getText());
                                ferretEntity.setBreed(breed.getText());
                                ferretEntity.setLevelOfTraining(Integer.parseInt(levelOfTraining.getText()));
                                masterService.updateMaster(master);
                                id.set(-1);
                            }
                        }));
    }

    @FXML
    private void create() throws SQLException, ClassNotFoundException {
        DB db = new DB();
        Ferret someFerret;
        ferretApp.showCreateWindow();
        someFerret = db.getLastFerret();
        if (someFerret.getName() != null && !someFerret.getName().getValue().isEmpty() &&
                !someFerret.getType().getValue().isEmpty()) {
            if (someFerret.getType().getValue().equals(MARTEN)) {
                martenCol.getItems().add(new FerretDB(someFerret.getIndex(),someFerret.getName()));
            } else {
                otterCol.getItems().add(new FerretDB(someFerret.getIndex(),someFerret.getName()));
            }
        }
        Console.log(someFerret);
    }

    private void loadData(){
        iteratorSearchWord=0;
        searchList = new ArrayList<>();
        iteratorPane.setVisible(true);
        String searchText = findField.getText();
        Console.log(searchText.length());
        if (martenCheck.isSelected()) {
            martenCol.getItems().forEach(ferret -> setWord(ferret, searchText));
        }
        if (otterCheck.isSelected()) {
            otterCol.getItems().forEach(ferret -> setWord(ferret, searchText));
        }
        Console.log(searchList);
        if (searchList.isEmpty()) {
            iteratorSearchWord = 0;
            findWordsKol.setText(iteratorSearchWord + "/" + searchList.size());
        } else {
            iteratorSearchWord = 1;
            findWordsKol.setText(iteratorSearchWord + "/" + searchList.size());
            showFerretsInformation(searchList.get(0).getFerret(), searchList.get(0).getField(),
                    searchList.get(0).getStartPos(),searchList.get(0).getLength());
        }
    }

    private void setWord(FerretDB ferretDB, String searchText) {
        AtomicReference<Ferret> ferret = null;
        try {
            List<MasterEntity> masters = masterService.showMasters();
            masters.stream().takeWhile(master -> ferret.get() !=null).forEach(master ->
                    master.getFerretEntities().stream()
                            .takeWhile(ferretEntity->ferretEntity.getIndex()==ferretDB.getIndex())
                            .forEach(ferretEntity -> ferret.set(new Ferret(ferretEntity))
                            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Map<String, String> map = ferret.get().getMap();
        //Ferret finalFerret = ferret;
        map.forEach((field, value) -> {
            if (value.contains(searchText)) {
                Console.log(field);
                int index = 0;
                while (true) {
                    index = value.indexOf(searchText, index);
                    if (index != -1) {
                        searchList.add(new SearchItem(new FerretDB(ferret.get().getIndex(), ferret.get().getName()),
                                field.replace("0",""),
                                index, searchText.length()));
                        index ++;
                    } else {
                        break;
                    }
                }
            }
        });
    }

    @FXML
    private void next(){
        if(iteratorSearchWord<searchList.size()){
            iteratorSearchWord++;
            showWord();
        }
    }
    @FXML
    private void prev(){
        if(iteratorSearchWord>1){
            iteratorSearchWord--;
            showWord();
        }
    }
    private void showWord(){
        findWordsKol.setText(iteratorSearchWord + "/" + searchList.size());
        Console.log(searchList.get(iteratorSearchWord-1));
        showFerretsInformation(searchList.get(iteratorSearchWord-1).getFerret(),
                searchList.get(iteratorSearchWord-1).getField(),
                searchList.get(iteratorSearchWord-1).getStartPos(),
                searchList.get(iteratorSearchWord-1).getLength());
    }

}

