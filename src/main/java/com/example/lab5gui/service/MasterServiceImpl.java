package com.example.lab5gui.service;

import com.example.lab5gui.dao.MasterDao;
import com.example.lab5gui.dao.MasterDaoImpl;
import com.example.lab5gui.entities.FerretDB;
import com.example.lab5gui.entities.master.MasterEntity;
import javafx.collections.ObservableList;
import org.hibernate.HibernateError;

import java.util.List;

public class MasterServiceImpl implements MasterService {

    MasterDao masterDao = new MasterDaoImpl();

    public MasterServiceImpl() {}

    @Override
    public boolean addMaster(MasterEntity master) {
        boolean isAdded = false;
        try {
            masterDao.addMaster(master);
            isAdded = true;
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
        }
        return isAdded;
    }

    @Override
    public boolean updateMaster(MasterEntity master) {
        boolean isUpdated = false;
        try {
            masterDao.updateMaster(master);
            isUpdated = true;
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteMaster(int id) {
        boolean isDeleted=false;
        try {
            masterDao.deleteMaster(id);
            isDeleted = true;
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
        }
        return isDeleted;
    }

    @Override
    public  List<MasterEntity> showMasters() {
        try {
            return masterDao.showMasters();
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
            return null;
        }
    }

    @Override
    public MasterEntity findMasterById(int id){
        try {
            return masterDao.findMasterById(id);
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
            return null;
        }
    }

    @Override
    public ObservableList<FerretDB> getOtters() {
        try {
            return masterDao.getOtters();
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
            return null;
        }
    }

    @Override
    public ObservableList<FerretDB> getMartens() {
        try {
            return masterDao.getMartens();
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
            return null;
        }
    }
}