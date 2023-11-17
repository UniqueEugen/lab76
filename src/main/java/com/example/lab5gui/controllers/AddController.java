package com.example.lab5gui.controllers;

import com.example.lab5gui.dao.DB;
import com.example.lab5gui.entities.ferret.FerretEntity;
import com.example.lab5gui.entities.master.MasterEntity;
import com.example.lab5gui.service.MasterService;
import com.example.lab5gui.service.MasterServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import lombok.Data;

import java.sql.SQLException;

@Data
public class AddController {
    private Stage dialogStage;

    @FXML
    private TextField nickName;
    @FXML
    private RadioButton martenRadio;
    @FXML
    private RadioButton otterRadio;
    @FXML
    private RadioButton masterRadio;
    @FXML
    private ToggleGroup ferretType;

    @FXML
    private void ok(){
        MasterService masterService = new MasterServiceImpl();
        if(nickName != null && !nickName.getText().isEmpty()){
            if (((RadioButton)ferretType.getSelectedToggle()).getText().equals("Master")){
                masterService.addMaster(new MasterEntity(nickName.getText()));
            }
        }else {
            MasterEntity master = masterService.findMasterById(1);
            FerretEntity ferret = new FerretEntity();
            ferret.setName(nickName.getText());
            ferret.setType(((RadioButton) ferretType.getSelectedToggle()).getText());
            ferret.setMasterEntity(master);
            master.addFerret(ferret);
            masterService.updateMaster(master);
        }
        /*if (nickName != null && !nickName.getText().isEmpty()) {
            DB db = new DB();
            db.insertFerret(nickName.getText(), ((RadioButton) ferretType.getSelectedToggle()).getText());
            dialogStage.close();
        }*/
    }

    @FXML
    private void cansel() {
        dialogStage.close();
    }
}