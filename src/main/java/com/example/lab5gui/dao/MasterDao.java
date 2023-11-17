package com.example.lab5gui.dao;


import com.example.lab5gui.entities.FerretDB;
import com.example.lab5gui.entities.master.MasterEntity;
import javafx.collections.ObservableList;

import java.util.List;

public interface MasterDao {
    boolean addMaster(MasterEntity master);
    boolean updateMaster(MasterEntity master);
    boolean deleteMaster(int id);
    List<MasterEntity> showMasters();
    MasterEntity findMasterById(int id);
    ObservableList<FerretDB> getMartens();
    ObservableList<FerretDB> getOtters();
}
