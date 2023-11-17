package com.example.lab5gui.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FerretDB {
    protected Integer index;
    protected StringProperty name;
    public FerretDB(Integer index, String name){
        this.index=index;
        this.name = new SimpleStringProperty(name);
    }
}
