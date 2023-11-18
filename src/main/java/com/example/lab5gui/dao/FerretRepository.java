package com.example.lab5gui.dao;

import com.example.lab5gui.entities.master.MasterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FerretRepository extends CrudRepository<MasterEntity, Integer>{
}
