package com.example.lab5gui.dao;


import com.example.lab5gui.entities.FerretDB;
import com.example.lab5gui.entities.ferret.FerretEntity;
import com.example.lab5gui.entities.master.MasterEntity;
import com.example.lab5gui.sessionFactory.SessionFactoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import myLibrary.console.Console;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MasterDaoImpl implements MasterDao {

    @Override
    public boolean addMaster(MasterEntity master) {
        boolean isAdded = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(master);
            tx.commit();
            session.close();
            isAdded = true;
        }
        catch (NoClassDefFoundError e) {
            Console.log("Exception: " + e);
        }
        return isAdded;
    }

    @Override
    public boolean updateMaster(MasterEntity master) {
        boolean isUpdated = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(master);
            tx.commit();
            session.close();
            isUpdated = true;
        }
        catch (NoClassDefFoundError e) {
            Console.log("Exception: " + e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteMaster(int id) {
        boolean isDeleted = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            MasterEntity master = session.load(MasterEntity.class, id);
            Transaction tx = session.beginTransaction();
            session.delete(master);
            tx.commit();
            session.close();
            isDeleted = true;
        }
        catch (NoClassDefFoundError e) {
            Console.log("Exception: " + e);
        }
        return isDeleted;
    }

    @Override
    public MasterEntity findMasterById(int id) {
        MasterEntity master = null;
        try(Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            return session.get(MasterEntity.class, id);
        }
        catch (NoClassDefFoundError e) {
            Console.log("Exception: " + e);
            return master;
        }
    }

    @Override
    public ObservableList<FerretDB> getMartens() {
        ObservableList<FerretDB> maretens = FXCollections.observableArrayList();
        try(Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            Query query = session.createQuery("from FerretEntity where type= :type");
            query.setParameter("type", "Marten");
            List<FerretEntity> martensList =  query.getResultList();
            martensList.forEach(marten-> maretens.add(new FerretDB(marten.getIndex(), marten.getName())));
            /*List<MasterEntity> masterEntities = (List<MasterEntity>) session.createQuery("from MasterEntity").list();
            masterEntities.forEach(master -> master.getFerretEntities().forEach(ferretEntity -> ));*/
            return maretens;
        }
        catch (NoClassDefFoundError e) {
            Console.log("Exception: " + e);
            return null;
        }
    }

    @Override
    public ObservableList<FerretDB> getOtters() {
        ObservableList<FerretDB> maretens = FXCollections.observableArrayList();
        try(Session session = SessionFactoryImpl.getSessionFactory().openSession()) {
            Query query = session.createQuery("from FerretEntity where type= :type");
            query.setParameter("type", "Otter");
            List<FerretEntity> martensList =  query.getResultList();
            martensList.forEach(marten-> maretens.add(new FerretDB(marten.getIndex(), marten.getName())));
            /*List<MasterEntity> masterEntities = (List<MasterEntity>) session.createQuery("from MasterEntity").list();
            masterEntities.forEach(master -> master.getFerretEntities().forEach(ferretEntity -> ));*/
            return maretens;
        }
        catch (NoClassDefFoundError e) {
            Console.log("Exception: " + e);
            return null;
        }
    }

    @Override
    public List<MasterEntity> showMasters() {
        try(Session session = SessionFactoryImpl.getSessionFactory().openSession()){
            return  (List<MasterEntity>) session.createQuery("from MasterEntity").list();
        }catch (Exception e){
            Console.log(e);
            return null;
        }
    }
}
