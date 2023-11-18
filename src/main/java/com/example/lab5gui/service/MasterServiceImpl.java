package com.example.lab5gui.service;

import com.example.lab5gui.dao.FerretRepository;
import com.example.lab5gui.entities.FerretDB;
import com.example.lab5gui.entities.master.MasterEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class MasterServiceImpl implements MasterService {

   // MasterDao masterDao = new MasterDaoImpl();

    /*@Autowired
    private EntityManager entityManager;
    RepositoryFactorySupport factory = new JpaRepositoryFactory(entityManager);
    private FerretRepository ferretRepository = factory.getRepository(FerretRepository.class) ;*/
    private FerretRepository ferretRepository;
    @Autowired
    public MasterServiceImpl(FerretRepository repository) {
        this.ferretRepository = repository;
    }

    @Override
    public boolean addMaster(MasterEntity master) {
        boolean isAdded = false;
        try {
            ferretRepository.save(master);
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
            ferretRepository.save(master);
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
            ferretRepository.deleteById(id);
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
            return (List<MasterEntity>) ferretRepository.findAll();
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
            return null;
        }
    }

    @Override
    public MasterEntity findMasterById(int id){
        try {
            return ferretRepository.findById(id).get();
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
            return null;
        }
    }

    @Override
    public ObservableList<FerretDB> getOtters() {
        try {
            ObservableList<FerretDB> otters = FXCollections.observableArrayList();
            ferretRepository.findAll().forEach(master ->
                    master.getFerretEntities().forEach(ferretEntity -> {
                        if (ferretEntity.getType().equals("Otter")) {
                            otters.add(new FerretDB(ferretEntity.getIndex(), ferretEntity.getName()));
                        }
                    }));
            return otters;
        } catch (HibernateError e) {
            ShowException.showNotice(e);
            return null;
        }
    }

    @Override
    public ObservableList<FerretDB> getMartens() {
        try {
            ObservableList<FerretDB> martens = FXCollections.observableArrayList();
            ferretRepository.findAll().forEach(master ->
                    master.getFerretEntities().forEach(ferretEntity -> {
                        if (ferretEntity.getType().equals("Marten")) {
                            martens.add(new FerretDB(ferretEntity.getIndex(), ferretEntity.getName()));
                        }
                    }));
            return martens;
        } catch (HibernateError e) {
            ShowException.showNotice(e);
            return null;
        }
    }
}